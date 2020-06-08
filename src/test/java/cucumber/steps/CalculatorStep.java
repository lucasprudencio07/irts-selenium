package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CalculatorStep {

    Integer sum_value = 0;

    @Given("that I access the calculator")
    public void that_I_access_the_calculator() {
        System.out.println("Accessing the calculator feature");
    }


    @When("I sum the {int} and {int}")
    public void i_sum_the_and(Integer value1, Integer value2) {
        sum_value = value1 + value2;
    }


    @Then("the result of this sum should be {int}")
    public void the_result_of_this_sum_should_be(Integer result) {
        Assertions.assertEquals(result, sum_value);
    }

}
