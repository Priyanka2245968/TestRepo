package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewHtmlTutorialOnW3SchoolsPage {
    private WebDriver driver;

    public ViewHtmlTutorialOnW3SchoolsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.w3schools.com"); }

    public void click_Tutorials() {
        driver.findElement(By.cssSelector("role=button[name="Tutorials"i]")).click();
    }

    public void click_HTML() {
        driver.findElement(By.cssSelector("role=link[name="HTML"i]")).click();
    }

    public void click_Learn_HTML() {
        driver.findElement(By.cssSelector("role=link[name="Learn HTML"i]")).click();
    }

    public boolean verify_home_page_loads_successfully() {
        // The W3Schools home page loads successfully
        return driver.findElement(By.cssSelector("home page loads successfully")).isDisplayed();
    }

    public boolean verify_Tutorials_menu_expands_showing_list() {
        // The Tutorials menu expands showing a list of topics
        return driver.findElement(By.cssSelector("Tutorials menu expands showing list")).isDisplayed();
    }

    public boolean verify_the_html_topic_page() {
        // The HTML topic page is displayed
        return driver.findElement(By.cssSelector("the html topic page")).isDisplayed();
    }

    public boolean verify_HTML_tutorial_introduction_page_loads() {
        // The HTML tutorial introduction page loads with beginner-friendly content
        return driver.findElement(By.cssSelector("HTML tutorial introduction page loads")).isDisplayed();
    }
}