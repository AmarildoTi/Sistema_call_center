package br.com.sistema.data;

import Util.Funcoes;
import br.com.sistema.modelo.Negociacao;
import br.com.sistema.modelo.Parcela;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ParcelaDAO {
    
        public static void inserir(Negociacao negociacao) throws Exception{
            try{
                Connection conexao = ConectaBanco.getInstance().getConexao();
                String sql = "SELECT * FROM inserirparcela(?,?,?,?)";
                CallableStatement pstmt = conexao.prepareCall(sql);
                for(Parcela parcela : negociacao.getParcela()){
                    pstmt.setInt(1,negociacao.getId());
                    pstmt.setInt(2,parcela.getNumero());
                    pstmt.setDouble(3,parcela.getValor());
                    pstmt.setString(4,Funcoes.formataDataSQL(parcela.getDataVencimentoString()));
                    
                    pstmt.execute();
                }
                pstmt.close();
                conexao.close();
            }
            catch (Exception e){
                throw new Exception("Inserir Parcela: " + e.getMessage());
            }
        }

        public static void pagar(Negociacao negociacao) throws Exception{
            try{
                Connection conexao = ConectaBanco.getInstance().getConexao();
                String sql = "SELECT * FROM pagarparcela(?,?,?,?)";
                CallableStatement pstmt = conexao.prepareCall(sql);
                
                pstmt.setInt(1,negociacao.getId());
                Parcela parcela = negociacao.getParcela().get(0);
                pstmt.setInt(2,parcela.getNumero());
                pstmt.setDouble(3,parcela.getValorPagamento());
                pstmt.setString(4,Funcoes.formataDataSQL(parcela.getDataPagamentoString()));
                
                
                pstmt.execute();
                pstmt.close();
                conexao.close();
            }
            catch (Exception e){
                throw new Exception("Pagar Parcela: " + e.getMessage());
            }
        }

    public static Parcela localizar(Negociacao negociacao) throws Exception{
        Parcela parcela = new Parcela();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarparcela(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, negociacao.getId());
            pstmt.setInt(2,negociacao.getParcela().get(0).getNumero());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                parcela.setNumero(resultado.getInt("numero"));
                parcela.setDataVencimento(resultado.getDate("vencimento"));
                parcela.setValor(resultado.getDouble("valor"));
                parcela.setDataPagamento(resultado.getDate("datapagamento"));
                parcela.setValorPagamento(resultado.getDouble("valorpagamento"));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Parcela: " + e.getMessage());
        }
        return parcela;
        
    }
    
    public static int localizarParcelaPendente(Negociacao negociacao) throws Exception{
        int parcela=0;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizarparcelapendente(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, negociacao.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                parcela = resultado.getInt("numero");
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Parcela Pendente: " + e.getMessage());
        }
        return parcela;
        
    }
    
    public static ArrayList<Parcela> listar(Negociacao negociacao) throws Exception{
        ArrayList<Parcela> lista = new ArrayList<Parcela>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarparcelas(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,negociacao.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Parcela parcela = new Parcela();
                
                parcela.setNumero(resultado.getInt("numero"));
                parcela.setDataVencimento(resultado.getDate("vencimento"));
                parcela.setValor(resultado.getDouble("valor"));
                parcela.setDataPagamento(resultado.getDate("datapagamento"));
                parcela.setValorPagamento(resultado.getDouble("valorpagamento"));
                lista.add(parcela);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Parcela: " + e.getMessage());
        }
        return lista;
    }
}
