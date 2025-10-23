package com.duoc.steps;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.openqa.selenium.WebElement;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactUsSteps {

    static WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-infobars");
        options.addArguments("--userdata-dir=/tmp/chrome-test-profile");

        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @After
    public void tearDown(){
        if(driver!= null){
            driver.quit();
        }

    }

    @When("el usuario accede a la opción Contact Us")
    public void accederContactUs() {
        driver.findElement(By.xpath("//*[@id='HyperLink3']")).click();
    }

    @When("el usuario abre el formulario de contacto")
    public void abrirFormularioContacto() {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div[1]/p[2]/a")).click();
    }

    @When("el usuario ingresa el nombre {string}")
    public void ingresarNombre(String nombre) {
        WebElement nombreInput = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input"));
        nombreInput.clear();
        nombreInput.sendKeys(nombre);
    }

    @When("el usuario ingresa el email {string}")
    public void ingresarEmail(String email) {
        WebElement emailInput = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input"));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @When("el usuario ingresa el propósito {string}")
    public void ingresarProposito(String proposito) {
        WebElement propositoInput = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input"));
        propositoInput.clear();
        propositoInput.sendKeys(proposito);
    }

    @When("el usuario ingresa los comentarios {string}")
    public void ingresarComentarios(String comentarios) {
        WebElement comentariosInput = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea"));
        comentariosInput.clear();
        comentariosInput.sendKeys(comentarios);
    }

    @When("el usuario envía el formulario de contacto")
    public void enviarFormulario() {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[1]")).click();
    }

    @When("el usuario limpia el formulario")
    public void limpiarFormulario() {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[2]")).click();
    }

    @Then("debería mostrarse confirmación de envío exitoso")
    public void verificarConfirmacionEnvio() {
        String mensaje = driver.findElement(By.xpath("//div[contains(text(),'Thank you')]")).getText();
        assertTrue(mensaje.contains("Thank you"));
    }

    @Then("todos los campos deberían estar vacíos")
    public void verificarCamposVacios() {
        assertTrue(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")).getText().isEmpty());
        assertTrue(driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")).getText().isEmpty());
    }

    @Then("deberían mostrarse mensajes de error por campos inválidos")
    public void verificarErroresFormulario() {
        // Aquí validas los mensajes de error que la página muestra, ejemplo:
        String errorNombre = driver.findElement(By.xpath("//span[contains(text(),'Name is required')]")).getText();
        String errorEmail = driver.findElement(By.xpath("//span[contains(text(),'Email is invalid')]")).getText();
        assertTrue(errorNombre.contains("required"));
        assertTrue(errorEmail.contains("invalid"));
    }

}
