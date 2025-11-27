package DefinicionPasos;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.ExcelUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosTransferFondos {


    static WebDriver driver;
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
		  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		  if (driver !=null)
		  	driver.quit();
	}
	
	@Given("que puedo acceder a la url {string}")
	public void que_puedo_acceder_a_la_url(String url) {
		 driver.get(url);
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();	
		 driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}

	@Given("puedo ingresar a mi aplicacion con mi usuario y mi password {int}")
	public void puedo_ingresar_a_mi_aplicacion_con_mi_usuario_y_mi_password(Integer fila) throws Exception {
	    driver.findElement(By.xpath("//*[@id='LoginLink']/font")).click();
	    WebDriverWait wait= new WebDriverWait(driver,60);
	    WebElement userName= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='uid']")));
	    WebElement password=driver.findElement(By.xpath("//*[@id='passw']"));
	    WebElement btnLogin=driver.findElement(By.xpath("//*[@id='login']/table/tbody/tr[3]/td[2]/input"));
	    userName.clear();
	    userName.sendKeys(ExcelUtils.getCellData(fila, 0).toString());
	    password.clear();
	    password.sendKeys(ExcelUtils.getCellData(fila, 1).toString());
	    btnLogin.click();
	}

	@When("doy click en el enlace de transferencia de fondos {string}")
	public void doy_click_en_el_enlace_de_transferencia_de_fondos(String linkTransfer) {
	   WebDriverWait wait= new WebDriverWait(driver,60);
	   WebElement mensaje=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[2]/div/p")));
	   if (mensaje.isDisplayed())
	      driver.findElement(By.linkText(linkTransfer)).click();
	   else
		   System.err.println("Error al ingresar en la cuenta...");
		
	}

	@When("selecciono la cuenta de origen y destino {int}")
	public void selecciono_la_cuenta_de_origen_y_destino(Integer fila) throws Exception {
		//Seleccione la cuenta de origen
		driver.findElement(By.xpath("//*[@id='fromAccount']")).sendKeys(ExcelUtils.getCellData(fila, 2).toString());
		//Seleccione la cuenta destino
		driver.findElement(By.xpath("//*[@id='toAccount']")).sendKeys(ExcelUtils.getCellData(fila, 3).toString());
	}

	@When("coloco el monto a transferir {int}")
	public void coloco_el_monto_a_transferir(Integer fila) throws Exception {
	    driver.findElement(By.xpath("//*[@id='transferAmount']")).clear();
	    driver.findElement(By.xpath("//*[@id='transferAmount']")).sendKeys(ExcelUtils.getCellData(fila,4).toString());
	}

	@When("doy click en el boton de transferencia {string}")
	public void doy_click_en_el_boton_de_transferencia(String btnTransferir) throws InterruptedException {
        Thread.sleep(2000);
		driver.findElement(By.xpath(btnTransferir)).click();
        Thread.sleep(2000);
	}

    @Then("aparece el mensaje de transferencia correcta {int}")
	public void aparece_el_mensaje_de_transferencia_correcta(Integer fila) throws Exception {
	     String mensajeObtenido=driver.findElement(By.xpath("//*[@id='_ctl0__ctl0_Content_Main_postResp']/span")).getText();
	     String mensajeEsperado=ExcelUtils.getCellData(fila, 5);
	     if (mensajeObtenido.contains(mensajeEsperado))
	    	 ExcelUtils.setCellData("Prueba OK", fila, 6);
	     else
	    	 ExcelUtils.setCellData("Prueba NO OK", fila, 6);
	}
}
