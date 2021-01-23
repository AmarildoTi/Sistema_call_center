package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class Negociacao {
    
    private int id=0;
    private String status="";
    private ArrayList<Parcela> parcela;
    private int parcelamento =0;
    private double valorCorrigido=0;
    private double valorNegociado=0;
    private Date data = null;
    private String email ="";
    
    public Negociacao(){
        
    }
    
    public Negociacao(int id){
        this.id = id;
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

    public double getValorCorrigido() {
        return valorCorrigido;
    }
    
    public String getValorCorrigidoString() {
        return NumberFormat.getCurrencyInstance().format(valorCorrigido);
    }

    public void setValorCorrigido(double valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public double getValorNegociado() {
        return valorNegociado;
    }
    public String getValorNegociadoString() {
        return NumberFormat.getCurrencyInstance().format(valorNegociado);
    }

    public void setValorNegociado(double valorNegociado) {
        this.valorNegociado = valorNegociado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status.trim();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Parcela> getParcela() {
        return parcela;
    }

    public void setParcela(ArrayList<Parcela> parcela) {
        this.parcela = parcela;
    }

    public int getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(int parcelamento) {
        this.parcelamento = parcelamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
