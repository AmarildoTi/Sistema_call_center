package br.com.sistema.logica;

import br.com.sistema.data.UsuarioDAO;
import br.com.sistema.modelo.Usuario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioExportar implements Logica{

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            ArrayList<Usuario> listaUsuario = UsuarioDAO.listar();
            BufferedWriter arquivo;
            arquivo = new BufferedWriter(new FileWriter("C:/usuario.xml"));
            arquivo.write("<?xml version='1.0' encoding='ISO-8859-1' ?> ");
            arquivo.write(System.getProperty("line.separator"));
            arquivo.write("<callcenter>");
            arquivo.write(System.getProperty("line.separator"));
            for (Usuario usuario : listaUsuario){
                arquivo.write("<usuario id='" + usuario.getId() + "'>");
                arquivo.write(System.getProperty("line.separator"));
                arquivo.write(" <cpf>" + usuario.getCpf() + "</cpf>");
                arquivo.write(System.getProperty("line.separator"));
                arquivo.write(" <nome>" + usuario.getNome() + "</nome>");
                arquivo.write(System.getProperty("line.separator"));
                arquivo.write(" <usuario>" + usuario.getUsuario() + "</usuario>");
                arquivo.write(System.getProperty("line.separator"));
                arquivo.write(" <tipo>" + usuario.getTipo() + "</tipo>");
                arquivo.write(System.getProperty("line.separator"));
                arquivo.write("</usuario>");
                arquivo.write(System.getProperty("line.separator"));
            }
            // finaliza o arquivo XML.
            arquivo.write("</callcenter>");
            arquivo.write(System.getProperty("line.separator"));
            arquivo.flush();
            arquivo.close();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
}
