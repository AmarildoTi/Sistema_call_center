package OpcaoNegociacao;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Util.Funcoes;
import br.com.sistema.data.DividaDAO;
import br.com.sistema.modelo.Atendimento;
import br.com.sistema.negocio.OpcaoNegociacaoRN;
import br.com.sistema.modelo.Divida;
import br.com.sistema.modelo.OpcaoNegociacao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author TI-02
 */
public class OpcaoNegociacaoTest {
    
    public OpcaoNegociacaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void calculaOpcaoNegociacao() throws ParseException, Exception {
        Divida divida;
        Date datapagamento;
        double valorentrada;
        double desconto;
        
        divida = DividaDAO.localizar(new Divida(9));
        datapagamento = Funcoes.toDate("10/10/2013");
        valorentrada = 0.0;
        desconto = 0.0;
        Atendimento atendimento = new Atendimento();
        atendimento.setDivida(divida);
        ArrayList<OpcaoNegociacao> opcoes = new ArrayList<OpcaoNegociacao>();
        OpcaoNegociacao opcaoNegociacao = new OpcaoNegociacao(datapagamento,valorentrada,desconto);
        opcoes.add(opcaoNegociacao);
        atendimento.setOpcaoNegociacao(opcoes);
        
        opcoes = OpcaoNegociacaoRN.calcular(atendimento);
        assertEquals("A Vista R$ 1.146,61",opcoes.get(0).getOpcao());
        
        
        datapagamento = Funcoes.toDate("25/09/2013");
        opcoes = new ArrayList<OpcaoNegociacao>();
        opcaoNegociacao = new OpcaoNegociacao(datapagamento,valorentrada,desconto);
        opcoes.add(opcaoNegociacao);
        atendimento.setOpcaoNegociacao(opcoes);
        opcoes = OpcaoNegociacaoRN.calcular(atendimento);
        assertEquals("2 Parcela(s) de R$ 584,86",opcoes.get(1).getOpcao());
        
        valorentrada = 11.0;
        opcoes = new ArrayList<OpcaoNegociacao>();
        opcaoNegociacao = new OpcaoNegociacao(datapagamento,valorentrada,desconto);
        opcoes.add(opcaoNegociacao);
        atendimento.setOpcaoNegociacao(opcoes);
        opcoes = OpcaoNegociacaoRN.calcular(atendimento);
        assertEquals("Entrada de R$ 11,00 + 1 Parcela(s) de R$ 1.149,80",opcoes.get(1).getOpcao());
    }
}
