package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Usuario;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioInserir implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                String nome = request.getParameter("nome");
                Long cpf = Long.parseLong(Funcoes.retirarMask(request.getParameter("cpf"),"###.###.###-##"));
                String username = request.getParameter("usuario");
                String senha = Funcoes.criptografa(request.getParameter("senha"));
                String tipo = request.getParameter("tipo");
                String status = request.getParameter("status");
                
                Usuario usuario = new Usuario();
                usuario.setCpf(cpf);
                usuario.setNome(nome);
                usuario.setSenha(senha);
                usuario.setTipo(tipo);
                usuario.setStatus(status);
                usuario.setUsuario(username);
                
                
                if(UsuarioDAO.usuarioJaExiste(usuario)){
                    request.setAttribute("mensagem","CPF já cadastrado!!!");
                }else{
                    if(UsuarioDAO.nomeUsuarioJaExiste(usuario)){
                        request.setAttribute("mensagem","login de usuário já cadastrado!!!");
                    }else{
                        UsuarioDAO.inserir(usuario);
                        LogDAO.inserir(new Log(login,"Inseriu no cadastro o novo usuário: "+usuario.getUsuario()));
                        request.setAttribute("mensagem","Usuário cadastrado com sucesso!!!");
                    }
                }
                ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
                request.setAttribute("listaUsuario",listaUsuario);
                request.getRequestDispatcher("/sistema/supervisor/usuario/usuario.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar inserir um usuário!!!");
            ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
            request.setAttribute("listaUsuario",listaUsuario);
            request.getRequestDispatcher("/sistema/supervisor/usuario/usuario.jsp").forward(request, response);        }
    }
    
}
