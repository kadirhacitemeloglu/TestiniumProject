package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/FeaturesFiles/BeymenTesting.feature"},
        glue = {"StepDefinitions"}
)

public class _01_TestRunner extends AbstractTestNGCucumberTests {


}
