package br.com.sistema.logica;

import Util.Funcoes;
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

public class UsuarioAlterarSenha implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Usuario usuario = UsuarioDAO.localizar(new Usuario(request.getParameter("usuario")));
                if (Funcoes.criptografa(request.getParameter("senhaatual")).equals(usuario.getSenha().trim())){
                    UsuarioDAO.alterarSenha(new Usuario(request.getParameter("usuario"), Funcoes.criptografa(request.getParameter("senhanova"))));
                    request.setAttribute("mensagem", "Senha alterada com sucesso!!!");
                    String acao;
                    if (usuario.getId() != login.getId()){
                        acao = "Alterou a senha do usuário: " + usuario.getUsuario();
                    }else{
                        acao = "Alterou sua senha";
                    }
                    LogDAO.inserir(new Log(login,acao));
                }else{
                    request.setAttribute("mensagem", "Senha atual inválida!!!");
                }
                ArrayList<Credencial> listaCredencial= CredencialDAO.listar();
                request.setAttribute("listaCredencial",listaCredencial);
                request.getRequestDispatcher("/sistema/publico/login/alterarSenha.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            ArrayList<Credencial> listaCredencial= CredencialDAO.listar();
            request.setAttribute("listaCredencial",listaCredencial);
            request.setAttribute("mensagem", "Erro ao tentar alterar senha!!!");
            request.getRequestDispatcher("/sistema/publico/login/alterarSenha.jsp").forward(request, response);
        }
    }    
}
