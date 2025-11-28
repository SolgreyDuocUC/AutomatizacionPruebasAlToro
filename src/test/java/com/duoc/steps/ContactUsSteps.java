package com.duoc.steps;

import com.duoc.utilidades.ExcelUtils;
import com.duoc.utilidades.ScreensUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ContactUsSteps {

    WebDriver driver;

    @Given("que puedo acceder a la url {string}")
    public void accederAUrl(String url) throws Exception {

        ExcelUtils.setExcelFileSheet("testData/dataTransferFondos.xlsx", "DatosUsuarios");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(url);

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Acceso_URL_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario accede a la opción Contact Us")
    public void accederAContactUs() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Contact Us")
        ));
        link.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Accede_ContactUs_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario abre el formulario de contacto")
    public void abrirFormularioContacto() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'online form')]")
        ));
        link.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Abrir_Formulario_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el nombre de la fila {string}")
    public void ingresarNombre(String fila) throws Exception {
        int row = Integer.parseInt(fila);

        WebElement name = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));

        name.clear();
        name.sendKeys(ExcelUtils.getCellData(row, 0));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Nombre_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el email de la fila {string}")
    public void ingresarEmail(String fila) throws Exception {
        int row = Integer.parseInt(fila);

        WebElement email = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("email_addr")));

        email.clear();
        email.sendKeys(ExcelUtils.getCellData(row, 1));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Email_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el propósito de la fila {string}")
    public void ingresarAsunto(String fila) throws Exception {
        int row = Integer.parseInt(fila);

        WebElement subject = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("subject")));

        subject.clear();
        subject.sendKeys(ExcelUtils.getCellData(row, 2));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Asunto_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa los comentarios de la fila {string}")
    public void ingresarComentarios(String fila) throws Exception {
        int row = Integer.parseInt(fila);

        WebElement comments = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("comments")));

        comments.clear();
        comments.sendKeys(ExcelUtils.getCellData(row, 3));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Comentarios_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario envía el formulario de contacto")
    public void enviarFormulario() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[1]")
        ));
        submit.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Enviar_Form_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @Then("debería mostrarse confirmación de envío exitoso para la fila {string}")
    public void verificarConfirmacionEnvio(String fila) throws Exception {

        WebElement mensaje = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));

        String texto = mensaje.getText().toLowerCase();

        boolean ok =
                texto.contains("thank you") ||
                        texto.contains("thank you for your comments") ||
                        texto.contains("your comments");

        assertTrue("No apareció mensaje de confirmación.\nTexto encontrado:\n" + texto, ok);

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Mensaje_Exito_" + ScreensUtils.GetTimeStampValue() + ".png");

        driver.quit();
    }

    @And("el usuario limpia el formulario")
    public void elUsuarioLimpiaElFormulario() throws Exception {

        WebElement clearBtn = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[2]")
                ));

        clearBtn.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Limpiar_Form_" + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @Then("todos los campos deberían estar vacíos")
    public void todosLosCamposDeberianEstarVacios() {

        WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
        WebElement email = driver.findElement(By.xpath("//input[@name='email_addr']"));
        WebElement subject = driver.findElement(By.xpath("//input[@name='subject']"));
        WebElement comments = driver.findElement(By.xpath("//textarea[@name='comments']"));

        assertEquals("", name.getAttribute("value"));
        assertEquals("", email.getAttribute("value"));
        assertEquals("", subject.getAttribute("value"));
        assertEquals("", comments.getAttribute("value"));

        driver.quit();
    }

    @Then("deberían mostrarse mensajes de error por campos inválidos")
    public void deberianMostrarseMensajesDeErrorPorCamposInvalidos() throws Exception {

        WebElement body = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body")
                ));

        String texto = body.getText().toLowerCase();

        boolean tieneAvisoEmailIncorrecto =
                texto.contains("email you gave is incorrect") ||
                        texto.contains("will not receive a response") ||
                        texto.contains("incorrect");

        assertTrue("No se encontró mensaje de error por email inválido.\nTexto encontrado:\n" + texto,
                tieneAvisoEmailIncorrecto);

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs\\Errores_Invalidos_" + ScreensUtils.GetTimeStampValue() + ".png");

        driver.quit();
    }
}
