package Ejecutador;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"./src/test/resources/Features/FeatureFondos.feature"}, glue= {"DefinicionPasos"},
                 plugin = { "json:target/cucumber.json", "pretty","html:target/cucumber-reports/report.html",
                             "junit:target/cucumber-results.xml" },
                               monochrome = true)

public class Runner {

}
