package TransferenciaContactoBusqueda.Ejecutador;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"./src/test/resources/features/TransferirFondos.feature"}, glue= {"steps"},
                plugin = { "json:target/cucumber.json", "pretty","html:target/cucumber-reports/report.html",
                            "junit:target/cucumber-results.xml" },
        monochrome = true)

public class Runner {

}
