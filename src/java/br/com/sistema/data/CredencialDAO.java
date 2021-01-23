package br.com.sistema.data;

import br.com.sistema.modelo.Credencial;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CredencialDAO {
    
    public static Credencial localizarPorID(Credencial credencial) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarusuarioporid(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,credencial.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                credencial.setId(resultado.getInt("id"));
                credencial.setNome(resultado.getString("nome"));
                credencial.setUsuario(resultado.getString("usuario"));
                credencial.setTipo(resultado.getString("tipo"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar por ID Credencial: " + e.getMessage());
        }
        return credencial;
    }
    
    public static ArrayList<Credencial> listar() throws Exception{
        ArrayList<Credencial> listaCredencial = new ArrayList<Credencial>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarusuarios()";
            CallableStatement pstmt = conexao.prepareCall(sql);
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Credencial credencial = new Credencial(resultado.getInt("id"));
                credencial.setNome(resultado.getString("nome"));
                credencial.setUsuario(resultado.getString("usuario"));
                credencial.setTipo(resultado.getString("tipo"));
                
                listaCredencial.add(credencial);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Credencial: " + e.getMessage());
        }
        return listaCredencial;
    }
}
