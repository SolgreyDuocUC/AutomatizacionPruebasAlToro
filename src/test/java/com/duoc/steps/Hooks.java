package com.duoc.steps;

import com.duoc.Utilidades.ExcelUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static com.duoc.steps.CommonsDriverSteps.driver;

public class Hooks {

    @Before
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-infobars");
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    @Before("@Transferencias")
    public void setUpTransferData() throws Exception {
        ExcelUtils.setExcelFileSheet("testData/dataTransferFondos.xlsx", "DatosUsuarios");
    }

    @Before("@ContactUs")
    public void setUpContactUsData() throws Exception {
        ExcelUtils.setExcelFileSheet("testData/dataContactUs.xlsx", "DatosUsuarios");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
