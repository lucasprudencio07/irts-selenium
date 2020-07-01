package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

// This class is responsible for execute all the scenario in the specified path

@RunWith(Cucumber.class)
@CucumberOptions(
                 //features = "C:\\Users\\lucas\\Documents\\Studying\\IRTS\\Web Testing\\irts-selenium-master\\src\\test\\resources\\features", // where is the feature file
                 features = "C:\\Users\\lprudencio\\Documents\\Courses & Studying\\IRTS\\Testing-Selenium\\src\\test\\resources\\features", // where is the feature file
                 glue = "cucumber\\steps", // the package to get the steps to be executed
                 tags = "@this",
                 plugin = "pretty")
public class Runner {}