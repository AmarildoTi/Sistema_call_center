package br.com.sistema.data;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {
    
    private final String url = "jdbc:postgresql://127.0.0.1:5432/CallCenter";
    private final String usuario = "usuario";
    private final String senha = "usuario";
    private static ConectaBanco instance;

    private ConectaBanco() throws Exception{
        try{
        //driver que serÃ¡ utilizado
        Class.forName("org.postgresql.Driver");
        //cria um objeto de conexao com um banco especificado no caminho...
        }
        catch (ClassNotFoundException e){
            throw new Exception("Conexão: " + e.getMessage());
        }
    }
    
    public static ConectaBanco getInstance() throws Exception{
        if (instance == null){
            instance = new ConectaBanco();
        }
        return instance;
    }
    
    public Connection getConexao() throws SQLException{
        return DriverManager.getConnection(url,usuario, senha);
    }
    
}