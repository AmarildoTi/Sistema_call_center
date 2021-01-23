package br.com.sistema.modelo;

public class Contato {
    
    private int id=0;
    private String tipo="";
    private String contato="";

    public Contato(){
        
    }
    
    public Contato(int id){
        this.id = id;
    }
    
    public Contato(String contato){
        this.contato = contato;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.trim();
    }

    public String getContato() {
        return contato.trim();
    }

    public void setContato(String contato) {
        this.contato = contato.trim();
    }
    
}
