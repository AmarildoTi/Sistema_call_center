package br.com.sistema.negocio;


import Util.Funcoes;
import br.com.sistema.data.ContatoDAO;
import br.com.sistema.data.DevedorDAO;
import br.com.sistema.data.DividaDAO;
import br.com.sistema.modelo.Contato;
import br.com.sistema.modelo.Devedor;
import br.com.sistema.modelo.Divida;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class DividaRN {

    public static void importar(Divida divida) throws Exception{
        try{
            FileReader file = new FileReader(divida.getArquivo());
            BufferedReader reader = new BufferedReader(file);

            String linha;
            String[] campos;

            while((linha = reader.readLine()) != null){
                campos = linha.split(";");
                if (campos.length != 16){
                    
                }else{
                    Devedor devedor = new Devedor();
                    devedor.setCpf(Long.parseLong(campos[0].trim()));
                    devedor.setNome(campos[1].trim());
                    devedor.setEndereco(campos[2].trim());
                    devedor.setComplemento(campos[3].trim());
                    devedor.setBairro(campos[4].trim());
                    devedor.setCidade(campos[5].trim());
                    devedor.setUf(campos[6].trim());
                    devedor.setCep(campos[7].trim());
                    if (!DevedorDAO.devedorJaExiste(devedor)){
                        devedor.setId(DevedorDAO.inserir(devedor));                        
                    }else{
                        devedor.setId(DevedorDAO.localizarPorCPF(devedor).getId());
                    }
                    for(int x = 8; x < 11; x++){
                        if(!campos[x].trim().equals("")){
                            Contato contato = new Contato();
                            String tipo = "";
                            if(x == 8){
                                tipo = "RESIDENCIAL";
                            }
                            if(x == 9){
                                tipo = "CELULAR";
                            }                                
                            if(x == 10){
                                tipo = "COMERCIAL";
                            }
                            contato.setTipo(tipo);
                            contato.setContato(campos[x].trim());
                            ArrayList<Contato> listaContato = new ArrayList<Contato>();
                            listaContato.add(contato);
                            devedor.setContato(listaContato);
                            if (!ContatoDAO.contatoJaExiste(devedor)){
                                ContatoDAO.inserir(devedor);
                            }
                        }
                    }
                    divida.setDevedor(devedor);
                    divida.setContrato(campos[11]);
                    divida.setData(Funcoes.toDate(campos[12]));
                    divida.setValor(Double.parseDouble(campos[13]));
                    if(!DividaDAO.dividaJaExiste(divida)){
                        DividaDAO.inserir(divida);
                    }
                }
            }
        }catch(FileNotFoundException e){
            throw new Exception("Arquivo não encontrado!!!");
        }
    }
}
