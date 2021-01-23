package br.com.sistema.modelo;

import Util.Funcoes;
import java.util.ArrayList;
import java.util.Date;

public class Atendimento {

    private int id;
    private Credencial credencial;
    private Date data = new Date();
    private Status status;
    private String descricao;
    private Divida divida;
    private ArrayList<OpcaoNegociacao> opcaoNegociacao = new  ArrayList<OpcaoNegociacao>();

    public Atendimento(){
        
    }
    
    public Atendimento(int id){
        this.id = id;
    }
    
    public Atendimento(Credencial credencial){
        this.credencial = credencial;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }
    
    public String getData() {
        return Funcoes.formatDateTime(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }
    
    public ArrayList<OpcaoNegociacao> getOpcaoNegociacao() {
        return opcaoNegociacao;
    }

    public void setOpcaoNegociacao(ArrayList<OpcaoNegociacao> opcaoNegociacao) {
        this.opcaoNegociacao = opcaoNegociacao;
    }
}
