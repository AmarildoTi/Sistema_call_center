
package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioEfetuarLogin implements Logica {

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        //String naFigura = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        try{
            Usuario usuario = new Usuario(request.getParameter("usuario"),Funcoes.criptografa(request.getParameter("senha")));
            usuario = UsuarioDAO.efetuarLogin(usuario);
            //String digitado = request.getParameter("kaptcha");
            if (usuario.getId() != 0){
                if (usuario.getStatus().equals("Ativo")){
                    //if (digitado.equals(naFigura)){
                    Login login = new Login();
                    login.setId(usuario.getId());
                    login.setNome(usuario.getNome());
                    login.setTipo(usuario.getTipo());
                    login.setUsuario(usuario.getUsuario());
                    session.setAttribute("login", login);
                    request.setAttribute("mensagem", "");
                    LogDAO.inserir(new Log(usuario,"Efetuou login"));
                    request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
                //}else{
                //    request.setAttribute("mensagem", "Texto digitado não confere com o texto da imagem!!!");
                //    request.setAttribute("usuario",request.getParameter("usuario"));
                //    request.setAttribute("senha",request.getParameter("senha"));
                //    request.getRequestDispatcher("/index.jsp").forward(request, response);
                //}
                }else{
                    request.setAttribute("mensagem", "Usuário Inativo!!!");
                request.setAttribute("usuario",request.getParameter("usuario"));
                request.setAttribute("senha","");
                request.getRequestDispatcher("/index.jsp").forward(request, response);                
                }
            }else{
                request.setAttribute("mensagem", "Usuário ou Senha inválido!!!");
                request.setAttribute("usuario",request.getParameter("usuario"));
                request.setAttribute("senha","");
                request.getRequestDispatcher("/index.jsp").forward(request, response);                
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem", "Erro ao tentar efetuar login!!!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
}
