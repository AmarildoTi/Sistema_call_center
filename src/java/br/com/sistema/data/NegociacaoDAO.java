package br.com.sistema.data;

import Util.Funcoes;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Negociacao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegociacaoDAO {
    
    public static int inserir(Atendimento atendimento) throws Exception{
        Negociacao negociacao = null;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM inserirnegociacao(?,?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            negociacao = atendimento.getDivida().getNegociacao().get(0);
            pstmt.setInt(1,atendimento.getId());
            pstmt.setInt(2,atendimento.getDivida().getId());
            pstmt.setDouble(3,negociacao.getValorCorrigido());
            pstmt.setDouble(4,negociacao.getValorNegociado());
            pstmt.setString(5,Funcoes.formataDataSQL(negociacao.getDataString()));
            pstmt.setString(6,negociacao.getStatus());
            pstmt.setString(7,negociacao.getEmail());
                
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                negociacao.setId(resultado.getInt("inserirnegociacao"));
                ParcelaDAO.inserir(negociacao);
            }
            
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Negociação: " + e.getMessage());
        }
        return negociacao.getId();
    }
    
    public static ArrayList<Negociacao> listar(Divida divida) throws Exception{
        ArrayList<Negociacao> lista = new ArrayList<Negociacao>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarnegociacoes(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, divida.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Negociacao negociacao = new Negociacao();
                
                negociacao.setId(resultado.getInt("id"));
                negociacao.setStatus(resultado.getString("status"));
                negociacao.setEmail(resultado.getString("email"));
                negociacao.setValorCorrigido(resultado.getDouble("valorcorrigido"));
                negociacao.setValorNegociado(resultado.getDouble("valornegociado"));
                negociacao.setData(resultado.getDate("data"));
                negociacao.setParcela(ParcelaDAO.listar(negociacao));
                negociacao.setParcelamento(negociacao.getParcela().size());
                lista.add(negociacao);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Negociação: " + e.getMessage());
        }
        return lista;
    }        

    public static ArrayList<Negociacao> listarAtivas(Divida divida) throws Exception{
        ArrayList<Negociacao> lista = new ArrayList<Negociacao>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listarnegociacoesativas(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, divida.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                Negociacao negociacao = new Negociacao();
                
                negociacao.setId(resultado.getInt("id"));
                negociacao.setStatus(resultado.getString("status"));
                negociacao.setEmail(resultado.getString("email"));
                negociacao.setValorCorrigido(resultado.getDouble("valorcorrigido"));
                negociacao.setValorNegociado(resultado.getDouble("valornegociado"));
                negociacao.setData(resultado.getDate("data"));
                negociacao.setParcela(ParcelaDAO.listar(negociacao));
                negociacao.setParcelamento(negociacao.getParcela().size());
                lista.add(negociacao);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Negociação: " + e.getMessage());
        }
        return lista;
    }        
    
}
