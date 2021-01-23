package br.com.sistema.data;

import br.com.sistema.modelo.Devedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DevedorDAO {
    
    public static Devedor localizar(Devedor devedor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardevedorporid(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,devedor.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                devedor.setId(devedor.getId());
                devedor.setCpf(resultado.getLong("cpf"));
                devedor.setNome(resultado.getString("nome"));
                devedor.setEndereco(resultado.getString("endereco"));
                devedor.setComplemento(resultado.getString("complemento"));
                devedor.setBairro(resultado.getString("bairro"));
                devedor.setCidade(resultado.getString("cidade"));
                devedor.setCep(resultado.getString("cep"));
                devedor.setUf(resultado.getString("uf"));
                devedor.setContato(ContatoDAO.listar(devedor));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Devedor: " + e.getMessage());
        }
        return devedor;
    }
    
    public static Devedor localizarPorCPF(Devedor devedor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardevedorporcpf(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getCpf());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                devedor.setId(resultado.getInt("id"));
                devedor.setCpf(resultado.getLong("cpf"));
                devedor.setNome(resultado.getString("nome"));
                devedor.setEndereco(resultado.getString("endereco"));
                devedor.setComplemento(resultado.getString("complemento"));
                devedor.setBairro(resultado.getString("bairro"));
                devedor.setCidade(resultado.getString("cidade"));
                devedor.setCep(resultado.getString("cep"));
                devedor.setUf(resultado.getString("uf"));
                devedor.setContato(ContatoDAO.listar(devedor));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Devedor Por CPF: " + e.getMessage());
        }
        return devedor;
    }
    
    public static boolean devedorJaExiste(Devedor devedor) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardevedorporcpf(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getCpf());

            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                resp = true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Devedor Já Existe?: " + e.getMessage());
        }
        return resp;
    }
    
    public static int inserir(Devedor devedor) throws Exception{
        int id = 0;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM inserirdevedor(?,?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getCpf());
            pstmt.setString(2,devedor.getNome());
            pstmt.setString(3,devedor.getEndereco());
            pstmt.setString(4,devedor.getComplemento());
            pstmt.setString(5,devedor.getBairro());
            pstmt.setString(6,devedor.getCidade());
            pstmt.setString(7,devedor.getCep());
            pstmt.setString(8,devedor.getUf());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                id = resultado.getInt("inserirdevedor");
            }
            
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Devedor: " + e.getMessage());
        }
        return id;
    }   

    public static void atualizar(Devedor devedor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM atualizardevedor(?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getId());
            pstmt.setString(2,devedor.getEndereco());
            pstmt.setString(3,devedor.getComplemento());
            pstmt.setString(4,devedor.getBairro());
            pstmt.setString(5,devedor.getCidade());
            pstmt.setString(6,devedor.getCep());
            pstmt.setString(7,devedor.getUf());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Atualizar Devedor: " + e.getMessage());
        }
    }   

}
