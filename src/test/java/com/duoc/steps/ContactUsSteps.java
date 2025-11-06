package com.duoc.steps;


import com.duoc.Utilidades.ExcelUtils;
import com.duoc.Utilidades.ScreensUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.duoc.steps.CommonsDriverSteps.driver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactUsSteps {





    @When("Oprime la opción Contact Us")
    public void Oprime_opcion_contact_us(String linkTransfer) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/form/table/tbody/tr[1]/td[2]/a[2]")));
        if (mensaje.isDisplayed())
            driver.findElement(By.linkText(linkTransfer)).click();
        else
            System.err.println("Error al ingresar en la cuenta...");
        String obj = "oprime_opcion_contactUsa";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");


    }

    @When("Oprime link de formulario")
    public void oprime_link_formulario(String linkTransfer) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div[1]/p[2]/a")));
        if (mensaje.isDisplayed())
            driver.findElement(By.linkText(linkTransfer));
        else
            System.err.println("Error al ingresar al formulario");
        String obj = "oprime_enlace_para_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");

    }

    @When("ingresa el nombre {string}")
    public void ingresarNombre(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")).sendKeys(ExcelUtils.getCellData(Integer.parseInt(fila), 2).toString());
        String obj = "Ingresa_nombre_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("ingresa el email address{string}")
    public void ingresa_email_address(String fila) throws Exception {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")).sendKeys(ExcelUtils.getCellData(Integer.parseInt(fila), 2).toString());
        String obj = "Ingresa_email_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("ingresa el asunto {string}")
    public void ingresa_asunto(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")).sendKeys(ExcelUtils.getCellData(Integer.parseInt(fila), 2).toString());
        String obj = "Ingresa_el_asunto_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("ingresa los comentarios o preguntas {string}")
    public void ingresar_los_comentarios_preguntas(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")).sendKeys(ExcelUtils.getCellData(Integer.parseInt(fila), 2).toString());
        String obj = "Ingresa_comentarios_o_preguntas_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
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
