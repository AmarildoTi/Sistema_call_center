package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class Divida {

    private int id=0;
    private String contrato="";
    private Date data = new Date();
    private double valor=0.0;
    private Devedor devedor = new Devedor();
    private Credor credor = new Credor();
    private ArrayList<Negociacao> negociacao;
    private Status status = new Status();
    private Credencial ultimoOperador;
    private Date dataUltimoAtendimento;
    private String arquivo = "";

    public Divida(){
        
    }
    
    public Divida(int id){
        this.id = id;
    }
    
    public Divida(String contrato){
        this.contrato = contrato;
    }
    public Divida(int credor, String arquivo){
        this.credor = new Credor(credor);
        this.arquivo = arquivo;
    }
        
    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public ArrayList<Negociacao> getNegociacao() {
        return negociacao;
    }

    public void setNegociacao(ArrayList<Negociacao> negociacao) {
        this.negociacao = negociacao;
    }

    public Devedor getDevedor() {
        return devedor;
    }

    public void setDevedor(Devedor devedor) {
        this.devedor = devedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Date getData() {
        return data;
    }
    
    public String getDataString() {
        return Funcoes.formatDate(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }
    
    public String getValorString() {
        return NumberFormat.getCurrencyInstance().format(valor);
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Credencial getUltimoOperador() {
        return ultimoOperador;
    }

    public void setUltimoOperador(Credencial ultimoOperador) {
        this.ultimoOperador = ultimoOperador;
    }

    public Date getDataUltimoAtendimento() {
        return dataUltimoAtendimento;
    }

    public void setDataUltimoAtendimento(Date dataUltimoAtendimento) {
        this.dataUltimoAtendimento = dataUltimoAtendimento;
    }
    
    public String getDataUltimoAtendimentoString(){
        return Funcoes.formatDate(dataUltimoAtendimento);
    }    

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }    
}
