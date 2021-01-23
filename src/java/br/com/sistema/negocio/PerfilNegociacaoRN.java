package br.com.sistema.negocio;

import br.com.sistema.modelo.Credor;
import br.com.sistema.modelo.PerfilNegociacao;
import java.util.ArrayList;

public class PerfilNegociacaoRN {
    
    public static String verificarPerfil(Credor credor) throws Exception{
        String mensagem = "";
        try{
            Integer min = -9999;
            Integer max = 0;
            ArrayList<PerfilNegociacao> listaPerfil = credor.getPerfilNegociacao();
            for ( PerfilNegociacao perfil : listaPerfil){
                if (min < perfil.getAtrasoDe()){
                    min = perfil.getAtrasoDe();
                }
                if ( max + 1 != perfil.getAtrasoDe()){
                        mensagem = mensagem + "Não há perfil de negociação para "+(max+1)+" a "+(perfil.getAtrasoDe()-1) + " dias em atraso para o credor :" + credor.getRazaosocial() + ".<br>";
                }
                max = perfil.getAtrasoAte();
            }
            if ( max < 9999){
                mensagem = mensagem + "Não há perfil de negociação para "+(max+1)+" a 9999 dias em atraso para o credor :" + credor.getRazaosocial() + ".<br>";
            }
        }
        catch (Exception e){
            throw new Exception("Verificar Perfil Negociação: " + e.getMessage());
        }
        return mensagem;
    }
    
}
