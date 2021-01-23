package br.com.sistema.logica;

import br.com.sistema.data.AtendimentoDAO;
import br.com.sistema.data.NegociacaoDAO;
import br.com.sistema.data.StatusDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Negociacao;
import br.com.sistema.modelo.Status;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtendimentoIniciar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int status = 0;
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Atendimento atendimento = new Atendimento(login);
                atendimento = AtendimentoDAO.iniciar(atendimento);
                if (atendimento.getDivida()==null){
                    request.setAttribute("mensagem","Avise seu supervisor que sua lista de atendimento está vazia!!!");
                    request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
                }else{
                    ArrayList<Atendimento> listaAtendimento = AtendimentoDAO.listar(atendimento);
                    ArrayList<Negociacao> listaNegociacao = NegociacaoDAO.listar(atendimento.getDivida());
                    ArrayList<Status> listaStatus = StatusDAO.listar();
                    request.getSession().setAttribute("listaAtendimento",listaAtendimento);
                    request.getSession().setAttribute("listaNegociacao",listaNegociacao);
                    request.getSession().setAttribute("atendimento",atendimento);
                    request.getSession().setAttribute("listaStatus",listaStatus);
                    request.setAttribute("indexstatus",status);
                    request.setAttribute("valorentrada",0.00);
                    request.setAttribute("aba",0);
                    request.setAttribute("mensagem","");
                    request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
                }
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar iniciar um atendimento!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
    
}
