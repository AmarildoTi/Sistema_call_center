package br.com.sistema.negocio;

import static Util.Funcoes.*;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Parcela;
import com.codigodebarra.Bradesco;
import com.codigodebarra.Compensacao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarneRN {
    
    private final static String logobrad = "c:/PFC/CallCenter/web/imagens/bradesco.tif";
    private final static String logomarca = "c:/PFC/CallCenter/web/imagens/mundo.jpg";

    private static Document documento;
    private static PdfWriter writer;
    
    public static void criar(Divida divida, String file) throws Exception{
        try{
            documento = new Document(new RectangleReadOnly(595,281),42.51968503937f,42.51968503937f,28.34645669291f,28.34645669291f);
            writer = PdfWriter.getInstance(documento,new FileOutputStream(file));
            BaseFont bf1 = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            BaseFont bf2 = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            boolean primeiraPagina = true;
            
            documento.open();
            
            //Capa
            PdfContentByte cb = writer.getDirectContent();
            Image logotipo = Image.getInstance(logomarca);
            logotipo.scaleToFit(50,55);
            documento.add(new Chunk(logotipo, 5, -40));
            posicionarTexto(cb,"CALL CENTER",50,212,0,0,bf2,7);

            posicionarTexto(cb,divida.getCredor().getNomefantasia(),320,225,1,0,bf2,12);
            
            posicionarTexto(cb,"NOME:",50,178,0,0,bf2,10);
            posicionarTexto(cb,"ENDEREÇO:",50,168,0,0,bf2,10);
            posicionarTexto(cb,divida.getDevedor().getNome(),120,178,0,0,bf1,9);
            posicionarTexto(cb,divida.getDevedor().getEndereco().trim()+"  "+divida.getDevedor().getBairro(),120,168,0,0,bf1,9);
            posicionarTexto(cb,divida.getDevedor().getCep()+"  "+divida.getDevedor().getCidade().trim()+"  "+divida.getDevedor().getUf(),120,158,0,0,bf1,9);
            posicionarTexto(cb,"CONTRATO:",50,138,0,0,bf2,10);
            posicionarTexto(cb,divida.getContrato(),120,138,0,0,bf1,9);
            posicionarTexto(cb,"DATA DA DÍVIDA:",240,138,0,0,bf2,10);
            posicionarTexto(cb,divida.getDataString(),330,138,0,0,bf1,9);
            posicionarTexto(cb,"VALOR DA DÍVIDA:",410,138,0,0,bf2,10);
            posicionarTexto(cb,divida.getValorString(),510,138,0,0,bf1,9);
            posicionarTexto(cb,"VALOR DA NEGOCIAÇÃO:",50,125,0,0,bf2,10);
            posicionarTexto(cb,divida.getNegociacao().get(0).getValorNegociadoString(),185,125,0,0,bf1,9);

            String texto;
            texto = "Este documento contém as "+String.valueOf(divida.getNegociacao().get(0).getParcela().size())+" parcelas do acordo firmado com V.Sa., para pagamento de seu débito junto a "+divida.getCredor().getNomefantasia().trim()+".";
            Paragraph paragrafo =  new Paragraph(texto,new Font(Font.FontFamily.HELVETICA,9,Font.NORMAL));

            paragrafo.setMultipliedLeading(1.2f);
            paragrafo.setFirstLineIndent(50f);
            paragrafo.setIndentationLeft(6f);
            paragrafo.setIndentationRight(-6f);
            paragrafo.setAlignment(3);
           
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(paragrafo);
            documento.add(new Paragraph(" "));

            texto = "ATENÇÃO: O não pagamento até a data de vencimento, acarretará no cancelamento do acordo e a inclusão de seu nome no orgão de proteção ao crédito de sua região e no Serasa.";
            paragrafo = new Paragraph(texto,new Font(Font.FontFamily.HELVETICA,9,Font.BOLDITALIC));
            paragrafo.setMultipliedLeading(1.2f);
            paragrafo.setFirstLineIndent(50f);
            paragrafo.setIndentationLeft(6f);
            paragrafo.setIndentationRight(-6f);
            paragrafo.setAlignment(3);

            documento.add(paragrafo);
            
            documento.newPage();
            
            writer.setPageEvent(new PDFBackground());
            
            // Parcelas
            for(Parcela parcela:divida.getNegociacao().get(0).getParcela()){
                if (primeiraPagina){
                    primeiraPagina = false;
                }else{
                    documento.newPage();
                }
                
                long nnumero = Long.parseLong(String.format("%02d", parcela.getNumero())+String.format("%09d",divida.getNegociacao().get(0).getId()));
                
                Compensacao fichaCompensacao = new Bradesco(6, 0231,"0", 67773,"6",nnumero,parcela.getValor(),parcela.getDataVencimento());
                fichaCompensacao.setDataDocumento(new Date());
                fichaCompensacao.setDataProcessamento(new Date());
                fichaCompensacao.setLocalPagamento("PAGÁVEL EM QUALQUE BANCO ATÉ O VENCIMENTO");
                fichaCompensacao.setNumeroDocumento(divida.getContrato());
                
                BarcodeInter25 codbarra = new BarcodeInter25();
                cb = writer.getDirectContent();
                codbarra.setCode(fichaCompensacao.getCodigoDeBarras());
                codbarra.setBarHeight(36);
                Image imageCodBarra = codbarra.createImageWithBarcode(cb, null,BaseColor.WHITE);
                logotipo = Image.getInstance(logobrad);
                logotipo.scaleToFit(120,15);
                documento.add(new Chunk(imageCodBarra, 135, -242));
                documento.add(new Chunk(logotipo, -138, 7));
                documento.add(new Chunk(logotipo, -317, -2));
                
                posicionarTexto(cb,fichaCompensacao.getLinhaDigitavel(),432,252,1,0,bf2,10.2f);
                posicionarTexto(cb,fichaCompensacao.getBanco()+"-"+fichaCompensacao.getDvBanco(),256,250,1,0,bf2,10.5f);
                posicionarTexto(cb,fichaCompensacao.getLocalPagamento(),156,232,0,0,bf2,10);
                posicionarTexto(cb,"PARCELA "+String.format("%02d", parcela.getNumero())+"/"+String.format("%02d", divida.getNegociacao().get(0).getParcela().size()),90,93,1,0,bf2,10);
                posicionarTexto(cb,"PARCELA "+String.format("%02d", parcela.getNumero())+"/"+String.format("%02d", divida.getNegociacao().get(0).getParcela().size()),320,160,1,0,bf2,12);
                posicionarTexto(cb,parcela.getDataVencimentoString(),580,232,2,0,bf2,9);
                posicionarTexto(cb,parcela.getDataVencimentoString(),92,158,1,0,bf2,10);
                posicionarTexto(cb,divida.getCredor().getRazaosocial(),156,214,0,0,bf2,10);
                posicionarTexto(cb,fichaCompensacao.getAgenciaCodigoCedente(),580,214,2,0,bf1,9);
                posicionarTexto(cb,formatDate(fichaCompensacao.getDataDocumento()),186,197,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getNumeroDocumento(),285,197,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getNumeroDocumento(),92,198,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getEspecieDocumento(),360,197,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getAceite(),400,197,1,0,bf1,9);
                posicionarTexto(cb,formatDate(fichaCompensacao.getDataProcessamento()),450,197,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getNossoNumero(),580,197,2,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getNossoNumero(),92,178,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getUsoBanco(),190,180,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getCarteira(),265,180,1,0,bf1,9);
                posicionarTexto(cb,fichaCompensacao.getEspecieMoeda(),320,180,1,0,bf1,9);
                posicionarTexto(cb,NumberFormat.getCurrencyInstance().format(fichaCompensacao.getValorDocumento()),580,180,2,0,bf2,9);
                posicionarTexto(cb,NumberFormat.getCurrencyInstance().format(fichaCompensacao.getValorDocumento()),135,139,2,0,bf2,9);

                posicionarTexto(cb,"NÃO RECEBER APÓS O VENCIMENTO",160,90,0,0,bf2,9);

                posicionarTexto(cb,divida.getDevedor().getNome(),45,70,0,0,bf1,6);
                posicionarTexto(cb,divida.getDevedor().getNome(),180,77,0,0,bf1,9);
                posicionarTexto(cb,divida.getDevedor().getEndereco().trim()+"  "+divida.getDevedor().getBairro(),180,68,0,0,bf1,9);
                posicionarTexto(cb,divida.getDevedor().getCep()+"  "+divida.getDevedor().getCidade().trim()+"  "+divida.getDevedor().getUf(),180,59,0,0,bf1,9);
            }
            documento.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            documento.close();
            writer.close();
            throw new Exception(e.getMessage());
        } catch (DocumentException e) {
            documento.close();
            writer.close();
            throw new Exception(e.getMessage());
        }
    }
    
    private static void posicionarTexto(PdfContentByte cb, String texto, int posicaoX, int posicaoY, int alinhamento, float rotacao, BaseFont fonte, float tamanho) throws Exception {
        try{
            cb.saveState();
            cb.beginText();
            cb.setFontAndSize(fonte, tamanho);
            cb.showTextAligned(alinhamento, texto, posicaoX, posicaoY, rotacao);
            cb.endText();
            cb.restoreState();
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }    
}

class PDFBackground extends PdfPageEventHelper {
    private final static String imagemBoleto = "c:/PFC/CallCenter/web/imagens/ficha.jpg";

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try{
            Image background = Image.getInstance(imagemBoleto);
            // This scales the image to the page,
            // use the image's width & height if you don't want to scale.
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            writer.getDirectContentUnder().addImage(background, width, 0, 0, height, 0, 0);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBackground.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFBackground.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
