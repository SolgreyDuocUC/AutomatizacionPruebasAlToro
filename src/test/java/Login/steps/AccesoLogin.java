package Login.steps;

import Login.utilities.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AccesoLogin {

    WebDriver driver;

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
        if (driver != null) driver.quit();
    }

    @Given("que ingreso en la página principal de AltoroMutual en {string}")
    public void queIngresoEnLaPaginaPrincipal(String url) throws IOException {
        driver.get(url);
        Utility.captureScreenShot(driver,
                "evidencias_login/pagina_principal_" + Utility.GetTimeStampValue() + ".png");
    }

    @When("presiono el botón para ir al formulario de login")
    public void presionoElBotonParaIrAlFormularioDeLogin() throws IOException {
        driver.findElement(By.linkText("Sign In")).click();
        Utility.captureScreenShot(driver,
                "evidencias_login/click_login_" + Utility.GetTimeStampValue() + ".png");
    }

    @Then("se muestra la página de login")
    public void seMuestraLaPaginaDeLogin() throws IOException {
        String urlActual = driver.getCurrentUrl();
        Utility.captureScreenShot(driver,
                "evidencias_login/login_cargado_" + Utility.GetTimeStampValue() + ".png");

        if (!urlActual.contains("login.jsp")) {
            throw new AssertionError("No se cargó la página de login. URL actual: " + urlActual);
        }
    }

    @Then("el formulario de autenticación se carga correctamente")
    public void elFormularioDeAutenticacionSeCargaCorrectamente() {

        boolean campoUsuario = driver.findElement(By.id("uid")).isDisplayed();
        boolean campoPassword = driver.findElement(By.id("passw")).isDisplayed();
        boolean botonLogin = driver.findElement(By.name("btnSubmit")).isDisplayed();

        if (!(campoUsuario && campoPassword && botonLogin)) {
            throw new AssertionError("El formulario de login no está completo");
        }
    }
}