package br.com.sistema.logica;

import br.com.sistema.data.CredencialDAO;
import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.SegmentacaoDAO;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Segmentacao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SegmentacaoListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            String credor2 = "0";
            String operador2 = "0";
            String indexcredor2 = "0";
            String indexoperador2 = "0";
            if (request.getParameter("idcredor2") != null){
                credor2 = request.getParameter("idcredor2");
            }
            if (request.getParameter("idoperador2") != null){
                operador2 = request.getParameter("idoperador2");
            }
            if (request.getParameter("indexcredor2") != null){
                indexcredor2 = request.getParameter("indexcredor2");
            }
            if (request.getParameter("indexoperador2") != null){
                indexoperador2 = request.getParameter("indexoperador2");
            }
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                ArrayList<Segmentacao> listaSegmentacao = SegmentacaoDAO.listar(new Segmentacao(Integer.parseInt(credor2),Integer.parseInt(operador2)));
                request.setAttribute("listaSegmentacao",listaSegmentacao);
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                ArrayList<Credencial> listaCredencial = CredencialDAO.listar();
                request.setAttribute("listaCredencial",listaCredencial);
                request.setAttribute("idcredor2", credor2);
                request.setAttribute("indexcredor2", indexcredor2);
                request.setAttribute("idoperador2", operador2);
                request.setAttribute("indexoperador2", indexoperador2);
                request.setAttribute("mensagem","");
                request.getRequestDispatcher("/sistema/supervisor/segmentacao/segmentacao.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }        
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar as Segmentações do Atendimento!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
    
}
