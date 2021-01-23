package br.com.sistema.negocio;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailRN {
    
    private final static String host = "smtp.gmail.com";
    private final static String usuario = "callcenterrecuperacaodecredito@gmail.com";
    private final static String senha = "callcenter03";
    
    public static boolean enviar(String destinatario, String arquivo) throws Exception{
        boolean resp = false;
        try{
            HtmlEmail email = new HtmlEmail();
            email.setSSLOnConnect(true);
            email.setHostName(host);
            email.setSslSmtpPort("465");
            email.setAuthenticator(new DefaultAuthenticator(usuario,senha));

            email.setFrom(usuario,"CALL CENTER");
            email.setDebug(true);
            email.setSubject("Quite sua dívida!!!");
            
            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(arquivo);
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setName("Negociação.pdf");
            
            email.attach(anexo);
            
            email.addTo(destinatario);
            email.setMsg("Em anexo segue o documento com o(s) boleto(s) para quitar sua dívida.");
            email.send();
        }
        catch(EmailException e){
            throw new Exception(e.getMessage());
        }
        return resp;
    }
}
