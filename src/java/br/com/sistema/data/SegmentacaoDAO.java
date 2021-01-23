package br.com.sistema.data;

import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Segmentacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SegmentacaoDAO {

    public static void inserir(Segmentacao segmentacao) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirsegmentacao(?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,segmentacao.getCredencial().getId());
            pstmt.setInt(2,segmentacao.getCredor().getId());
            pstmt.setInt(3,segmentacao.getAtrasoDe());
            pstmt.setInt(4,segmentacao.getAtrasoAte());
            pstmt.setDouble(5,segmentacao.getValorDividaDe());
            pstmt.setDouble(6,segmentacao.getValorDividaAte());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Segmentação: " + e.getMessage());
        }
    }
    
    public static void atualizar(Segmentacao segmentacao) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT atualizarsegmentacao(?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,segmentacao.getId());
            pstmt.setInt(2,segmentacao.getCredencial().getId());
            pstmt.setInt(3,segmentacao.getCredor().getId());
            pstmt.setInt(4,segmentacao.getAtrasoDe());
            pstmt.setInt(5,segmentacao.getAtrasoAte());
            pstmt.setDouble(6,segmentacao.getValorDividaDe());
            pstmt.setDouble(7,segmentacao.getValorDividaAte());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Atualizar Segmentação: " + e.getMessage());
        }
    }
    
    public static ArrayList<Segmentacao> listar(Segmentacao segmentacao) throws Exception{
        ArrayList<Segmentacao> lista = new ArrayList<Segmentacao>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarsegmentacao(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,segmentacao.getCredor().getId());
            pstmt.setInt(2,segmentacao.getCredencial().getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                segmentacao = new Segmentacao();
                segmentacao.setId(resultado.getInt("id"));
                segmentacao.setAtrasoDe(resultado.getInt("atrasoini"));
                segmentacao.setAtrasoAte(resultado.getInt("atrasofim"));
                segmentacao.setValorDividaDe(resultado.getDouble("valordividaini"));
                segmentacao.setValorDividaAte(resultado.getDouble("valordividafim"));
                Credor credor = new Credor(resultado.getInt("credor"));
                segmentacao.setCredor(CredorDAO.localizarSemPerfil(credor));
                segmentacao.setCredencial(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("usuario"))));
                lista.add(segmentacao);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Segmentação: " + e.getMessage());
        }
        return lista;
    }

    public static void excluir(Segmentacao segmentacao) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT excluirsegmentacao(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,segmentacao.getId());
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Excluir Segmentação: " + e.getMessage());
        }
    }
    
}
