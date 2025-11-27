package com.duoc.steps;


import com.duoc.Utilidades.ExcelUtils;
import com.duoc.Utilidades.ScreensUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static com.duoc.steps.CommonsDriverSteps.driver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUsSteps {


    @Given("el navegador está abierto")
    public void el_navegador_esta_abierto() {

    }

    @Given("el usuario está en la página principal")
    public void el_usuario_esta_en_la_pagina_principal() {
        driver.get("http://demo.testfire.net/");  // IMPORTANTE: http, no https
        driver.manage().window().maximize();
    }


    @When("el usuario accede a la opción Contact Us")
    public void Oprime_opcion_contact_us() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/form/table/tbody/tr[1]/td[2]/a[2]")));
        link.click();
        String obj = "oprime_opcion_contactUsa";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");


    }

    @When("el usuario abre el formulario de contacto")
    public void oprime_link_formulario() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div[1]/p[2]/a")));
        link.click();
        String obj = "oprime_enlace_para_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");

    }

    @When("el usuario ingresa el nombre de la fila {string}")
    public void ingresarNombre(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")).click();
        int row = Integer.parseInt(fila);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")));
        name.clear();
        name.sendKeys(ExcelUtils.getCellData(row,0).toString());
        String obj = "Ingresa_nombre_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("el usuario ingresa el email de la fila {string}")
    public void ingresa_email_address(String fila) throws Exception {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")).click();
        int row = Integer.parseInt(fila);
        WebDriverWait wait= new WebDriverWait(driver, 60);
        WebElement email_addr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")));
        email_addr.clear();
        email_addr.sendKeys(ExcelUtils.getCellData(row, 1));
        String obj = "Ingresa_email_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("el usuario ingresa el propósito de la fila {string}")
    public void ingresa_asunto(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")).click();
        int row = Integer.parseInt(fila);
        WebDriverWait wait= new WebDriverWait(driver, 60);
        WebElement subject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")));
        subject.clear();
        subject.sendKeys(ExcelUtils.getCellData(row, 2));
        String obj = "Ingresa_el_asunto_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("el usuario ingresa los comentarios de la fila {string}")
    public void ingresar_los_comentarios_preguntas(String fila) throws Exception{
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")).click();
        int row = Integer.parseInt(fila);
        WebDriverWait wait= new WebDriverWait(driver, 60);
        WebElement comments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")));
        comments.clear();
        comments.sendKeys(ExcelUtils.getCellData(row, 3));
        String obj = "Ingresa_comentarios_o_preguntas_al_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @When("el usuario envía el formulario de contacto")
    public void enviarFormulario() throws IOException {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement submit = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[1]"));
        submit.click();
        String obj = "envia_el_formulario";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");


    }

    @When("el usuario limpia el formulario")
    public void limpiarFormulario() throws IOException {
        driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[2]")).click();
        WebElement clearBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[2]"));
        clearBtn.click();

        String obj = "Limpia_el_formulario";
        ScreensUtils.captureScreenShot(driver,"Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }

    @Then("debería mostrarse confirmación de envío exitoso para la fila {string}")
    public void verificarConfirmacionEnvio(String fila) throws Exception {
        int row = Integer.parseInt(fila);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/p")));

        String mensajeObtenido = mensaje.getText();
        String mensajeEsperado = ExcelUtils.getCellData(row, 4);

        assertTrue(mensajeObtenido.contains(mensajeEsperado));

        String obj = "mensaje_contacto_exitoso";
        ScreensUtils.captureScreenShot(driver, "Evidencia_ContactUs\\"+obj+" "+ScreensUtils.GetTimeStampValue()+".png");
    }


    @Then("todos los campos deberían estar vacíos")
    public void verificarCamposVacios() {
        String name = driver.findElement(
                By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")
        ).getAttribute("value");
        String email = driver.findElement(
                By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")
        ).getAttribute("value");
        String subject = driver.findElement(
                By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")
        ).getAttribute("value");
        String comments = driver.findElement(
                By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")
        ).getAttribute("value");

        assertTrue(name.isEmpty());
        assertTrue(email.isEmpty());
        assertTrue(subject.isEmpty());
        assertTrue(comments.isEmpty());
    }


    @Then("deberían mostrarse mensajes de error por campos inválidos")
    public void verificarErroresFormulario() {
        String errorNombre = driver.findElement(
                By.xpath("//span[contains(text(),'Name is required')]")
        ).getText();
        String errorEmail = driver.findElement(
                By.xpath("//span[contains(text(),'Email is invalid')]")
        ).getText();
        assertTrue(errorNombre.contains("required"));
        assertTrue(errorEmail.contains("invalid"));
    }

}
