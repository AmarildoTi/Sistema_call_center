package br.com.sistema.negocio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactadorRN {

    static final int TAMANHO_BUFFER = 4096;
    public static void compactarParaZip(String arquivoZIP, String arquivo) throws IOException{
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];
        
        BufferedInputStream origem;
        FileInputStream streamDeEntrada;
        FileOutputStream destino;
        ZipOutputStream saida;
        ZipEntry entry;
        try{
            destino = new FileOutputStream(new File(arquivoZIP));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(arquivo);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada,TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);
            while((cont = origem.read(dados,0,TAMANHO_BUFFER)) != -1){
                saida.write(dados,0,cont);
            }
            origem.close();
            saida.close();
        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }
}
