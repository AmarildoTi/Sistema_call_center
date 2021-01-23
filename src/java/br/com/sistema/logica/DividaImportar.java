package br.com.sistema.logica;

import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.negocio.DividaRN;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DividaImportar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                int credor = Integer.parseInt(request.getParameter("id3"));
                String arquivo = request.getParameter("arquivo");
                DividaRN.importar(new Divida(credor, arquivo));
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("id3", request.getParameter("id3"));
                request.setAttribute("indexcredor3", request.getParameter("indexcredor3"));
                LogDAO.inserir(new Log(login,"Importou o arquivo ("+arquivo+") com as dívidas do credor "+String.valueOf(credor)));
                request.setAttribute("mensagem","Arquivo Importado com sucesso!!!");
                request.getRequestDispatcher("/sistema/gerente/divida/importar.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            request.setAttribute("mensagem","Erro ao tentar importar o arquivo!!!");
            request.getRequestDispatcher("/sistema/publico/login/home.jsp").forward(request, response);
        }
    }
    
}
