package br.com.sistema.logica;

import Util.Funcoes;
import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CredorAlterar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {            
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                String razaosocial = request.getParameter("razaosocial");
                int id = Integer.parseInt(request.getParameter("id"));
                String nomefantasia = request.getParameter("nomefantasia");
                String endereco = request.getParameter("endereco");
                String complemento = request.getParameter("complemento");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String cep = Funcoes.retirarMask(request.getParameter("cep"),"#####-###");
                String uf = request.getParameter("uf");
            
                Credor credor = new Credor(id);
                credor.setRazaosocial(razaosocial);
                credor.setNomefantasia(nomefantasia);
                credor.setEndereco(endereco);
                credor.setComplemento(complemento);
                credor.setBairro(bairro);
                credor.setCidade(cidade);
                credor.setCep(cep);
                credor.setUf(uf);
                
                CredorDAO.atualizar(credor);
                LogDAO.inserir(new Log(login,"Alterou o cadastro do credor: "+credor.getNomefantasia()));
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("mensagem","Credor alterado com sucesso!!!");
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
            request.setAttribute("mensagem","Erro ao tentar alterar o credor!!!");
            request.getRequestDispatcher("/sistema/gerente/credor/credor.jsp").forward(request, response);
        }
    }
    
}
