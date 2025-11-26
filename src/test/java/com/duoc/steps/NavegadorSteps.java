package com.duoc.steps;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.duoc.steps.CommonsDriverSteps.driver;

public class NavegadorSteps {

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Given("que puedo acceder a la url {string}")
    public void que_puedo_acceder_a_la_url(String url) {
        driver.get(url);
        driver.manage().deleteAllCookies();
    }
}
