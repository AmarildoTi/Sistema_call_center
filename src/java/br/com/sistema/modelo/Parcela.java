package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.NumberFormat;
import java.util.Date;

public class Parcela {

    private int numero = 0;
    private double valor = 0.0;
    private Date dataVencimento = null;
    private double valorPagamento = 0.0;
    private Date dataPagamento = null;

    public Parcela(){
        
    }
    
    public Parcela(int numero, Date dataPagamento, double valorPagamento){
        this.numero = numero;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }
    
    public String getValorString() {
        return NumberFormat.getCurrencyInstance().format(valor);
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public String getDataVencimentoString() {
        return Funcoes.formatDate(dataVencimento);
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public String getDataPagamentoString() {
        return Funcoes.formatDate(dataPagamento);
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
    
    public String getValorPagamentoString(){
        return NumberFormat.getCurrencyInstance().format(valorPagamento);
    }
}
