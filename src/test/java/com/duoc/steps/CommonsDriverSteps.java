package com.duoc.steps;

import com.duoc.Utilidades.ExcelUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonsDriverSteps {

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

}
