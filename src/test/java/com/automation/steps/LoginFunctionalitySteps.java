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

    @Given("^Enter username in the username field$")
    public void Enter_username_in_the_username_field() throws Throwable {
        // TODO: implement step
    }

    @Given("^Enter password in the password field$")
    public void Enter_password_in_the_password_field() throws Throwable {
        // TODO: implement step
    }

    @Given("^Click the Login button$")
    public void Click_the_Login_button() throws Throwable {
        // TODO: implement step
    }
}