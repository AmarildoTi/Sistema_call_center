/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class PerfilNegociacaoAlterar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                int id = Integer.parseInt(request.getParameter("id"));
                int idcredor = Integer.parseInt(request.getParameter("idcredor").trim());
                Credor credor = new Credor(idcredor);
                int atrasode =  Integer.parseInt(request.getParameter("atrasode").trim());
                int atrasoate =  Integer.parseInt(request.getParameter("atrasoate").trim());
                int maximoparcelas =  Integer.parseInt(request.getParameter("maximoparcelas").trim());
                double juros =  Double.parseDouble(request.getParameter("juros").replace(",",".").replace("%","").trim());
                double multa =  Double.parseDouble(request.getParameter("multa").replace(",",".").replace("%","").trim());
                double jurosporparcela =  Double.parseDouble(request.getParameter("jurosporparcela").replace(",",".").replace("%","").trim());
                double descontovalorprincipal =  Double.parseDouble(request.getParameter("descontovalorprincipal").replace(",",".").replace("%","").trim());
                double descontoencargos =  Double.parseDouble(request.getParameter("descontoencargos").replace(",",".").replace("%","").trim());
                double valorminimoentrada =  Double.parseDouble(request.getParameter("valorminimoentrada").replace(",",".").replace("R$","").trim());
                double valorminimoparcela =  Double.parseDouble(request.getParameter("valorminimoparcela").replace(",",".").replace("R$","").trim());
                String tipo =  request.getParameter("tipo");
                ArrayList<PerfilNegociacao> listaPerfil = new ArrayList<PerfilNegociacao>();
                PerfilNegociacao perfil = new PerfilNegociacao();
                perfil.setId(id);
                perfil.setAtrasoDe(atrasode);
                perfil.setAtrasoAte(atrasoate);
                perfil.setMaximoParcelas(maximoparcelas);
                perfil.setJuros(juros);
                perfil.setMulta(multa);
                perfil.setJurosPorParcela(jurosporparcela);
                perfil.setDescontoValorPrincipal(descontovalorprincipal);
                perfil.setDescontoEncargos(descontoencargos);
                perfil.setValorMinimoEntrada(valorminimoentrada);
                perfil.setValorMinimoParcela(valorminimoparcela);
                perfil.setTipo(tipo);
                listaPerfil.add(perfil);
                credor.setPerfilNegociacao(listaPerfil);
                if (PerfilNegociacaoDAO.diasAtrasoJaExiste(credor)){
                    request.setAttribute("mensagem","Já existe um perfil para o dia em atraso informado!!!");
                }else{
                    PerfilNegociacaoDAO.atualizar(perfil);
                    LogDAO.inserir(new Log(login,"Alterou o cadastro do perfil de negociação: "+perfil.getId()));
                    request.setAttribute("mensagem","Perfil de Negociação alterado com sucesso!!!");
                }
                credor = CredorDAO.localizarComPerfil(new Credor(idcredor));
                String mensagem2 = PerfilNegociacaoRN.verificarPerfil(credor);
                request.setAttribute("credor",credor);
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                request.setAttribute("idcredor", request.getParameter("idcredor"));
                request.setAttribute("indexcredor", request.getParameter("indexcredor"));
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
            request.setAttribute("mensagem","Erro ao tentar alterar um Perfil de Negociaçãoo!!!");
            request.getRequestDispatcher("/sistema/gerente/credor/perfilnegociacao.jsp").forward(request, response);
        }
    }
}
