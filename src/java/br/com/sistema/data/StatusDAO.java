package br.com.sistema.data;

import br.com.sistema.modelo.Status;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatusDAO {

    public static ArrayList<Status> listar() throws Exception{
        ArrayList<Status> listaStatus = new ArrayList<Status>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarstatusatendimento()";
            CallableStatement pstmt = conexao.prepareCall(sql);
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Status status = new Status();
                
                status.setId(resultado.getInt("id"));
                status.setDescricao(resultado.getString("descricao"));
                
                listaStatus.add(status);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Status Atendimento: " + e.getMessage());
        }
        return listaStatus;
    }
    
    public static Status localizar(Status status) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarstatusatendimento(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            pstmt.setInt(1,status.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                status.setId(resultado.getInt("id"));
                status.setDescricao(resultado.getString("descricao"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Status Atendimento: " + e.getMessage());
        }
        return status;
    }
    
}
