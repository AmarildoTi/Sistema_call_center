package br.com.sistema.logica;

import br.com.sistema.data.CredorDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Login;
import br.com.sistema.negocio.PerfilNegociacaoRN;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PerfilNegociacaoListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            int idcredor = 1;
            String indexcredor = "0";
            if (request.getParameter("idcredor") != null){
                idcredor =Integer.parseInt(request.getParameter("idcredor"));
            }
            if (request.getParameter("indexcredor") != null){
                indexcredor = request.getParameter("indexcredor");
            }
            Credor credor = CredorDAO.localizarComPerfil(new Credor(idcredor));
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                String mensagem2 = PerfilNegociacaoRN.verificarPerfil(credor);
                request.setAttribute("credor",credor);
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("idcredor", idcredor);
                request.setAttribute("indexcredor", indexcredor);
                request.setAttribute("mensagem","");
                request.setAttribute("mensagem2",mensagem2);
                request.getRequestDispatcher("/sistema/gerente/credor/perfilnegociacao.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }        
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar os Perfis de Negociação!!!");            
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
    
}
