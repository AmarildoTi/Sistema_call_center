package br.com.sistema.logica;

import Util.Funcoes;
import Util.Relatorio;
import br.com.sistema.data.CredorDAO;
import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Credor;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RelatorioExibir implements Logica{
    
    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            String relatorio = "C://PFC/CallCenter/";
            ArrayList lista =null;
            Map<String, Object> parametros = new HashMap<String, Object>();
            int opcao = Integer.parseInt(request.getParameter("relatorio"));
            Date periodoInicial;
            Date periodoFinal;
            Credor credor;
            switch (opcao){
                case 0:
                    relatorio += "relatorios/relatorioUsuario.jrxml";
                    lista = UsuarioDAO.listar();
                    break;
                case 1:
                    relatorio += "relatorios/relatorioCredor.jrxml";
                    lista = CredorDAO.listarSemPerfil();
                    break;
                case 2:
                    relatorio += "relatorios/relatorioPerfilNegociacao.jrxml";
                    credor = CredorDAO.localizarComPerfil(new Credor(Integer.parseInt(request.getParameter("idcredor"))));
                    lista = credor.getPerfilNegociacao();
                    parametros.put("CREDOR",credor.getRazaosocial());
                    break;
                case 3:
                    relatorio += "relatorios/relatorioDividaPorPerfilNegociacao.jrxml";
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    break;
                case 4:
                    relatorio += "relatorios/relatorioAcordoAVencer.jrxml";
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    break;
                case 5:
                    relatorio += "relatorios/relatorioAcordoQuebrado.jrxml";
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    break;
                case 6:
                    relatorio += "relatorios/relatorioDividaQuitada.jrxml";
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    break;
                case 7:
                    relatorio += "relatorios/relatorioNegociacaoPorOperadorSintetico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
                case 8:
                    relatorio += "relatorios/relatorioNegociacaoPorOperadorAnalitico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
                case 9:
                    relatorio += "relatorios/relatorioAcordoPorOperadorSintetico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
                case 10:
                    relatorio += "relatorios/relatorioAcordoPorOperadorAnalitico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
                case 11:
                    relatorio += "relatorios/relatorioPromessaPorOperadorSintetico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
                case 12:
                    relatorio += "relatorios/relatorioPromessaPorOperadorAnalitico.jrxml";
                    periodoInicial = Funcoes.toDate(request.getParameter("periodoinicial"));
                    periodoFinal = Funcoes.toDate(request.getParameter("periodofinal"));
                    parametros.put("CREDOR",Integer.parseInt(request.getParameter("idcredor")));
                    parametros.put("PERIODO_INICIAL", periodoInicial);
                    parametros.put("PERIODO_FINAL", periodoFinal);
                    break;
            }
            response.setContentType("application/pdf");
            Relatorio.criarPDF(relatorio, parametros, lista, response);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
