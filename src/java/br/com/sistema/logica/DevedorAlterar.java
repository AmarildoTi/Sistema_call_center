package br.com.sistema.logica;

import br.com.sistema.data.DevedorDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Devedor;
import br.com.sistema.modelo.Login;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DevedorAlterar implements Logica{
    
    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Atendimento atendimento = (Atendimento) request.getSession().getAttribute("atendimento");
                Devedor devedor = new Devedor(atendimento.getDivida().getDevedor().getId());
                devedor.setEndereco(request.getParameter("endereco"));
                devedor.setComplemento(request.getParameter("complemento"));
                devedor.setBairro(request.getParameter("bairro"));
                devedor.setCidade(request.getParameter("cidade"));
                devedor.setUf(request.getParameter("uf"));
                devedor.setCep(request.getParameter("cep"));
                DevedorDAO.atualizar(devedor);
                atendimento.getDivida().setDevedor(DevedorDAO.localizar(devedor));
                request.getSession().setAttribute("atendimento",atendimento);
                request.setAttribute("indexstatus",0);
                request.setAttribute("valorentrada",0.00);
                request.setAttribute("aba",0);
                request.setAttribute("mensagem","Endereço atualizado com sucesso!!!");
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
