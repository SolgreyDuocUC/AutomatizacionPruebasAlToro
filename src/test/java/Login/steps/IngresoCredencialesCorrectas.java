package Login.steps;

import Login.utilities.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IngresoCredencialesCorrectas {

    public static WebDriver driver;

    private final By campoUsuario = By.xpath("//*[@id='uid']");
    private final By campoContrasena = By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input");
    private final By botonLogin = By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input");
    private final By mensajeBienvenida = By.xpath("//h1[contains(text(), 'Hello Admin User')]");


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que ingreso a la pagina de login en {string}")
    public void abrirPaginaLogin(String url) throws Exception {
        driver.get(url);
        Utility.captureScreenShot(driver,
                "evidencias_login/pagina_login_" + Utility.GetTimeStampValue() + ".png");
    }

    @When("ingreso el nombre de usuario {string}")
    public void ingresoUsuarioManual(String usuario) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement userInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(campoUsuario)
        );

        userInput.clear();
        userInput.sendKeys(usuario);

        Utility.captureScreenShot(driver,
                "evidencias_login/usuario_ingresado_" + Utility.GetTimeStampValue() + ".png");
    }

    @And("ingreso la contrasenia {string}")
    public void ingresoContrasenaManual(String pass) throws Exception {

        int intentos = 0;
        while (intentos < 10) {
            try {
                WebElement inputPass = driver.findElement(campoContrasena);
                if (inputPass.isDisplayed() && inputPass.isEnabled()) {
                    inputPass.clear();
                    Thread.sleep(200);
                    inputPass.sendKeys(pass);
                    break;
                }
            } catch (Exception e) {
                Thread.sleep(500);
            }
            intentos++;
        }

        Utility.captureScreenShot(driver,
                "evidencias_login/pass_ingresada_" + Utility.GetTimeStampValue() + ".png");
    }

    @And("presiono el boton de inicio de sesion")
    public void presionarBotonLogin() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(botonLogin)
        );

        Utility.captureScreenShot(driver,
                "evidencias_login/click_login_" + Utility.GetTimeStampValue() + ".png");

        loginBtn.click();
    }

    @Then("el sistema redirige al panel principal")
    public void validarRedireccion() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlContains("bank/main.jsp"));

        Utility.captureScreenShot(driver,
                "evidencias_login/redireccion_" + Utility.GetTimeStampValue() + ".png");

        String urlActual = driver.getCurrentUrl();

        if (!urlActual.contains("bank/main.jsp")) {
            throw new AssertionError("No redirigió al panel principal. URL actual: " + urlActual);
        }
    }

    @Then("se muestra el mensaje {string}")
    public void validarMensaje(String esperado) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(mensajeBienvenida)
        );

        String obtenido = mensaje.getText().trim();

        Utility.captureScreenShot(driver,
                "evidencias_login/mensaje_" + Utility.GetTimeStampValue() + ".png");

        if (!obtenido.contains(esperado)) {
            throw new AssertionError("Mensaje distinto. Esperado: " + esperado +
                    " | Obtenido: " + obtenido);
        }
    }
}