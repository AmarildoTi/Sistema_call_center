package br.com.sistema.logica;

import br.com.sistema.data.AtendimentoDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtendimentoInserir implements Logica{
    
    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Atendimento atendimento = (Atendimento) request.getSession().getAttribute("atendimento");
                atendimento.setDescricao(request.getParameter("descricao"));
                atendimento.getStatus().setId(Integer.parseInt(request.getParameter("idstatus")));
                AtendimentoDAO.inserir(atendimento);
                request.setAttribute("mensagem", "Atendimento cadastrado com sucesso!!!");
                request.getSession().removeAttribute("atendimento");
                request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
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
