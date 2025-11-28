package com.duoc.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BuscadorSteps {

    static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Given("el navegador está abierto")
    public void elNavegadorEstaAbierto() {
        driver.get("http://testfire.net/index.jsp");
    }

    @Given("el usuario oprime el panel de Search")
    public void oprimirPanelBusqueda() {
        driver.findElement(By.id("query")).click();
    }

    @When("el usuario ingresa el valor {string} en el panel de Search")
    public void ingresarValorBusqueda(String valor) {
        driver.findElement(By.id("query")).clear();
        driver.findElement(By.id("query")).sendKeys(valor);
    }

    @And("realiza la búsqueda")
    public void realizarBusqueda() {
        driver.findElement(By.xpath("//form[@id='frmSearch']//input[@type='submit']")).click();
    }

    @Then("la página debería mostrar resultados relacionados con {string}")
    public void verificarResultado(String valor) {
        String urlActual = driver.getCurrentUrl();
        assertTrue(urlActual.toLowerCase().contains(valor.toLowerCase()));
    }

    @Then("debería mostrarse un mensaje de respuesta no encontrado")
    public void verificarMensajeNoEncontrado() {
        String mensaje = driver.findElement(By.xpath("//p")).getText();
        assertTrue(mensaje.toLowerCase().contains("no")
                || mensaje.toLowerCase().contains("not")
                || mensaje.toLowerCase().contains("no encontrado"));
    }
}
