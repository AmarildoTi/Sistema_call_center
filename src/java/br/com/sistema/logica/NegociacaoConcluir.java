package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.AtendimentoDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Negociacao;
import br.com.sistema.modelo.Parcela;
import br.com.sistema.negocio.CarneRN;
import br.com.sistema.negocio.EmailRN;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NegociacaoConcluir implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                double valorcorrigido,valorparcela,valorentrada, valornegociado;
                valornegociado = 0.0;
                Atendimento atendimento = (Atendimento) request.getSession().getAttribute("atendimento");
                int parcelas;
                String descricao="Negociação Fechada: ";
                String[] parc =  request.getParameter("parcelamento").split("#");
                parcelas = Integer.parseInt(parc[0]);
                valorcorrigido = Double.parseDouble(request.getParameter("valorcorrigido").replace("R$","").replace(".","").replace(",",".").replace(" ",""));
                valorparcela = Double.parseDouble(parc[1].replace("R$","").replace(".","").replace(",",".").trim());
                
                Negociacao negociacao = new Negociacao();
                if (request.getParameter("email") != null){
                    negociacao.setEmail(request.getParameter("email"));
                }else{
                    negociacao.setEmail(null);
                }
                negociacao.setStatus("Ativa");
                negociacao.setValorCorrigido(valorcorrigido);
                negociacao.setData(Funcoes.toDate(request.getParameter("datapagamento")));
                ArrayList<Parcela> lista = new ArrayList<Parcela>();
                Parcela parcela = new Parcela();
                
                if (request.getParameter("valorentrada").trim().equals("")){
                    valorentrada=0.0;
                }else{
                    valorentrada = Double.parseDouble(request.getParameter("valorentrada").replace("R$","").replace(".","").replace(",",".").replace(" ",""));
                }
                if(valorentrada>0.0){                
                    negociacao.setParcelamento(parcelas+1);
                    parcela.setValor(valorentrada);
                    descricao = descricao + "Entrada de " +request.getParameter("valorentrada") + " + " + parc[0]+ " parcela(s) de "+parc[1];
                }else{
                    negociacao.setParcelamento(parcelas);
                    parcela.setValor(valorparcela);
                    if (parc[0].trim().equals("0")){
                        descricao = descricao + "A vista: "+parc[1];
                    }else{
                        descricao = descricao + parc[0]+ " parcela(s) de "+parc[1];                        
                    }
                }
                valornegociado += parcela.getValor();
                if (parc[0].trim().equals("0")){
                    descricao = descricao + " com vencimento em "+ negociacao.getDataString();
                }else{
                    descricao = descricao + " com primeiro vencimento em "+ negociacao.getDataString();
                }
                Calendar dt = Calendar.getInstance();
                dt.setTime(Funcoes.toDate(negociacao.getDataString()));
                parcela.setNumero(1);
                parcela.setDataVencimento(dt.getTime());
                lista.add(parcela);
                for(int i=2; i <= negociacao.getParcelamento(); i++){
                    parcela = new Parcela();
                    dt.add(Calendar.MONTH,1);
                    parcela.setDataVencimento(dt.getTime());
                    parcela.setValor(valorparcela);
                    parcela.setNumero(i);
                    valornegociado += parcela.getValor();
                    lista.add(parcela);
                }
                negociacao.setValorNegociado(valornegociado);
                negociacao.setParcela(lista);
                ArrayList<Negociacao> listaNegociacao = new ArrayList<Negociacao>();
                listaNegociacao.add(negociacao);
                atendimento.getDivida().setNegociacao(listaNegociacao);
                atendimento.getStatus().setId(9);
                atendimento.setDescricao(descricao);
                negociacao.setId(AtendimentoDAO.inserirComNegociacao(atendimento));
                
                request.getSession().removeAttribute("atendimento");
                request.getSession().removeAttribute("listaAtendimento");
                request.getSession().removeAttribute("listaNegociacao");
                request.getSession().removeAttribute("listaStatus");
                if (request.getSession().getAttribute("listaDivida") != null){
                    request.getSession().removeAttribute("listaDivida");
                }
                String nomeDiretorio = "C:\\CallCenter\\Negociacao\\";
                nomeDiretorio += atendimento.getDivida().getCredor().getNomefantasia()+"\\";

                String nomeArquivo = atendimento.getDivida().getCredor().getId()+"_"+atendimento.getDivida().getDevedor().getCpf()+"_"+atendimento.getDivida().getDevedor().getNome()+"_"+negociacao.getId()+".pdf";
                
                File diretorio = new File(nomeDiretorio);
                if (!diretorio.exists()) {  
                    diretorio.mkdirs();
                }
                try{
                    CarneRN.criar(atendimento.getDivida(),nomeDiretorio+nomeArquivo);
                }
                catch (Exception e){
                    request.setAttribute("mensagem","Erro ao tentar criar o carnê de cobrança!!!");
                    request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
                }
                try{
                    if (request.getParameter("email") != null){
                        boolean enviarEmail = EmailRN.enviar(request.getParameter("email"), nomeDiretorio+nomeArquivo);
                    }
                }
                catch (Exception e){
                    request.setAttribute("mensagem","Erro ao tentar enviar o email!!!");
                    request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
                }
                request.setAttribute("mensagem", "Negociação concluída com sucesso!!!");
                request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar concluir a negociação!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
}

}
