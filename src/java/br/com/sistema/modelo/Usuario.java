package br.com.sistema.modelo;

import Util.Funcoes;
import java.text.ParseException;

public class Usuario extends Credencial{

    private long cpf = 0;
    private String senha = "";

    public Usuario(){
        
    }
    
    public Usuario(int id){
        super.setId(id);
    }
    
    public Usuario(long cpf){
        this.cpf = cpf;
    }
    
    public Usuario(String usuario){
        super.setUsuario(usuario);
    }
    
    public Usuario(String usuario, String senha){
        this.senha = senha;
        super.setUsuario(usuario);
    }
    
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCPF() throws ParseException{
        return Funcoes.formatString(String.format("%011d",cpf),"###.###.###-##");
    }    
    
}