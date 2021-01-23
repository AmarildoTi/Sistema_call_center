package br.com.sistema.data;

import br.com.sistema.modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {
    
    public static void inserir(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirusuario(?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,usuario.getCpf());
            pstmt.setString(2,usuario.getNome());
            pstmt.setString(3,usuario.getUsuario());
            pstmt.setString(4,usuario.getSenha());
            pstmt.setString(5,usuario.getTipo());
            pstmt.setString(6,usuario.getStatus());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Usuário: " + e.getMessage());
        }
    }
    
    public static void atualizar(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT atualizarusuario(?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);

            pstmt.setLong(1,usuario.getId());
            pstmt.setString(2,usuario.getNome());
            pstmt.setString(3,usuario.getTipo());
            pstmt.setString(4,usuario.getStatus());
           
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Atualizar Usuário: " + e.getMessage());
       }
    }
    
    public static void alterarSenha(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT alterarsenha(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);

            pstmt.setString(1,usuario.getUsuario());
            pstmt.setString(2,usuario.getSenha());
           
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Alterar Senha Usuário: " + e.getMessage());
        }
    } 
        
    public static ArrayList<Usuario> listar() throws Exception{
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarusuarios()";
            CallableStatement pstmt = conexao.prepareCall(sql);
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Usuario usuario = new Usuario();
                
                usuario.setId(resultado.getInt("id"));
                usuario.setCpf(resultado.getLong("cpf"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setStatus(resultado.getString("status"));
                
                listaUsuario.add(usuario);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Usuário: " + e.getMessage());
        }
        return listaUsuario;
    }

     public static Usuario localizar(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarusuario(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setString(1, usuario.getUsuario());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                usuario.setId(resultado.getInt("id"));
                usuario.setCpf(resultado.getLong("cpf"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setStatus(resultado.getString("status"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Usuário: " + e.getMessage());
        }
        return usuario;
    }
    
    public static boolean nomeUsuarioJaExiste(Usuario usuario) throws Exception{
        boolean resp = false;
        try{
            usuario = localizar(usuario);
            if(usuario.getId() != 0){
                resp = true;
            }
        }
        catch (Exception e){
            throw new Exception("Usuário Ja Cadastrado? - Usuário: " + e.getMessage());
        }
        return resp;
    }
    
    public static boolean usuarioJaExiste(Usuario usuario) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarusuarioporcpf(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,usuario.getCpf());

            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                resp = true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Usuário Ja Cadastrado? - Usuário: " + e.getMessage());
        }
        return resp;
    }
    
    public static void excluir(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT excluirusuario(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,usuario.getId());
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Excluir Usuário: " + e.getMessage());
        }
    }

    public static Usuario efetuarLogin(Usuario usuario) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "select * from efetuarlogin(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2,usuario.getSenha());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setStatus(resultado.getString("status"));
            }
            pstmt.close();
            conexao.close();
        }
         catch (Exception e){
             throw new Exception("Efetuar Login Usuário: " + e.getMessage());
        }
        return usuario;
    }
}