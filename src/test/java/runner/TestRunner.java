package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/index.html"
        },
        tags = "@MercadoLibre"
)

public class TestRunner {
}