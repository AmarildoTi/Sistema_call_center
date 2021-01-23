package br.com.sistema.data;

import br.com.sistema.modelo.Log;
import java.sql.CallableStatement;
import java.sql.Connection;

public class LogDAO {

        public static void inserir(Log log) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirlog(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,log.getCredencial().getId());
            pstmt.setString(2,log.getAcao());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Log: " + e.getMessage());
        }
    }
}
