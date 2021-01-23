package br.com.sistema.modelo;

import java.util.Date;

public class Log {

    private Credencial credencial = new Credencial();
    private Date data = new Date();
    private String acao = "";

    public Log(Credencial usuario, String acao){
        this.credencial = usuario;
        this.acao = acao;
    }
    
    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial usuario) {
        this.credencial = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
    
}