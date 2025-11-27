package TransferenciaContactoBusqueda.steps;

import TransferenciaContactoBusqueda.Utilidades.ExcelUtils;
import TransferenciaContactoBusqueda.Utilidades.ScreensUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUsSteps {

    static WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() throws Exception {

        ExcelUtils.setExcelFileSheet("testData/dataTransferFondos.xlsx", "DatosUsuarios");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-infobars");
        options.addArguments("--user-data-dir=/tmp/chrome-test-profile");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, 10);   // ← AHORA SÍ FUNCIONA EN TODAS PARTES
    }

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver != null)
            driver.quit();
    }

    // -----------------------------------
    // GIVEN
    // -----------------------------------

    @Given("el navegador está abierto")
    public void el_navegador_esta_abierto() {
        // No haces nada aquí, está OK
    }

    @Given("el usuario está en la página principal")
    public void el_usuario_esta_en_la_pagina_principal() {
        driver.get("http://demo.testfire.net/");
        driver.manage().window().maximize();
    }

    // -----------------------------------
    // WHEN
    // -----------------------------------

    @When("el usuario accede a la opción Contact Us")
    public void Oprime_opcion_contact_us() throws Exception {

        WebElement link = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div[1]/form/table/tbody/tr[1]/td[2]/a[2]")
                )
        );
        link.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/oprime_opcion_contactUs "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario abre el formulario de contacto")
    public void oprime_link_formulario() throws Exception {

        WebElement link = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div[1]/p[2]/a")
                )
        );
        link.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/oprime_enlace_para_formulario "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el nombre de la fila {string}")
    public void ingresarNombre(String fila) throws Exception {

        int row = Integer.parseInt(fila);

        WebElement name = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")
                )
        );

        name.clear();
        name.sendKeys(ExcelUtils.getCellData(row, 0));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/Ingresa_nombre_al_formulario "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el email de la fila {string}")
    public void ingresa_email_address(String fila) throws Exception {

        int row = Integer.parseInt(fila);

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")
                )
        );
        email.clear();
        email.sendKeys(ExcelUtils.getCellData(row, 1));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/Ingresa_email_al_formulario "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa el propósito de la fila {string}")
    public void ingresa_asunto(String fila) throws Exception {

        int row = Integer.parseInt(fila);

        WebElement subject = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")
                )
        );
        subject.clear();
        subject.sendKeys(ExcelUtils.getCellData(row, 2));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/Ingresa_el_asunto "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario ingresa los comentarios de la fila {string}")
    public void ingresar_los_comentarios_preguntas(String fila) throws Exception {

        int row = Integer.parseInt(fila);

        WebElement comments = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")
                )
        );
        comments.clear();
        comments.sendKeys(ExcelUtils.getCellData(row, 3));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/Ingresa_comentarios "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario envía el formulario de contacto")
    public void enviarFormulario() throws IOException {

        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[1]")
                )
        );
        submit.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/envia_el_formulario "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @When("el usuario limpia el formulario")
    public void limpiarFormulario() throws IOException {

        WebElement clearBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[6]/td[2]/input[2]")
                )
        );
        clearBtn.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/Limpia_el_formulario "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    // -----------------------------------
    // THEN
    // -----------------------------------

    @Then("debería mostrarse confirmación de envío exitoso para la fila {string}")
    public void verificarConfirmacionEnvio(String fila) throws Exception {

        int row = Integer.parseInt(fila);

        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/p")
                )
        );

        String mensajeObtenido = mensaje.getText();
        String mensajeEsperado = ExcelUtils.getCellData(row, 4);

        assertTrue(mensajeObtenido.contains(mensajeEsperado));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_ContactUs/mensaje_contacto_exitoso "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    @Then("todos los campos deberían estar vacíos")
    public void verificarCamposVacios() {

        String name = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input")).getAttribute("value");
        String email = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input")).getAttribute("value");
        String subject = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[4]/td[2]/input")).getAttribute("value");
        String comments = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[5]/td[2]/textarea")).getAttribute("value");

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
