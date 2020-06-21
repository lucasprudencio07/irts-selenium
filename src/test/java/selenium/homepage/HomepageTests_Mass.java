package selenium.homepage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import selenium.base.BaseTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HomepageTests_Mass extends BaseTests {



    @ParameterizedTest
    @CsvFileSource(resources = "/test-mass_login.csv", numLinesToSkip = 1, delimiter = ',')
    public void testValidateLoginAtHeader_Mass(String test_name, String emailCSV, String passwordCSV, String usernameCSV, String expected_resultCSV) {

        homepage.clickSignInButton().fillLoginForm(emailCSV, passwordCSV);

        Boolean expectedResult;

        if (expected_resultCSV.equals("positive"))
            expectedResult = Boolean.TRUE;
        else
            expectedResult = Boolean.FALSE;

        assertThat(homepage.getLoginNameAtHeaderBoolean("Lucas Santos"), is(equalTo(expectedResult)));

        takeScreenshot(test_name);

        if (expectedResult)
            homepage.clickSingOutButton();

        System.out.println("Test Name: " + test_name + " \nExpected Result: " + expected_resultCSV);

        loadInitialPage();
    }

}
