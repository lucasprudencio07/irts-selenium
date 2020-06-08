package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// This class is responsible for execute all the scenario in the specified path

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\lucas\\Documents\\Studying\\IRTS\\Web Testing\\irts-selenium\\src\\test\\resources", // where is the feature file
                 glue = "cucumber\\steps", // the package to get the steps to be executed
                 plugin = "pretty")
public class Runner {}