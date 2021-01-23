package Util;

import br.com.sistema.data.ConectaBanco;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Relatorio {
     
    public static void criarPDF(String jRelatorio, Map<String,Object> parametros, ArrayList lista, HttpServletResponse response) throws IOException, JRException, Exception{
        
        JasperDesign jDesign = JRXmlLoader.load(jRelatorio);
        JasperReport jReport = JasperCompileManager.compileReport(jDesign);
        JasperPrint jPrint;
        if (lista == null){
            jPrint = JasperFillManager.fillReport(jReport, parametros, ConectaBanco.getInstance().getConexao());
        }else{    
            JRDataSource ds = new JRBeanCollectionDataSource(lista);
            jPrint = JasperFillManager.fillReport(jReport, parametros, ds);
        }
        
        JasperExportManager.exportReportToPdfStream(jPrint,response.getOutputStream());
    }
}
