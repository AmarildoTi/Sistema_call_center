package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.AtendimentoDAO;
import br.com.sistema.data.DividaDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.ParcelaDAO;
import br.com.sistema.data.StatusDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Devedor;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Negociacao;
import br.com.sistema.modelo.Parcela;
import br.com.sistema.modelo.Status;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParcelaPagar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int opcao = 4;
        int indexcredor = 0;
        String texto2 = "";
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                texto2 = request.getParameter("texto2").trim().replaceAll("\\.", "").replace("-","");
                opcao = Integer.parseInt(request.getParameter("opcao"));
                indexcredor = Integer.parseInt(request.getParameter("indexcredor"));
                Negociacao negociacao = new Negociacao(Integer.parseInt(request.getParameter("negociacao")));
                int numero = Integer.parseInt(request.getParameter("parcela").trim());
                double valor = Double.parseDouble(request.getParameter("valorpagamento").replaceAll("\\.","").replace(",",".").replace("R$ ",""));
                Date data = Date.valueOf(Funcoes.formataDataSQL(request.getParameter("datapagamento")).replaceAll("\\/", "-"));
                Parcela parcela = new Parcela(numero, data, valor);
                ArrayList<Parcela> listaParcela = new ArrayList<Parcela>();
                listaParcela.add(parcela);
                negociacao.setParcela(listaParcela);
                if (ParcelaDAO.localizarParcelaPendente(negociacao) == numero){
                    ParcelaDAO.pagar(negociacao);
                    if (ParcelaDAO.localizarParcelaPendente(negociacao) == 0){
                        Atendimento atendimento = new Atendimento();
                        atendimento.setDivida(new Divida(Integer.parseInt(request.getParameter("iddivida"))));
                        atendimento.setCredencial(login);
                        atendimento.setDescricao("Dívida Quitada");
                        atendimento.setStatus(StatusDAO.localizar(new Status(6)));
                        AtendimentoDAO.inserir(atendimento);
                    }
                    request.setAttribute("mensagem","Confirmação efetuada com sucesso!!!");
                }else{
                    request.setAttribute("mensagem","Parcela(s) Anterior(es) Pendente(s)!!!");
                }
                ArrayList<Divida> listaDivida = DividaDAO.listarNegociadaPorCPF(new Devedor(Long.parseLong(texto2.trim())));
                request.setAttribute("listaDivida2",listaDivida);
                LogDAO.inserir(new Log(login,"Confirmou o pagamento da parcela "+String.valueOf(numero)+" com vencimento em "+request.getParameter("vencimento")+" da negociação "+String.valueOf(negociacao.getId())));
                request.setAttribute("opcao",opcao);
                request.setAttribute("texto2", texto2);
                request.setAttribute("indexcredor", indexcredor);
                request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){    
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar confirmar pagamento!!!");
            ArrayList<Divida> listaDivida = new ArrayList<Divida>();
            request.setAttribute("listaDivida",listaDivida);
            request.setAttribute("opcao",opcao);
            request.setAttribute("texto2", texto2);
            request.setAttribute("indexcredor", indexcredor);
            request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
        }
    }
    
}
