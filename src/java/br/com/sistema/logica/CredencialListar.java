package br.com.sistema.logica;

import br.com.sistema.data.CredencialDAO;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Login;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CredencialListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                ArrayList<Credencial> listaCredencial = CredencialDAO.listar();
                request.setAttribute("listaCredencial",listaCredencial);
                request.setAttribute("mensagem","");
                request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar credenciais!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
}
