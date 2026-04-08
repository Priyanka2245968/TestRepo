package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class 1OpenBrowserAndGoToHttpsWwwGeeksforgeeksOrgPage {
    private WebDriver driver;

    public 1OpenBrowserAndGoToHttpsWwwGeeksforgeeksOrgPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() { driver.get("https://www.geeksforgeeks.org/"); }

    public void fill_search_input_box(String value) {
        driver.findElement(By.cssSelector("[placeholder="Search..."]")).sendKeys(value);
    }

    public void click_Search_button() {
        driver.findElement(By.cssSelector("a[href="https://www.geeksforgeeks.org/"]")).click();
    }

    public void fill_text_input(String value) {
        driver.findElement(By.cssSelector("[placeholder="Search..."]")).sendKeys(value);
    }

    // press keyboard

    public void click_Learn_Java___A_Beginners_Guide_for_2024___GeeksforGeeks() {
        driver.findElement(By.cssSelector("role=link[name="Learn Java - A Beginners Guide for 2024 - GeeksforGeeks"i]")).click();
    }

    public void click_Java_Basics() {
        driver.findElement(By.cssSelector("text=Java Basics")).click();
    }

    // wait networkidle

    public void click_10__Take_screenshots() {
        driver.findElement(By.cssSelector("a[href="https://www.geeksforgeeks.org/"]")).click();
    }

    // No assertions defined
}