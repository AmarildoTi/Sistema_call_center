package br.com.sistema.modelo;

public class PerfilNegociacao {
    
    private int id=0;
    private int atrasoDe=0;
    private int atrasoAte=0;
    private int maximoParcelas=0;
    private double juros=0.0;
    private double multa=0.0;
    private double jurosPorParcela=0.0;
    private double descontoValorPrincipal=0.0;
    private double descontoEncargos=0.0;
    private double valorMinimoEntrada=0.0;
    private double valorMinimoParcela=0.0;
    private String tipo="";
    
    public PerfilNegociacao(){
        
    }
    
    public PerfilNegociacao(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtrasoDe() {
        return atrasoDe;
    }

    public void setAtrasoDe(int atrasoDe) {
        this.atrasoDe = atrasoDe;
    }

    public int getAtrasoAte() {
        return atrasoAte;
    }

    public void setAtrasoAte(int atrasoAte) {
        this.atrasoAte = atrasoAte;
    }

    public int getMaximoParcelas() {
        return maximoParcelas;
    }

    public void setMaximoParcelas(int maximoParcela) {
        this.maximoParcelas = maximoParcela;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public double getJurosPorParcela() {
        return jurosPorParcela;
    }

    public void setJurosPorParcela(double jurosPorParcela) {
        this.jurosPorParcela = jurosPorParcela;
    }

    public double getDescontoValorPrincipal() {
        return descontoValorPrincipal;
    }

    public void setDescontoValorPrincipal(double descontoValorPrincipal) {
        this.descontoValorPrincipal = descontoValorPrincipal;
    }

    public double getDescontoEncargos() {
        return descontoEncargos;
    }

    public void setDescontoEncargos(double descontoEncargo) {
        this.descontoEncargos = descontoEncargo;
    }

    public double getValorMinimoEntrada() {
        return valorMinimoEntrada;
    }

    public void setValorMinimoEntrada(double valorMininoEntrada) {
        this.valorMinimoEntrada = valorMininoEntrada;
    }

    public double getValorMinimoParcela() {
        return valorMinimoParcela;
    }

    public void setValorMinimoParcela(double valorMininoParcela) {
        this.valorMinimoParcela = valorMininoParcela;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
