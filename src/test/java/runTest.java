import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.Test;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/login.feature",
        glue = {"stepdefinitions.java"},
        plugin = {"pretty"},
    monochrome = true)
public class runTest {

}
