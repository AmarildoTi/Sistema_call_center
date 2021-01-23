package br.com.sistema.data;

import br.com.sistema.modelo.Atendimento;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Status;
import br.com.sistema.negocio.OpcaoNegociacaoRN;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class AtendimentoDAO {
    
    public static Atendimento localizar(Atendimento atendimento) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardivida(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, atendimento.getDivida().getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                atendimento.setData(new Date());
                atendimento.getDivida().setCredor(CredorDAO.localizarComPerfil(atendimento.getDivida().getCredor()));
                atendimento.setOpcaoNegociacao(OpcaoNegociacaoRN.calcular(atendimento));
                atendimento.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Iniciar Atendimento: " + e.getMessage());
        }
        return atendimento;
    }
    
        public static ArrayList<Atendimento> listar(Atendimento atendimento) throws Exception{
        ArrayList<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listaratendimentos(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);

            pstmt.setInt(1,atendimento.getDivida().getId());
            
            ResultSet resultado = pstmt.executeQuery();
            while(resultado.next()){
                atendimento = new Atendimento();
                atendimento.setCredencial(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("usuario"))));
                atendimento.setData(resultado.getTimestamp("data"));
                atendimento.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
                atendimento.setDescricao(resultado.getString("descricao"));
                listaAtendimento.add(atendimento);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Atendimento: " + e.getMessage());
        }
        return listaAtendimento;
    }

    
    public static Atendimento iniciar(Atendimento atendimento) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM iniciaratendimento(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,atendimento.getCredencial().getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                atendimento.setData(resultado.getTimestamp("data"));
                atendimento.setDivida(DividaDAO.localizar(new Divida(resultado.getInt("divida"))));
                atendimento.setOpcaoNegociacao(OpcaoNegociacaoRN.calcular(atendimento));
                atendimento.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Iniciar Atendimento: " + e.getMessage());
        }
        return atendimento;
    }
    
    public static void inserir(Atendimento atendimento) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM inseriratendimento(?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,atendimento.getDivida().getId());
            pstmt.setInt(2, atendimento.getCredencial().getId());
            pstmt.setInt(3, atendimento.getStatus().getId());
            pstmt.setString(4, atendimento.getDescricao());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Atendimento: " + e.getMessage());
        }
    }
    
    public static int inserirComNegociacao(Atendimento atendimento) throws Exception{
        int negociacao =0;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM inseriratendimento(?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, atendimento.getDivida().getId());
            pstmt.setInt(2, atendimento.getCredencial().getId());
            pstmt.setInt(3, atendimento.getStatus().getId());
            pstmt.setString(4, atendimento.getDescricao());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                atendimento.setId(resultado.getInt("inseriratendimento"));
                negociacao = NegociacaoDAO.inserir(atendimento);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Concluir Negociação Atendimento: " + e.getMessage());
        }
        return negociacao;
    }
}