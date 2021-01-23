package br.com.sistema.data;

import br.com.sistema.modelo.Credor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CredorDAO {

    public static void inserir(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserircredor(?,?,?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,credor.getCnpj());
            pstmt.setString(2,credor.getRazaosocial());
            pstmt.setString(3,credor.getEndereco());
            pstmt.setString(4,credor.getComplemento());
            pstmt.setString(5,credor.getBairro());
            pstmt.setString(6,credor.getCidade());
            pstmt.setString(7,credor.getCep());
            pstmt.setString(8,credor.getUf());
            pstmt.setString(9,credor.getNomefantasia());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Credor: " + e.getMessage());
        }
    }
 
        public static void atualizar(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT atualizarcredor(?,?,?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);

            pstmt.setInt(1,credor.getId());
            pstmt.setString(2,credor.getRazaosocial());
            pstmt.setString(3,credor.getEndereco());
            pstmt.setString(4,credor.getComplemento());
            pstmt.setString(5,credor.getBairro());
            pstmt.setString(6,credor.getCidade());
            pstmt.setString(7,credor.getCep());
            pstmt.setString(8,credor.getUf());
            pstmt.setString(9,credor.getNomefantasia());
           
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Atualizar Credor: " + e.getMessage());
       }
    }
        
    public static Credor localizarSemPerfil(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarcredorporid(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,credor.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                credor.setId(credor.getId());
                credor.setCnpj(resultado.getLong("cnpj"));
                credor.setRazaosocial(resultado.getString("razaosocial"));
                credor.setNomefantasia(resultado.getString("nomefantasia"));
                credor.setEndereco(resultado.getString("endereco"));
                credor.setComplemento(resultado.getString("complemento"));
                credor.setBairro(resultado.getString("bairro"));
                credor.setCidade(resultado.getString("cidade"));
                credor.setCep(resultado.getString("cep"));
                credor.setUf(resultado.getString("uf"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Credor: " + e.getMessage());
        }
        return credor;
    }

    public static Credor localizarComPerfil(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarcredorporid(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,credor.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                credor.setId(credor.getId());
                credor.setCnpj(resultado.getLong("cnpj"));
                credor.setRazaosocial(resultado.getString("razaosocial"));
                credor.setNomefantasia(resultado.getString("nomefantasia"));
                credor.setEndereco(resultado.getString("endereco"));
                credor.setComplemento(resultado.getString("complemento"));
                credor.setBairro(resultado.getString("bairro"));
                credor.setCidade(resultado.getString("cidade"));
                credor.setCep(resultado.getString("cep"));
                credor.setUf(resultado.getString("uf"));
                credor.setPerfilNegociacao(PerfilNegociacaoDAO.listar(credor));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Credor: " + e.getMessage());
        }
        return credor;
    }

    public static ArrayList<Credor> listarSemPerfil() throws Exception{
        ArrayList<Credor> lista = new ArrayList<Credor>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarcredores()";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Credor credor = new Credor(resultado.getInt("id"));
                credor.setCnpj(resultado.getLong("cnpj"));
                credor.setRazaosocial(resultado.getString("razaosocial"));
                credor.setNomefantasia(resultado.getString("nomefantasia"));
                credor.setEndereco(resultado.getString("endereco"));
                credor.setComplemento(resultado.getString("complemento"));
                credor.setBairro(resultado.getString("bairro"));
                credor.setCidade(resultado.getString("cidade"));
                credor.setCep(resultado.getString("cep"));
                credor.setUf(resultado.getString("uf"));
                
                lista.add(credor);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Credor: " + e.getMessage());
        }
        return lista;
    }
        
    public static ArrayList<Credor> listarComPerfil() throws Exception{
        ArrayList<Credor> lista = new ArrayList<Credor>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarcredores()";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Credor credor = new Credor(resultado.getInt("id"));
                credor.setCnpj(resultado.getLong("cnpj"));
                credor.setRazaosocial(resultado.getString("razaosocial"));
                credor.setNomefantasia(resultado.getString("nomefantasia"));
                credor.setEndereco(resultado.getString("endereco"));
                credor.setComplemento(resultado.getString("complemento"));
                credor.setBairro(resultado.getString("bairro"));
                credor.setCidade(resultado.getString("cidade"));
                credor.setCep(resultado.getString("cep"));
                credor.setUf(resultado.getString("uf"));
                credor.setPerfilNegociacao(PerfilNegociacaoDAO.listar(credor));
                
                lista.add(credor);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Credor: " + e.getMessage());
        }
        return lista;
    }
        
    public static void excluir(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT excluircredor(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,credor.getId());
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Excluir Credor: " + e.getMessage());
        }
    }        

    public static boolean credorJaExiste(Credor credor) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarcredorporcnpj(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,credor.getCnpj());

            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                resp = true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Credor Ja Cadastrado? - Credor: " + e.getMessage());
        }
        return resp;
    }
}
