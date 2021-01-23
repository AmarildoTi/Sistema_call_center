package br.com.sistema.data;

import Util.Funcoes;
import br.com.sistema.modelo.Credencial;
import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.Devedor;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.Status;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DividaDAO {
    
        public static void inserir(Divida divida) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT inserirdivida(?,?,?,?,?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,divida.getCredor().getId());
            pstmt.setLong(2,divida.getDevedor().getId());
            pstmt.setString(3,divida.getContrato());
            pstmt.setDouble(4,divida.getValor());
            pstmt.setString(5, Funcoes.formataDataSQL(Funcoes.formatDate(divida.getData())));
            pstmt.setString(6,divida.getArquivo());
            
            pstmt.execute();
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Inserir Usuário: " + e.getMessage());
        }
    }
    
    public static Divida localizar(Divida divida) throws Exception{
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardivida(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1,divida.getId());
            
            ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                divida.setId(resultado.getInt("id"));
                divida.setContrato(resultado.getString("contrato"));
                divida.setValor(resultado.getDouble("valor"));
                divida.setData(resultado.getDate("data"));
                divida.setDevedor(DevedorDAO.localizar(new Devedor(resultado.getInt("devedor"))));
                divida.setCredor(CredorDAO.localizarComPerfil(new Credor(resultado.getInt("credor"))));
                divida.setNegociacao(NegociacaoDAO.listar(divida));
                if (resultado.getInt("status") != 0){
                    divida.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
                    divida.setUltimoOperador(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("operadoratendimento"))));
                    divida.setDataUltimoAtendimento(resultado.getDate("dataatendimento"));
                }
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Localizar Dívida: " + e.getMessage());
        }
        return divida;
    }

    public static boolean dividaJaExiste(Divida divida) throws Exception{
        boolean resp = false;
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM localizardividaporcontrato(?,?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setInt(1, divida.getCredor().getId());
            pstmt.setString(2, divida.getContrato());
            
           ResultSet resultado = pstmt.executeQuery();
            if(resultado.next()){
                resp = true;
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Dívida já Existe: " + e.getMessage());
        }
        return resp;
    }

    public static ArrayList<Divida> listarCPF(Devedor devedor) throws Exception{
        ArrayList<Divida> listaDivida = new ArrayList<Divida>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listardividascpf(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getCpf());
            
            ResultSet resultado = pstmt.executeQuery();
            
            while(resultado.next()){
                Divida divida = new Divida();
                divida.setId(resultado.getInt("id"));
                divida.setContrato(resultado.getString("contrato"));
                divida.setValor(resultado.getDouble("valor"));
                divida.setData(resultado.getDate("data"));
                divida.setDevedor(DevedorDAO.localizar(new Devedor(resultado.getInt("devedor"))));
                divida.setCredor(CredorDAO.localizarSemPerfil(new Credor(resultado.getInt("credor"))));
                divida.setNegociacao(NegociacaoDAO.listar(divida));
                if (resultado.getInt("status") != 0){
                    divida.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
                    divida.setUltimoOperador(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("operadoratendimento"))));
                    divida.setDataUltimoAtendimento(resultado.getDate("dataatendimento"));
                }
                listaDivida.add(divida);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Dívida CPF: " + e.getMessage());
        }
        return listaDivida;
    }
    
    public static ArrayList<Divida> listarNome(Devedor devedor) throws Exception{
        ArrayList<Divida> listaDivida = new ArrayList<Divida>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listardividasnome(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setString(1,devedor.getNome());
            
            ResultSet resultado = pstmt.executeQuery();
            
            while(resultado.next()){
                Divida divida = new Divida();
                divida.setId(resultado.getInt("id"));
                divida.setContrato(resultado.getString("contrato"));
                divida.setValor(resultado.getDouble("valor"));
                divida.setData(resultado.getDate("data"));
                divida.setDevedor(DevedorDAO.localizar(new Devedor(resultado.getInt("devedor"))));
                divida.setCredor(CredorDAO.localizarSemPerfil(new Credor(resultado.getInt("credor"))));
                divida.setNegociacao(NegociacaoDAO.listar(divida));
                if (resultado.getInt("status") != 0){
                    divida.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
                    divida.setUltimoOperador(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("operadoratendimento"))));
                    divida.setDataUltimoAtendimento(resultado.getDate("dataatendimento"));
                }
                listaDivida.add(divida);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Dívida Nome: " + e.getMessage());
        }
        return listaDivida;
    }
    
    public static ArrayList<Divida> listarContrato(Divida divida) throws Exception{
        ArrayList<Divida> listaDivida = new ArrayList<Divida>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listardividascontrato(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setString(1,divida.getContrato());
            
            ResultSet resultado = pstmt.executeQuery();
            
            while(resultado.next()){
                divida = new Divida();
                divida.setId(resultado.getInt("id"));
                divida.setContrato(resultado.getString("contrato"));
                divida.setValor(resultado.getDouble("valor"));
                divida.setData(resultado.getDate("data"));
                divida.setDevedor(DevedorDAO.localizar(new Devedor(resultado.getInt("devedor"))));
                Credor credor = new Credor(resultado.getInt("credor"));
                divida.setCredor(CredorDAO.localizarSemPerfil(credor));
                divida.setNegociacao(NegociacaoDAO.listar(divida));
                if (resultado.getInt("status") != 0){
                    divida.setStatus(StatusDAO.localizar(new Status(resultado.getInt("status"))));
                    divida.setUltimoOperador(CredencialDAO.localizarPorID(new Credencial(resultado.getInt("operadoratendimento"))));
                    divida.setDataUltimoAtendimento(resultado.getDate("dataatendimento"));
                }
                listaDivida.add(divida);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Dívida Contrato: " + e.getMessage());
        }
        return listaDivida;
    }    
    
    public static ArrayList<Divida> listarNegociadaPorCPF(Devedor devedor) throws Exception{
        ArrayList<Divida> listaDivida = new ArrayList<Divida>();
        try{
            Connection conexao = ConectaBanco.getInstance().getConexao();
            String sql = "SELECT * FROM listardividanegociacadaporcpf(?)";
            CallableStatement pstmt = conexao.prepareCall(sql);
            
            pstmt.setLong(1,devedor.getCpf());
            
            ResultSet resultado = pstmt.executeQuery();
            
            while(resultado.next()){
                Divida divida = new Divida();
                divida.setId(resultado.getInt("id"));
                divida.setContrato(resultado.getString("contrato"));
                divida.setValor(resultado.getDouble("valor"));
                divida.setData(resultado.getDate("data"));
                divida.setDevedor(DevedorDAO.localizar(new Devedor(resultado.getInt("devedor"))));
                divida.setCredor(CredorDAO.localizarSemPerfil(new Credor(resultado.getInt("credor"))));
                divida.setNegociacao(NegociacaoDAO.listarAtivas(divida));
                listaDivida.add(divida);
            }
            pstmt.close();
            conexao.close();
        }
        catch (Exception e){
            throw new Exception("Listar Dívida CPF: " + e.getMessage());
        }
        return listaDivida;
    }

}
