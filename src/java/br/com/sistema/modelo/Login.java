package br.com.sistema.modelo;

import Util.Funcoes;
import java.util.Date;

public class Login extends Credencial {
    
    private Date data = new Date();

    public String getData() {
        return Funcoes.formatDateTime(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

}
