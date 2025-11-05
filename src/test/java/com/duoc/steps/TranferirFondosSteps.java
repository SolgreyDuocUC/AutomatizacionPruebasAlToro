package com.duoc.steps;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TranferirFondosSteps {


    // Usar driver de CommonSteps
    private WebDriver driver = CommonSteps.driver;

    @When("el usuario oprime el botón Transfer Funds")
    public void oprimirTransferFunds() {
        driver.findElement(By.xpath("//*[@id='MenuHyperLink3']")).click();
    }

    @When("el usuario selecciona la cuenta desde la cual transferir")
    public void seleccionarCuentaDesde() {
        Select fromAccount = new Select(driver.findElement(By.xpath("//*[@id='fromAccount']")));
        fromAccount.selectByIndex(1);
    }

    @When("el usuario selecciona la cuenta destino")
    public void seleccionarCuentaDestino() {
        Select toAccount = new Select(driver.findElement(By.xpath("//*[@id='toAccount']")));
        toAccount.selectByIndex(2);
    }

    @When("el usuario ingresa el monto a transferir")
    public void ingresarMontoValido() {
        driver.findElement(By.xpath("//*[@id='transferAmount']")).sendKeys("100");
    }

    @When("el usuario ingresa un monto inválido a transferir")
    public void ingresarMontoInvalido() {
        driver.findElement(By.xpath("//*[@id='transferAmount']")).sendKeys("1000000");
    }

    @When("el usuario confirma la transacción")
    public void confirmarTransaccion() {
        driver.findElement(By.xpath("//*[@id='transfer']")).click();
    }

    @Then("deberia mostrarse una confirmación de transferencia exitosa")
    public void verificarConfirmacion() {
        String mensaje = driver.findElement(By.xpath("//div[contains(text(),'Transferencia exitosa')]")).getText();
        assertTrue(mensaje.contains("Transferencia exitosa"));
    }

    @Then("deberia mostrarse un mensaje de error indicando monto inválido")
    public void verificarErrorMonto() {
        String mensajeError = driver.findElement(By.xpath("//div[contains(text(),'monto inválido')]")).getText();
        assertTrue(mensajeError.contains("monto inválido"));
    }

}
