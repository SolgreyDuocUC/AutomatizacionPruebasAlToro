package com.duoc.stepsLogin.LoginTests;

import com.duoc.stepsLogin.Commons.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class IngresoCorrectoSteps {

    @Given("que ingreso a la página de login en {string}")
    public void que_ingreso_a_la_pagina_de_login_en(String url) {
        DriverManager.getDriver().get(url);
        DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[1]/td[2]/input")
                )
        );
        System.out.println("Página de login cargada correctamente: " + url);
    }

    @When("ingreso el nombre de usuario {string} y la contraseña {string}")
    public void ingreso_el_nombre_de_usuario_y_la_contrasena(String usuario, String contrasena) {
        WebElement campoUsuario = DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[1]/td[2]/input")
                )
        );
        WebElement campoClave = DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")
                )
        );

        campoUsuario.clear();
        campoUsuario.sendKeys(usuario);
        campoClave.clear();
        campoClave.sendKeys(contrasena);
        System.out.println("Credenciales ingresadas correctamente.");
    }

    @And("presiono el botón de inicio de sesión")
    public void presiono_el_boton_de_inicio_de_sesion() {
        WebElement botonLogin = DriverManager.getWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")
                )
        );
        botonLogin.click();
        System.out.println("Botón de inicio de sesión presionado.");
    }

    @Then("el sistema redirige al panel principal y muestra el mensaje {string}")
    public void el_sistema_redirige_al_panel_principal_y_muestra_el_mensaje(String mensajeEsperado) {
        WebElement mensajeBienvenida = DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/h1")
                )
        );

        String textoActual = mensajeBienvenida.getText();
        System.out.println("Mensaje mostrado en el panel: " + textoActual);

        assertTrue(
                "El mensaje de bienvenida no coincide. Se esperaba: " + mensajeEsperado + " pero se obtuvo: " + textoActual,
                textoActual.contains(mensajeEsperado)
        );
    }
}
