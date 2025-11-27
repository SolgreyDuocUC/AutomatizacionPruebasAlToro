package TransferenciaContactoBusqueda.steps;

import TransferenciaContactoBusqueda.Utilidades.ExcelUtils;
import TransferenciaContactoBusqueda.Utilidades.ScreensUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TranferirFondosSteps {

    static WebDriver driver;

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
        driver.get("http://testfire.net");
    }

    @After
    public void tearDown() throws Exception {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver != null)
            driver.quit();
    }

    // ---------------------------------------------
    // GIVEN LOGIN
    // ---------------------------------------------
    @Given("puedo ingresar a mi aplicacion con mi usuario y mi password {int}")
    public void puedo_ingresar_a_mi_aplicacion_con_mi_usuario_y_mi_password(Integer fila) throws Exception {

        // Clic en Login
        driver.findElement(By.xpath(
                "/html/body/table[1]/tbody/tr/td[2]/table/tbody/tr/td[2]/a/font"
        )).click();

        WebDriverWait wait = new WebDriverWait(driver, 60);

        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[1]/td[2]/input")
        ));

        WebElement password = driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[2]/td[2]/input"
        ));

        WebElement btnLogin = driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[3]/td[2]/input"
        ));

        userName.clear();
        userName.sendKeys(ExcelUtils.getCellData(fila, 0));

        password.clear();
        password.sendKeys(ExcelUtils.getCellData(fila, 1));

        btnLogin.click();

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\iniciar_sesion_AltoroMutual " +
                        ScreensUtils.GetTimeStampValue() + ".png"
        );
    }

    // ---------------------------------------------
    // CLICK EN TRANSFER FUNDS
    // ---------------------------------------------
    @When("da click en el enlace de transferencia de fondos {string}")
    public void da_click_en_el_enlace_de_transferencia_de_fondos(String linkTransfer) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, 60);

        WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/p")
        ));

        if (mensaje.isDisplayed()) {
            driver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/ul/li[3]/a"
            )).click();
        } else {
            System.err.println("Error al ingresar en la cuenta...");
        }

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\oprime_boton_transferencia_fondos_para_ir_a_enlace "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    // ---------------------------------------------
    // SELECCIONAR CUENTAS
    // ---------------------------------------------
    @When("selecciono la cuenta de origen y destino {int}")
    public void selecciono_la_cuenta_de_origen_y_destino(Integer fila) throws Exception {

        driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[1]/td[2]/select"
        )).sendKeys(ExcelUtils.getCellData(fila, 2));

        driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[2]/td[2]/select"
        )).sendKeys(ExcelUtils.getCellData(fila, 3));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\Elegir_cuenta_de_origen_y_destino "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    // ---------------------------------------------
    // MONTO
    // ---------------------------------------------
    @When("coloco el monto a transferir {int}")
    public void coloco_el_monto_a_transferir(Integer fila) throws Exception {

        driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[3]/td[2]/input"
        )).clear();

        driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[3]/td[2]/input"
        )).sendKeys(ExcelUtils.getCellData(fila, 4));

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\Ingresa_monto_a_transferir "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    // ---------------------------------------------
    // CLICK BOTÓN TRANSFER
    // ---------------------------------------------
    @When("da click en el boton de transferencia {string}")
    public void da_click_en_el_boton_de_transferencia(String btnTransferir) throws InterruptedException, IOException {

        Thread.sleep(2000);

        driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/form/table/tbody/tr[4]/td[2]/input"
        )).click();

        Thread.sleep(2000);

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\Oprime_boton_para_realizar_transferencia "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }

    // ---------------------------------------------
    // ASSERT FINAL
    // ---------------------------------------------
    @Then("muestra mensaje de transferencia correcta {int}")
    public void muestra_mensaje_de_transferencia_correcta(Integer fila) throws Exception {

        String mensajeObtenido = driver.findElement(By.xpath(
                "/html/body/table[2]/tbody/tr/td[2]/div/span"
        )).getText();

        String mensajeEsperado = ExcelUtils.getCellData(fila, 5);

        if (mensajeObtenido.contains(mensajeEsperado))
            ExcelUtils.setCellData("Prueba OK", fila, 6);
        else
            ExcelUtils.setCellData("Prueba NO OK", fila, 6);

        ScreensUtils.captureScreenShot(driver,
                "Evidencia_Transferencia\\Transferencia_completa "
                        + ScreensUtils.GetTimeStampValue() + ".png");
    }
}
