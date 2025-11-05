package com.duoc.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class IngresoIncorrectoSteps {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--incognito", "--disable-notifications", "--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("que abro la página de login en {string}")
    public void que_abro_la_pagina_de_login_en(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uid")));
    }

    @When("escribo el nombre de usuario {string} y la clave {string}")
    public void escribo_el_nombre_de_usuario_y_la_clave(String usuario, String clave) {
        WebElement user = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uid")));
        WebElement pass = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("passw")));
        user.clear();
        user.sendKeys(usuario);
        pass.clear();
        pass.sendKeys(clave);
    }

    @And("hago clic para iniciar sesión")
    public void hago_clic_para_iniciar_sesion() {
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnSubmit")));
        boton.click();
    }

    @Then("el sistema muestra un mensaje de error que contiene {string}")
    public void el_sistema_muestra_un_mensaje_de_error_que_contiene(String mensaje) {
        boolean errorVisible = driver.getPageSource().contains(mensaje);
        assertTrue("El mensaje de error esperado no apareció, pero el sistema se comportó como fallo de login.", errorVisible);
    }

}


