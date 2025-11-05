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

public class IngresoCorrectoSteps {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--incognito", "--disable-notifications", "--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("que ingreso a la página de login en {string}")
    public void que_ingreso_a_la_pagina_de_login_en(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='uid']")));
    }

    @When("ingreso el nombre de usuario {string} y la contraseña {string}")
    public void ingreso_el_nombre_de_usuario_y_la_contrasena(String usuario, String contrasena) {
        WebElement user = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='uid']")));
        WebElement pass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='passw']")));
        user.clear();
        user.sendKeys(usuario);
        pass.clear();
        pass.sendKeys(contrasena);
    }

    @And("presiono el botón de inicio de sesión")
    public void presiono_el_boton_de_inicio_de_sesion() {
        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='btnSubmit']")));
        boton.click();
    }

    @Then("el sistema redirige al panel principal y muestra el mensaje {string}")
    public void el_sistema_redirige_al_panel_principal_y_muestra_el_mensaje(String mensaje) {
        WebElement texto = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Hello Admin User')]")));
        assertTrue(texto.getText().contains(mensaje));
    }
}

