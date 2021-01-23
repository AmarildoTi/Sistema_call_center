package br.com.sistema.modelo;

public class Status {

    private int id=0;
    private String descricao="";

    public Status(){
        
    }
    
    public Status(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
