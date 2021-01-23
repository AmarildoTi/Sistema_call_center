package br.com.sistema.logica;

import br.com.sistema.data.CredencialDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioExcluir implements  Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Credencial credencial = new Credencial(Integer.parseInt(request.getParameter("id")));
                credencial = CredencialDAO.localizarPorID(credencial);
                UsuarioDAO.excluir(new Usuario(Integer.parseInt(request.getParameter("id"))));
                LogDAO.inserir(new Log(login,"Excluiu o cadastro do usuário: "+credencial.getUsuario()));
                
                ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
                request.setAttribute("listaUsuario",listaUsuario);
                request.setAttribute("mensagem","Usuário excluído com sucesso!!!");
                request.getRequestDispatcher("/sistema/supervisor/usuario/usuario.jsp").forward(request, response);               
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
            request.setAttribute("listaUsuario",listaUsuario);
            request.setAttribute("mensagem","Erro ao tentar excluir usuário!!!");
            request.getRequestDispatcher("/sistema/supervisor/usuario/usuario.jsp").forward(request, response);               
        }
    }
}
