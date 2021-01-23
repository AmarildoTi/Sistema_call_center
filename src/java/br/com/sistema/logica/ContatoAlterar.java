package br.com.sistema.logica;

import br.com.sistema.data.ContatoDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Contato;
import br.com.sistema.modelo.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContatoAlterar implements Logica{
    
    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Atendimento atendimento = (Atendimento) request.getSession().getAttribute("atendimento");
                String contatonovo = request.getParameter("contatonovo");
                int numcontato = Integer.parseInt(request.getParameter("contato"));
                Contato contato = atendimento.getDivida().getDevedor().getContato().get(numcontato);
                contato.setContato(contatonovo);
                ContatoDAO.atualizar(contato);
                atendimento.getDivida().getDevedor().setContato(ContatoDAO.listar(atendimento.getDivida().getDevedor()));
                request.getSession().removeAttribute("atendimento");
                request.getSession().setAttribute("atendimento",atendimento);
                request.setAttribute("indexstatus",0);
                request.setAttribute("valorentrada",0.00);
                request.setAttribute("aba",0);
                request.setAttribute("mensagem","Contato atualizado com sucesso!!!");
                request.getRequestDispatcher("/sistema/operador/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar cadastrar o atendimento!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }  
    
}
