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

public class IngresoIncorrectoSteps {

    @Given("que abro la página de login en {string}")
    public void que_abro_la_pagina_de_login_en(String url) {
        DriverManager.getDriver().get(url);
        DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[1]/td[2]/input")
                )
        );
        System.out.println("Página cargada correctamente: " + url);
    }

    @When("escribo el nombre de usuario {string} y la clave {string}")
    public void escribo_el_nombre_de_usuario_y_la_clave(String usuario, String clave) {
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
        campoClave.sendKeys(clave);
        System.out.println("Credenciales ingresadas: " + usuario + " / " + clave);
    }

    @And("hago clic para iniciar sesión")
    public void hago_clic_para_iniciar_sesion() {
        WebElement botonLogin = DriverManager.getWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")
                )
        );
        botonLogin.click();
        System.out.println("Botón de login presionado.");
    }

    @Then("el sistema muestra un mensaje de error que contiene {string}")
    public void el_sistema_muestra_un_mensaje_de_error_que_contiene(String mensajeEsperado) {
        WebElement mensajeError = DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/span")
                )
        );

        String textoError = mensajeError.getText();
        System.out.println("Mensaje mostrado: " + textoError);

        assertTrue(
                "El mensaje esperado no apareció. Se esperaba: " + mensajeEsperado + " pero se obtuvo: " + textoError,
                textoError.contains(mensajeEsperado)
        );
    }
}



