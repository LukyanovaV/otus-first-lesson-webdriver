package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions( features = "src/features")
public class RunnerTest extends AbstractTestNGCucumberTests {
}
