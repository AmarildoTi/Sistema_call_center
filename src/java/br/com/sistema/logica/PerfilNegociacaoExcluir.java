package br.com.sistema.logica;

import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.PerfilNegociacaoDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.PerfilNegociacao;
import br.com.sistema.negocio.PerfilNegociacaoRN;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PerfilNegociacaoExcluir implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                int idcredor = Integer.parseInt(request.getParameter("idcredor").trim());
                PerfilNegociacaoDAO.excluir(new PerfilNegociacao(Integer.parseInt(request.getParameter("id"))));
                LogDAO.inserir(new Log(login,"Excluiu o cadastro de perfil de negociação: "+request.getParameter("id")));
                
                Credor credor = CredorDAO.localizarComPerfil(new Credor(idcredor));
                String mensagem2 = PerfilNegociacaoRN.verificarPerfil(credor);
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("credor", credor);
                request.setAttribute("idcredor", idcredor);
                request.setAttribute("indexcredor", request.getParameter("indexcredor"));
                request.setAttribute("mensagem","Perfil de Negociação excluído com sucesso!!!");
                request.setAttribute("mensagem2",mensagem2);
                request.getRequestDispatcher("/sistema/gerente/credor/perfilnegociacao.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }        
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            Credor credor = CredorDAO.localizarComPerfil(new Credor(Integer.parseInt(request.getParameter("idcredor"))));
            request.setAttribute("credor",credor);
            ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
            request.setAttribute("listaCredor",listaCredor);
            request.setAttribute("idcredor", request.getParameter("idcredor"));
            request.setAttribute("indexcredor", request.getParameter("indexcredor"));
            request.setAttribute("mensagem","Erro ao tentar excluir um Perfil de Negociação!!!");            
            request.getRequestDispatcher("/sistema/gerente/credor/perfilnegociacao.jsp").forward(request, response);
        }
    }
}