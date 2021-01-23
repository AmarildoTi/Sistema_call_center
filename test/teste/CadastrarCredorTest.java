/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author TI-02
 */
public class CadastrarCredorTest {
    
    private static WebDriver driver;
    
    public CadastrarCredorTest() {
    }
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8084/CallCenter");
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void cadastrarCredor() throws InterruptedException{
         WebElement txtUsuario = driver.findElement(By.id("usuario"));
         txtUsuario.sendKeys("usu001");
         WebElement txtSenha = driver.findElement(By.id("senha"));
         txtSenha.sendKeys("usu001");
         WebElement btn = driver.findElement(By.id("login"));
         btn.click();
         WebElement mensagem = driver.findElement(By.id("textoMessageBoxOK"));
         assertEquals(mensagem.getText(),"");
         
         Thread.sleep(2000);
       
         ((JavascriptExecutor)driver).executeScript("return $(\"a:contains('Cadastro')\").mouseover();");
         Thread.sleep(2000);
         driver.findElement(By.linkText("Credores")).click();
         Thread.sleep(2000);
         btn = driver.findElement(By.id("novo"));
         btn.click();
         Thread.sleep(2000);
         
         WebElement txtCNPJ = driver.findElement(By.id("cnpj"));
         txtCNPJ.sendKeys("86794865000192");
         WebElement txtnomefantasia = driver.findElement(By.id("nomefantasia"));
         txtnomefantasia.sendKeys("Nome Fantasia Teste");
         WebElement txtrazaosocial = driver.findElement(By.id("razaosocial"));
         txtrazaosocial.sendKeys("Credor Teste");
         WebElement txtendereco = driver.findElement(By.id("endereco"));
         txtendereco.sendKeys("Endereço Teste");
         WebElement txtcomplemento = driver.findElement(By.id("complemento"));
         txtcomplemento.sendKeys("Complemento Teste");
         WebElement txtbairro = driver.findElement(By.id("bairro"));
         txtbairro.sendKeys("Bairro Teste");
         WebElement txtCEP = driver.findElement(By.id("cep"));
         txtCEP.sendKeys("09876543");
         WebElement txtcidade = driver.findElement(By.id("cidade"));
         txtcidade.sendKeys("Cidade Teste");
         WebElement txtestado = driver.findElement(By.id("uf"));
         txtestado.sendKeys("SP");
         
         btn = driver.findElement(By.id("salvar"));
         btn.click();
         mensagem = driver.findElement(By.id("textoMessageBoxOK"));
         assertEquals(mensagem.getText(),"Credor cadastrado com sucesso!!!");

         btn = driver.findElement(By.id("ok"));
         btn.click();

         Thread.sleep(3000);
     }
}
