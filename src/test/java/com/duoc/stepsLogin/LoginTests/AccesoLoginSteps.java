package com.duoc.stepsLogin.LoginTests;

import com.duoc.stepsLogin.Commons.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;

public class AccesoLoginSteps {

    @Given("que ingreso directamente a la página de login en {string}")
    public void ingresoDirectamenteALaPaginaDeLogin(String url) {

        DriverManager.getDriver().get(url);

        DriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table")
                )
        );

        System.out.println("Página cargada correctamente: " + url);
    }

    @Then("el formulario de autenticación se carga correctamente")
    public void formularioDeAutenticacionSeCargaCorrectamente() {
        WebElement form = DriverManager.getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table")
                )
        );

        assertTrue("El formulario de login no está visible.", form.isDisplayed());
        System.out.println("Formulario visible correctamente");
    }
}




