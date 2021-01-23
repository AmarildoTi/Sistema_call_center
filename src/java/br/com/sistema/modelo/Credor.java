package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.ParseException;
import java.util.ArrayList;

public class Credor {

    private int id=0;
    private long cnpj=0;
    private String razaosocial="";
    private String nomefantasia="";
    private String endereco="";
    private String complemento="";
    private String bairro="";
    private String cidade="";
    private String cep="";
    private String uf="";
    private ArrayList<PerfilNegociacao> perfilNegociacao;

    public Credor(){
    }
    
    public Credor(int id){
        this.id = id;
    }
    
    public Credor(long cnpj){
        this.cnpj = cnpj;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaosocial() {
        return razaosocial.trim();
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia.trim();
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getEndereco() {
        return endereco.trim();
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento.trim();
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro.trim();
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade.trim();
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep.trim();
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf.trim();
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public ArrayList<PerfilNegociacao> getPerfilNegociacao() {
        return perfilNegociacao;
    }

    public void setPerfilNegociacao(ArrayList<PerfilNegociacao> perfilNegociacao) {
        this.perfilNegociacao = perfilNegociacao;
    }

    public String getCNPJ() throws ParseException{
        return Funcoes.formatString(String.format("%014d",cnpj),"##.###.###/####-##");
    }

    public String getCEP() throws ParseException{
        return Funcoes.formatString(String.format("%08d",Integer.parseInt(cep)),"#####-###");
    }
}
