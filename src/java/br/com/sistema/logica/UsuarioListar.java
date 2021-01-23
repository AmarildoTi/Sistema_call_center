package br.com.sistema.logica;

import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
                request.setAttribute("listaUsuario",listaUsuario);
                request.setAttribute("mensagem","");
                request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar usuários!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
}
