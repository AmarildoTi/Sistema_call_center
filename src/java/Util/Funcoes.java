package Util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import org.apache.commons.codec.binary.Base64;

public class Funcoes {
    
    private final static SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
    private final static SimpleDateFormat formatoDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String formatDate(Date data){
        return formatoDate.format(data);
    }
    
    public static String formatDateTime(Date data){
        return formatoDateTime.format(data);
    }
    
    public static String formataDataSQL(String data){
        String datasql = data.substring(6,10) +  data.substring(2,6) + data.substring(0,2);
        return datasql;
    }
    
    public static long dateDiff(Date datainicio, Date datafinal) throws ParseException{
        String d1 = formatoDate.format(datainicio);
        String d2 = formatoDate.format(datafinal);
        return (formatoDate.parse(d2).getTime()- formatoDate.parse(d1).getTime()) / 86400000L;
    }
    
    public static double arredondamento(double valor,int casasDecimais){
        BigDecimal bd = new BigDecimal(valor);
        bd = bd.setScale(casasDecimais, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    public static Date toDate(String data) throws ParseException{
        return formatoDate.parse(data);
    }
    
    public static Date toDateTime(String data) throws ParseException{
        return formatoDateTime.parse(data);
    }
    
    public static String formatString(String string, String mask)
            throws ParseException {
        MaskFormatter mf = new MaskFormatter(mask);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(string);
    }
    
    public static String retirarMask(String string, String mask)
            throws ParseException {
        MaskFormatter mf = new MaskFormatter(mask);
        mf.setValueContainsLiteralCharacters(false);
        return mf.stringToValue(string).toString();
    }

    public static String criptografa(String senha) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(senha.getBytes());
        Base64 encoder = new Base64 ();
        return encoder.encodeAsString(digest.digest ());
    }
}
