package br.com.sistema.data;

import br.com.sistema.modelo.Contato;
import br.com.sistema.modelo.Devedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO {

    public static void inserir(Devedor devedor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirtelefone(?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getId());
            pstmt.setString(2,devedor.getContato().get(0).getTipo());
            pstmt.setString(3,devedor.getContato().get(0).getContato());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Contato: " + e.getMessage());
        }
    }
    
    public static void atualizar(Contato contato) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM atualizartelefone(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, contato.getId());
            pstmt.setString(2, contato.getContato());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Atualizar Contato: " + e.getMessage());
        }
    }
    
    public static ArrayList<Contato> listar(Devedor devedor) throws Exception{
        ArrayList<Contato> lista = new ArrayList<Contato>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listartelefones(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, devedor.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Contato contato = new Contato();
                
                contato.setId(Integer.parseInt(resultado.getString("id")));
                contato.setTipo(resultado.getString("tipo"));
                contato.setContato(resultado.getString("numero"));
                
                lista.add(contato);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Contato: " + e.getMessage());
        }
        return lista;
    }

    public static Contato localizarPorNumero(Devedor devedor) throws Exception{
        Contato contato = new Contato();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizartelefonepornumero(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, devedor.getId());
            pstmt.setString(2,devedor.getContato().get(0).getContato().trim());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                contato.setId(resultado.getInt("id"));
                contato.setTipo(resultado.getString("tipo"));
                contato.setContato(resultado.getString("numero"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Contato Pelo Número: " + e.getMessage());
        }
        return contato;
    }
    
    public static boolean contatoJaExiste(Devedor devedor) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizartelefonepornumero(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getId());
            pstmt.setString(2,devedor.getContato().get(0).getContato());

            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                resp = true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Contato Já Existe?: " + e.getMessage());
        }
        return resp;
    }
}
