package br.com.sistema.logica;

import br.com.sistema.data.CredencialDAO;
import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.LogDAO;
import br.com.sistema.data.SegmentacaoDAO;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Log;
import br.com.sistema.modelo.Login;
import br.com.sistema.modelo.Segmentacao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SegmentacaoAlterar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                int id2 = Integer.parseInt(request.getParameter("id2").trim());
                int credor2 = Integer.parseInt(request.getParameter("idcredor2").trim());
                int operador2 = Integer.parseInt(request.getParameter("idoperador2").trim());
                int atrasode =  Integer.parseInt(request.getParameter("atrasode").trim());
                int atrasoate =  Integer.parseInt(request.getParameter("atrasoate").trim());
                double valordividade =  Double.parseDouble(request.getParameter("valordividade").replace("R$","").replace(".","").replace(",",".").trim());
                double valordividaate =  Double.parseDouble(request.getParameter("valordividaate").replace("R$","").replace(".","").replace(",",".").trim());
                
                Segmentacao segmentacao = new Segmentacao(credor2,operador2);
                segmentacao.setId(id2);
                segmentacao.setAtrasoDe(atrasode);
                segmentacao.setAtrasoAte(atrasoate);
                segmentacao.setValorDividaDe(valordividade);
                segmentacao.setValorDividaAte(valordividaate);
                
                SegmentacaoDAO.atualizar(segmentacao);
                LogDAO.inserir(new Log(login,"Alterou o cadastro de segmentação do atendimento: "+segmentacao.getId()));
                
                ArrayList<Segmentacao> listaSegmentacao = SegmentacaoDAO.listar(segmentacao);
                request.setAttribute("listaSegmentacao",listaSegmentacao);
                ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
                request.setAttribute("listaCredor",listaCredor);
                ArrayList<Credencial> listaCredencial = CredencialDAO.listar();
                request.setAttribute("listaCredencial",listaCredencial);
                request.setAttribute("idcredor2", credor2);
                request.setAttribute("indexcredor2", request.getParameter("indexcredor2"));
                request.setAttribute("idoperador2", operador2);
                request.setAttribute("indexoperador2", request.getParameter("indexoperador2"));
                request.setAttribute("mensagem","Segmentação de Atendimento inserido com sucesso!!!");
                request.getRequestDispatcher("/sistema/supervisor/segmentacao/segmentacao.jsp").forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }        
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            ArrayList<Segmentacao> listaSegmentacao = SegmentacaoDAO.listar(new Segmentacao(Integer.parseInt(request.getParameter("idcredor2")),Integer.parseInt(request.getParameter("idoperador2"))));
            request.setAttribute("listaSegmentacao",listaSegmentacao);
            ArrayList<Credor> listaCredor = CredorDAO.listarSemPerfil();
            request.setAttribute("listaCredor",listaCredor);
            ArrayList<Credencial> listaCredencial = CredencialDAO.listar();
            request.setAttribute("listaCredencial",listaCredencial);
            request.setAttribute("idcredor2", request.getParameter("idcredor2"));
            request.setAttribute("indexcredor2", request.getParameter("indexcredor2"));
            request.setAttribute("idoperador2", request.getParameter("idoperador2"));
            request.setAttribute("indexoperador2", request.getParameter("indexoperador2"));
            request.setAttribute("mensagem","Erro ao tentar alterar uma Segmentação de Atendimento!!!");
            request.getRequestDispatcher("/sistema/supervisor/segmentacao/segmentacao.jsp").forward(request, response);
        }
    }

}
