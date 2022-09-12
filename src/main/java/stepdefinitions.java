
/*

This class contains the steps definitions and calls the class login page's methods to test the login features
from the cucumber steps.

 */




import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.LoginPage;
import java.io.IOException;


public class stepdefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user navigate to hudl website")
    public void userNavigateToHudlWebsite() throws Throwable {
        loginPage.navigateToUrl();
    }

    @When("user enters the valid username and password")
    public void userEntersTheValidUsernameAndPassword() throws InterruptedException {
        loginPage.logintoApp();

    }

    @Then("user should be successfully logged in to the application")
    public void userShouldBeSuccessfullyLoggedInToTheApplication() {
        loginPage.validateLogin();
    }


    @Then("i close the browser")
    public void iCloseTheBrowser() {

        loginPage.closeBrowser();
    }

    @When("user enters the invalid username and password")
    public void userEntersTheInvalidUsernameAndPassword() throws InterruptedException {
        loginPage.logininWithincorrectCredentials();
    }

    @Then("user should not be logged into the application")
    public void userShouldNotBeLoggedIntoTheApplication() {
        loginPage.invalidLogin();
    }

    @When("user selects login with organization but with invalid email")
    public void userSelectsLoginWithOrganizationButWithInvalidEmail() throws IOException, InterruptedException {

        loginPage.loginWithOrganization();


    }

    @Then("user is prompted to login with email and password")
    public void userIsPromptedToLoginWithEmailAndPassword() {

        loginPage.promptoLoginwithEmailAndPassword();
    }
}
