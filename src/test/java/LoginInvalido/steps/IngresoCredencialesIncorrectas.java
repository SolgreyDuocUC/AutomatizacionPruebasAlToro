package LoginInvalido.steps;

import LoginInvalido.utilities.Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Login.steps.IngresoCredencialesCorrectas.driver;

public class IngresoCredencialesIncorrectas {

    private final By mensajeError = By.xpath("//*[@id='Login']/table/tbody/tr[4]/td[2]/span");

    @Then("se muestra el mensaje de error {string}")
    public void validarMensajeError(String esperado) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeError));
        String obtenido = error.getText().trim();
        Assert.assertTrue(obtenido.contains(esperado));
    }

    @And("no permanece en la URL principal {string}")
    public void validarPermanenciaURL(String urlProhibida) throws Exception {
        Utility.captureScreenShot(
                driver,
                "evidencias_login_error/validacion_url_" + Utility.GetTimeStampValue() + ".png"
        );

        String urlActual = driver.getCurrentUrl();

        if (urlActual.contains(urlProhibida)) {
            throw new AssertionError("Redirigió al panel principal: " + urlActual);
        }
    }
}
