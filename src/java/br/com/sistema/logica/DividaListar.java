package br.com.sistema.logica;

import br.com.sistema.data.DividaDAO;
import br.com.sistema.modelo.Devedor;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Login;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DividaListar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int opcao = 0;
        String texto = "";
        String texto2 = "";
        int indexcredor = 0;
        if (request.getSession().getAttribute("listaDivida") != null){
            request.getSession().removeAttribute("listaDivida");
        }
        try{
            Login login = (Login) request.getSession().getAttribute("login");
            if( login != null){
                ArrayList<Divida> listaDivida = new ArrayList<Divida>();
                request.setAttribute("mensagem","");
                if (request.getParameter("texto") != null){
                    texto = request.getParameter("texto") ;
                }
                if (request.getParameter("texto2") != null && !request.getParameter("texto2").equals("___.___.___-__")){
                    texto2 = request.getParameter("texto2") ;
                }
                if (request.getParameter("opcao") != null && !"".equals(request.getParameter("opcao"))){
                    opcao = Integer.parseInt(request.getParameter("opcao"));
                    switch (opcao){
                        case 1:
                            texto = texto.trim().replaceAll("\\.", "").replaceAll("\\_", "").replace("-","");
                            texto2="";
                            listaDivida = DividaDAO.listarCPF(new Devedor(Long.parseLong(texto)));
                            if (listaDivida.isEmpty()){
                                request.setAttribute("mensagem","Não há dívida para esse CPF!!!");
                            }
                            request.getSession().setAttribute("listaDivida",listaDivida);
                            break;
                        case 2:
                            listaDivida = DividaDAO.listarNome(new Devedor("%"+texto.trim()+"%"));
                            texto2="";
                            if (listaDivida.isEmpty()){
                                request.setAttribute("mensagem","Não há dívida para esse Nome!!!");
                            }
                            request.getSession().setAttribute("listaDivida",listaDivida);
                            break;
                        case 3:
                            listaDivida = DividaDAO.listarContrato(new Divida("%"+texto.trim()+"%"));
                            texto2="";
                            if (listaDivida.isEmpty()){
                                request.setAttribute("mensagem","Não há dívida para esse Contrato!!!");
                            }
                            request.getSession().setAttribute("listaDivida",listaDivida);
                            break;
                        case 4:
                            texto2 = texto2.trim().replaceAll("\\.", "").replaceAll("\\_", "").replace("-","");
                            texto="";
                            request.getSession().removeAttribute("listaDivida");
                            listaDivida = DividaDAO.listarNegociadaPorCPF(new Devedor(Long.parseLong(texto2.trim())));
                            if (listaDivida.isEmpty()){
                                request.setAttribute("mensagem","Não há negociação para esse CPF!!!");
                            }
                            request.setAttribute("listaDivida2",listaDivida);
                            break;
                    }
                }
                request.setAttribute("opcao",opcao);
                request.setAttribute("texto", texto);
                request.setAttribute("texto2", texto2);
                request.setAttribute("indexcredor", indexcredor);
                request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
            }else{
                request.setAttribute("mensagem", "Favor efetuar login!!!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            //throw new Exception(e.getMessage());
            request.setAttribute("mensagem","Erro ao tentar listar as dívidas!!!");
            ArrayList<Divida> listaDivida = new ArrayList<Divida>();
            request.setAttribute("listaDivida",listaDivida);
            request.setAttribute("opcao",opcao);
            request.setAttribute("texto", texto);
            request.setAttribute("indexcredor", indexcredor);
            request.getRequestDispatcher("/sistema/"+request.getParameter("jsp")).forward(request, response);
        }
    }
    
}
