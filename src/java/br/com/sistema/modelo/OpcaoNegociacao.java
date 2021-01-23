package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.NumberFormat;
import java.util.Date;

public class OpcaoNegociacao {
    
    private double valorEntrada=0.0;
    private double valorParcela=0.0;
    private double valorCorrecao = 0.0;
    private double valorCorrigido=0.0;
    private double maximoDesconto=0.0;
    private double valorNegociacao = 0.0;
    private int parcelamento=0;
    private Date data;
    
    public OpcaoNegociacao(){
        
    }

    public OpcaoNegociacao(Date data, double valorEntrada, double maximoDesconto){
        this.data = data;
        this.valorEntrada = valorEntrada;
        this.maximoDesconto = maximoDesconto;
    }

    public double getValorEntrada() {
        return valorEntrada;
    }
    
    public String getValorEntradaString() {
        return NumberFormat.getCurrencyInstance().format(valorEntrada);
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public double getValorParcela() {
        return valorParcela;
    }
    
    public String getValorParcelaString() {
        return NumberFormat.getCurrencyInstance().format(valorParcela);
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
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

    public int getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(int parcelamento) {
        this.parcelamento = parcelamento;
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
    
    public String getOpcao(){
        String op;
        if(parcelamento==0){
            op = "A Vista " + getValorParcelaString();
        }
        else{
            if(valorEntrada>0){
                op = "Entrada de " +getValorEntradaString() + " + " + getParcelamento() + " Parcela(s) de " +getValorParcelaString();
            }
            else{
                op = getParcelamento() + " Parcela(s) de " +getValorParcelaString();
            }
        }
        return op;
    }

    public double getValorCorrecao() {
        return valorCorrecao;
    }

    public String getValorCorrecaoString() {
        return NumberFormat.getCurrencyInstance().format(valorCorrecao);
    }

    public void setValorCorrecao(double valorCorrecao) {
        this.valorCorrecao = valorCorrecao;
    }

    public double getMaximoDesconto() {
        return maximoDesconto;
    }

    public String getMaximoDescontoString() {
        return NumberFormat.getCurrencyInstance().format(maximoDesconto);
    }

    public void setMaximoDesconto(double maximoDesconto) {
        this.maximoDesconto = maximoDesconto;
    }

    public double getValorNegociacao() {
        return valorNegociacao;
    }

    public String getValorNegociacaoString() {
        return NumberFormat.getCurrencyInstance().format(valorNegociacao);
    }

    public void setValorNegociacao(double valorNegociacao) {
        this.valorNegociacao = valorNegociacao;
    }
        
    public String getParcelamentoMaisValor(){
        return String.valueOf(this.parcelamento)+"#"+getValorParcelaString();
    }
}
