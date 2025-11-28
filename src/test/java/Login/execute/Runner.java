package Login.execute;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Login",
        glue = {"Login.steps"},
        plugin = {"pretty"}
)
public class Runner {
}
