package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.ParseException;
import java.util.ArrayList;

public class Devedor {
    
    private int id=0;
    private long cpf=0;
    private String nome="";
    private String endereco="";
    private String complemento="";
    private String bairro="";
    private String cidade="";
    private String cep="";
    private String uf="";
    private ArrayList<Contato> contato;
    
    public Devedor(){
        
    }
    
    public Devedor(int id){
        this.id = id;
    }
    
    public Devedor(long cpf){
        this.cpf = cpf;
    }
    
    public Devedor(String nome){
        this.nome = nome;
    }

    public ArrayList<Contato> getContato() {
        return contato;
    }

    public void setContato(ArrayList<Contato> contato) {
        this.contato = contato;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
            
    public String getCPF() throws ParseException{
        return Funcoes.formatString(String.format("%011d",cpf),"###.###.###-##");
    }
}
