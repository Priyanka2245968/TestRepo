package com.automation.steps;

import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

public class LoginFunctionalitySteps {
    private WebDriver driver;
    private LoginFunctionalityPage page;

    public LoginFunctionalitySteps() {
        // driver initialisation happens via hooks
    }

    @Given("^Navigate to the login page$")
    public void Navigate_to_the_login_page() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter username "tomsmith" in the username field$")
    public void Enter_username__tomsmith__in_the_usernam() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter password "SuperSecretPassword!" in the password field$")
    public void Enter_password__SuperSecretPassword___in() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the Login button$")
    public void Click_the_Login_button() throws Throwable {
        // TODO: implement step
    }
}