package com.duoc.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class IngresoCredencialesLogin {

    static WebDriver driver;

    @Before
    public void setup() {

        // Configurar WebDriver de Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Opciones de Chrome
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-infobars");
        options.addArguments("--user-data-dir=/tmp/chrome-test-profile");

        // Inicializar driver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que accedo a la página de inicio de sesión en {string}")
    public void que_accedo_a_la_pagina_de_inicio_de_sesion_en(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);
    }

    @When("ingreso el usuario {string} y la contraseña {string}")
    public void ingreso_el_usuario_y_la_contraseña(String usuario, String contrasena) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='uid']")).sendKeys(usuario);
        driver.findElement(By.xpath("//*[@id='passw']")).sendKeys(contrasena);
        Thread.sleep(1000);
    }

    @When("hago clic en el botón de login")
    public void hago_clic_en_el_boton_de_login() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='Login']/table/tbody/tr[3]/td[2]/input")).click();
        Thread.sleep(2000);
    }

    @Then("el sistema redirige al panel principal y muestra el mensaje {string}")
    public void el_sistema_redirige_al_panel_principal_y_muestra_el_mensaje(String mensajeEsperado) throws InterruptedException {
        // Buscar el mensaje de bienvenida
        String textoActual = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/h1")).getText();

        if (textoActual.contains(mensajeEsperado)) {
            System.out.println("Prueba exitosa: se mostró el mensaje esperado -> " + textoActual);
        } else {
            System.out.println("Prueba fallida: mensaje diferente. Se obtuvo -> " + textoActual);
        }

        Thread.sleep(2000);
    }

}

