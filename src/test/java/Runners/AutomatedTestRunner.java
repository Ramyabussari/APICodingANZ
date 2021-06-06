package Runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"}, // format = {"pretty"},
        features = {"src/test/resources/Features"},
        glue = {"StepDefs"},
        tags = "@getRgisteredStation"
)
public class AutomatedTestRunner {
}
