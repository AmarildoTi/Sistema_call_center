package br.com.sistema.logica;

import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CredorExcluir implements  Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                Credor credor = new Credor(Integer.parseInt(request.getParameter("id")));
                CredorDAO.excluir(credor);
                LogDAO.inserir(new Log(login,"Excluiu o cadastro do credor: "+credor.getNomefantasia()));
                
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("mensagem","Credor excluído com sucesso!!!");
                request.getRequestDispatcher("/sistema/gerente/credor/credor.jsp").forward(request, response);               
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
            request.setAttribute("listaCredor",listaCredor);
            request.setAttribute("mensagem","Erro ao tentar excluir Credor!!!");
            request.getRequestDispatcher("/sistema/gerente/credor/credor.jsp").forward(request, response);               
        }
    }
}