package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikipediaSearchPage {
    private WebDriver driver;

    @FindBy(css = "#searchInput")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(normalize-space(.),'Search')]")
    private WebElement searchButton;

    @FindBy(css = "button[type='submit']")
    private WebElement htmlTutorialLink;

    public WikipediaSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String text) {
        searchInput.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickHtmlTutorialLink() {
        htmlTutorialLink.click();
    }

    public boolean isSearchResultsDisplayed() {
        return driver.getCurrentUrl().contains("https://en.wikipedia.org/wiki/Special:Search");
    }

    public boolean isHtmlTutorialPageDisplayed() {
        return driver.getCurrentUrl().contains("https://en.wikipedia.org/wiki/HTML_tutorial");
    }
}