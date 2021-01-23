package br.com.sistema.logica;

import br.com.sistema.data.LogDAO;
import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioAlterar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                String nome = request.getParameter("nome");
                int id = Integer.parseInt(request.getParameter("id"));
                String tipo = request.getParameter("tipo");
                String status = request.getParameter("status");
                
                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setTipo(tipo);
                usuario.setStatus(status);
                
                UsuarioDAO.atualizar(usuario);
                LogDAO.inserir(new Log(login,"Alterou o cadastro do usuário: "+usuario.getUsuario()));
                
                ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
                request.setAttribute("listaUsuario",listaUsuario);
                request.setAttribute("mensagem","Usuário alterado com sucesso!!!");
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
            request.setAttribute("mensagem","Erro ao tentar alterar usuário!!!");
            request.getRequestDispatcher("/sistema/supervisor/usuario/usuario.jsp").forward(request, response);
        }
    }
    
}
