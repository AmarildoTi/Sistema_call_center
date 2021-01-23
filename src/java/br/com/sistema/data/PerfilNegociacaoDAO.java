package br.com.sistema.data;

import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.PerfilNegociacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilNegociacaoDAO {
    
    public static void inserir(Credor credor) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirperfilnegociacao(?,?,?,?,?,?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);

            PerfilNegociacao perfil = credor.getPerfilNegociacao().get(0);
            pstmt.setInt(1,credor.getId());
            pstmt.setInt(2,perfil.getAtrasoDe());
            pstmt.setInt(3,perfil.getAtrasoAte());
            pstmt.setInt(4,perfil.getMaximoParcelas());
            pstmt.setDouble(5,perfil.getJuros());
            pstmt.setDouble(6,perfil.getMulta());
            pstmt.setDouble(7,perfil.getJurosPorParcela());
            pstmt.setDouble(8,perfil.getDescontoValorPrincipal());
            pstmt.setDouble(9,perfil.getDescontoEncargos());
            pstmt.setDouble(10,perfil.getValorMinimoEntrada());
            pstmt.setDouble(11,perfil.getValorMinimoParcela());
            pstmt.setString(12,perfil.getTipo());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Perfil de Negociação: " + e.getMessage());
        }
    }
    
    public static void atualizar(PerfilNegociacao perfil) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT atualizarperfilnegociacao(?,?,?,?,?,?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            pstmt.setInt(1,perfil.getId());
            pstmt.setInt(2,perfil.getAtrasoDe());
            pstmt.setInt(3,perfil.getAtrasoAte());
            pstmt.setInt(4,perfil.getMaximoParcelas());
            pstmt.setDouble(5,perfil.getJuros());
            pstmt.setDouble(6,perfil.getMulta());
            pstmt.setDouble(7,perfil.getJurosPorParcela());
            pstmt.setDouble(8,perfil.getDescontoValorPrincipal());
            pstmt.setDouble(9,perfil.getDescontoEncargos());
            pstmt.setDouble(10,perfil.getValorMinimoEntrada());
            pstmt.setDouble(11,perfil.getValorMinimoParcela());
            pstmt.setString(12,perfil.getTipo());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Perfil de Negociação: " + e.getMessage());
        }
    }
    
    public static ArrayList<PerfilNegociacao> listar(Credor credor) throws Exception{
        ArrayList<PerfilNegociacao> lista = new ArrayList<PerfilNegociacao>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarperfilnegociacao(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, credor.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                PerfilNegociacao perfil = new PerfilNegociacao();
                
                perfil.setId(resultado.getInt("id"));
                perfil.setAtrasoDe(resultado.getInt("atrasode"));
                perfil.setAtrasoAte(resultado.getInt("atrasoate"));
                perfil.setMaximoParcelas(resultado.getInt("maximoparcelas"));
                perfil.setJuros(resultado.getDouble("juros"));
                perfil.setMulta(resultado.getDouble("multa"));
                perfil.setJurosPorParcela(resultado.getDouble("jurosporparcela"));
                perfil.setDescontoValorPrincipal(resultado.getDouble("descontovalorprincipal"));
                perfil.setDescontoEncargos(resultado.getDouble("descontoencargos"));
                perfil.setValorMinimoEntrada(resultado.getDouble("valorminimoentrada"));
                perfil.setValorMinimoParcela(resultado.getDouble("valorminimoparcela"));
                perfil.setTipo(resultado.getString("tipo"));
                lista.add(perfil);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Perfil de Negociação: " + e.getMessage());
        }
        return lista;
    }
    
    public static void excluir(PerfilNegociacao perfil) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT excluirperfilnegociacao(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,perfil.getId());
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Excluir Perfil de Negociação: " + e.getMessage());
        }
    }
    
    public static boolean diasAtrasoJaExiste(Credor credor) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarperfilnegociacao(?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, credor.getId());
            pstmt.setLong(2,credor.getPerfilNegociacao().get(0).getAtrasoDe());
            pstmt.setLong(3,credor.getPerfilNegociacao().get(0).getAtrasoAte());
            pstmt.setLong(4,credor.getPerfilNegociacao().get(0).getId());
            
            ResultSet resultado = pstmt.executeQuery();
            
            if(resultado.next()){
                resp= true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Dias em atraso já existe? - Perfil Negociação: " + e.getMessage());
        }
        return resp;
    }
}