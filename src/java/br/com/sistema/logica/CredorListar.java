package br.com.sistema.logica;

import br.com.sistema.data.CredorDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Login;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CredorListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            String credor = "0";
            String indexcredor = "0";
            if (request.getParameter("idcredor") != null){
                credor = request.getParameter("idcredor");
            }
            if (request.getParameter("indexcredor") != null){
                indexcredor = request.getParameter("indexcredor");
            }
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("idcredor", credor);
                request.setAttribute("contador", 0);
                request.setAttribute("indexcredor", indexcredor);
                request.setAttribute("mensagem","");
                request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar os credores!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }   
}
