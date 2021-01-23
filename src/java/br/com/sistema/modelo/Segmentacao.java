package br.com.sistema.modelo;

public class Segmentacao {

    private int id;
    private Credor credor;
    private Credencial credencial;
    private int atrasoDe;
    private int atrasoAte;
    private double valorDividaDe;
    private double valorDividaAte;

    public Segmentacao(){
    }
    
    public Segmentacao(int id){
        this.id = id;
    }
    
    public Segmentacao(int credor, int usuario){
        this.credor = new Credor(credor);
        this.credencial = new Credencial(usuario);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
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

    public double getValorDividaDe() {
        return valorDividaDe;
    }

    public void setValorDividaDe(double valorDividaDe) {
        this.valorDividaDe = valorDividaDe;
    }

    public double getValorDividaAte() {
        return valorDividaAte;
    }

    public void setValorDividaAte(double valorDividaAte) {
        this.valorDividaAte = valorDividaAte;
    }
    
}
