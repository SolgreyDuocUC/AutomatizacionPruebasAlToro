package Login.steps;

import Login.utilities.ExcelUtils;
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

import java.util.concurrent.TimeUnit;

public class IngresoCredencialesCorrectas {

    WebDriver driver;

    // XPaths explicitamente pedidos
    private final By campoUsuario = By.xpath("//*[@id='uid']");
    private final By campoContrasena = By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[2]/td[2]/input");
    private final By botonLogin = By.xpath("/html/body/table/tbody/tr[2]/td[2]/div/form/table/tbody/tr[3]/td[2]/input");
    private final By mensajeBienvenida = By.xpath("//*[@id='_ctl0__ctl0_Content_Main_pnl_vAccount']/h1");

    int fila = 1;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String path = System.getProperty("user.dir") + "/testData/DatosUsuarios.xlsx";
        ExcelUtils.setExcelFileSheet(path, "Sheet1");
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

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement userInput = wait.until(ExpectedConditions.elementToBeClickable(campoUsuario));

        userInput.clear();
        userInput.sendKeys(usuario);

        ExcelUtils.setCellData(usuario, fila, 0);

        Utility.captureScreenShot(driver,
                "evidencias_login/usuario_ingresado_" + Utility.GetTimeStampValue() + ".png");
    }

    @And("ingreso la contrasenia {string}")
    public void ingresoContrasenaManual(String pass) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement passInput = wait.until(ExpectedConditions.elementToBeClickable(campoContrasena));

        passInput.clear();
        passInput.sendKeys(pass);

        ExcelUtils.setCellData(pass, fila, 1);

        Utility.captureScreenShot(driver,
                "evidencias_login/pass_ingresada_" + Utility.GetTimeStampValue() + ".png");
    }

    @And("presiono el boton de inicio de sesion")
    public void presionarBotonLogin() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(botonLogin));

        Utility.captureScreenShot(driver,
                "evidencias_login/click_login_" + Utility.GetTimeStampValue() + ".png");

        loginBtn.click();
    }

    @Then("el sistema redirige al panel principal")
    public void validarRedireccion() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("bank/main.jsp"));

        String urlActual = driver.getCurrentUrl();

        Utility.captureScreenShot(driver,
                "evidencias_login/redireccion_" + Utility.GetTimeStampValue() + ".png");

        if (!urlActual.contains("bank/main.jsp")) {
            ExcelUtils.setCellData("FAIL", fila, 2);
            throw new AssertionError("No redirigio al panel principal. URL: " + urlActual);
        }
    }

    @Then("se muestra el mensaje {string}")
    public void validarMensaje(String esperado) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeBienvenida));

        String obtenido = mensaje.getText().trim();

        ExcelUtils.setCellData(obtenido, fila, 3);

        Utility.captureScreenShot(driver,
                "evidencias_login/mensaje_" + Utility.GetTimeStampValue() + ".png");

        if (!obtenido.contains(esperado)) {
            ExcelUtils.setCellData("FAIL", fila, 2);
            throw new AssertionError("Mensaje distinto. Esperado: " + esperado +
                    " | Obtenido: " + obtenido);
        }

        ExcelUtils.setCellData("OK", fila, 2);
    }
}
