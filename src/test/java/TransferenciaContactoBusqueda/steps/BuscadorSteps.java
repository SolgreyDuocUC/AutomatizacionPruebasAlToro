package TransferenciaContactoBusqueda.steps;

import TransferenciaContactoBusqueda.Utilidades.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuscadorSteps {

    static WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() throws Exception {

        ExcelUtils.setExcelFileSheet("testData/dataTransferFondos.xlsx", "DatosUsuarios");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Evita que cargue datos o contraseñas guardadas
        options.addArguments("--disable-save-password-bubble"); // Desactiva sugerencias de contraseñas
        options.addArguments("--disable-notifications"); // Desactiva notificaciones
        options.addArguments("--disable-popup-blocking"); // Desactiva bloqueo de pop-ups
        options.addArguments("--no-default-browser-check"); // Evita aviso de navegador predeterminado
        options.addArguments("--disable-infobars"); // Quita la barra de "Chrome está siendo controlado..."
        options.addArguments("--user-data-dir=/tmp/chrome-test-profile");//fuerza a usar un perfil limpio de Chrome (sin contraseñas ni datos guardados).
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver !=null)
            driver.quit();
    }

    // ---------------------------------------------------------
    // STEPS
    // ---------------------------------------------------------

    @Given("el navegador está abierto")
    public void navegador_abierto() {
        driver.get("http://testfire.net/search.jsp");
    }

    @Given("el usuario oprime el panel de Search")
    public void oprimirPanelBusqueda() {

        // FULL XPATH del campo de búsqueda
        By buscador = By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/div/form/input[1]");

        wait.until(ExpectedConditions.elementToBeClickable(buscador)).click();
    }

    @When("el usuario ingresa el valor {string} en el panel de Search")
    public void ingresarValorBusqueda(String valor) {

        // FULL XPATH del campo de búsqueda
        By buscador = By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/div/form/input[1]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(buscador)).sendKeys(valor);
    }

    @And("realiza la búsqueda")
    public void realizarBusqueda() {

        // FULL XPATH del botón Search
        By botonSearch = By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/div/form/input[2]");

        wait.until(ExpectedConditions.elementToBeClickable(botonSearch)).click();
    }

    @Then("la página debería mostrar resultados relacionados con {string}")
    public void verificarResultado(String valor) {

        // Esperar que la URL cambie
        wait.until(ExpectedConditions.urlContains("searchresults.jsp"));

        assertTrue(
                driver.getCurrentUrl().contains("searchresults"),
                "La URL no contiene searchresults"
        );
    }

    @Then("debería mostrarse un mensaje de respuesta no encontrado")
    public void verificarMensajeNoEncontrado() {

        // FULL XPATH del mensaje "No results found"
        By mensaje = By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/p");

        WebElement textElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(mensaje)
        );

        String texto = textElement.getText().trim();

        // Validación exacta
        assertEquals("No results found.", texto);
    }
}
