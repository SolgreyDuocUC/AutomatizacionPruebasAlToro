package com.duoc.steps;

import com.duoc.Utilidades.ExcelUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.duoc.steps.CommonsDriverSteps.driver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuscadorSteps {



    @Given("el usuario oprime el panel de Search")
    public void oprimirPanelBusqueda() {
        driver.findElement(By.id("query")).click();
    }

    @When("el usuario ingresa el valor {string} en el panel de Search")
    public void ingresarValorBusqueda(String valor) {
        driver.findElement(By.id("query")).sendKeys(valor);
    }

    @And("realiza la búsqueda")
    public void realizarBusqueda() {
        driver.findElement(By.name("search")).click(); // o el elemento que dispare la búsqueda
    }

    @Then("la página debería mostrar resultados relacionados con {string}")
    public void verificarResultado(String valor) {
        String urlActual = driver.getCurrentUrl();
        assertTrue(urlActual.contains(valor));
    }

    @Then("debería mostrarse un mensaje de respuesta no encontrado")
    public void verificarMensajeNoEncontrado() {
        String mensaje = driver.findElement(By.xpath("//p[contains(text(),'No encontrado')]")).getText();
        assertEquals("No se encontraron resultados", mensaje);
    }


}
