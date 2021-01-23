package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.StatusDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.OpcaoNegociacao;
import br.com.sistema.modelo.Status;
import br.com.sistema.negocio.OpcaoNegociacaoRN;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpcaoNegociacaoCalcular implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Atendimento atendimento = (Atendimento) request.getSession().getAttribute("atendimento");
                double valorentrada, desconto;
                if (request.getParameter("valorentrada") == null){
                    valorentrada = 0.0;
                }
                else{
                    valorentrada = Double.parseDouble(request.getParameter("valorentrada").replace("R$","").replace(".","").replace(",",".").replace(" ",""));
                }
                if (request.getParameter("desconto") == null){
                    desconto = 0.0;
                }
                else{
                    desconto = Double.parseDouble(request.getParameter("desconto").replace("R$","").replace(".","").replace(",",".").replace(" ",""));
                }
                ArrayList<OpcaoNegociacao> listaOpcao = new ArrayList<OpcaoNegociacao>();
                OpcaoNegociacao opcaonegociacao = new OpcaoNegociacao(Funcoes.toDate(request.getParameter("datapagamento")),valorentrada,desconto);
                listaOpcao.add(opcaonegociacao);
                atendimento.setOpcaoNegociacao(listaOpcao);
                listaOpcao = OpcaoNegociacaoRN.calcular(atendimento);
                if (valorentrada < listaOpcao.get(0).getValorEntrada()){
                    valorentrada = listaOpcao.get(0).getValorEntrada();
                }
                if(valorentrada > listaOpcao.get(0).getValorNegociacao()){
                    valorentrada = listaOpcao.get(0).getValorNegociacao();
                }
                atendimento.setOpcaoNegociacao(listaOpcao);
                atendimento.setData(Funcoes.toDateTime(request.getParameter("dataatendimento")));
                ArrayList<Status> listaStatus = StatusDAO.listar();
                request.setAttribute("listaStatus",listaStatus);
                request.setAttribute("desconto",desconto);
                request.setAttribute("valorentrada",valorentrada);
                request.setAttribute("atendimento",atendimento);
                request.setAttribute("aba",1);
                request.getRequestDispatcher("/sistema/operador/"+request.getParameter("jsp")).forward(request, response);
                request.setAttribute("mensagem","");
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar calcular opções de negocição!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
    
}
