package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class WikipediaSearchPage {
    public WikipediaSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#searchInput")
    public WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    public WebElement searchButton;

    @FindBy(css = "a[title='HTML']")
    public WebElement htmlLink;

    @FindBy(css = "a[href='#bodyContent']")
    public WebElement aLink;

    @FindBy(css = "#vector-main-menu-dropdown-checkbox")
    public WebElement mainMenu;

    @FindBy(css = "button[name='pinnable-header.vector-main-menu.pin']")
    public WebElement pinMainMenuButton;

    @FindBy(css = "button[name='pinnable-header.vector-main-menu.unpin']")
    public WebElement unpinMainMenuButton;

    @FindBy(css = "a[href='/wiki/Main_Page']")
    public WebElement mainPageLink;

    @FindBy(css = "a[href='/wiki/Wikipedia:Contents']")
    public WebElement contentsLink;

    @FindBy(css = "a[href='/wiki/Portal:Current_events']")
    public WebElement currentEventsLink;

    @FindBy(css = "a[href='/wiki/Special:Random']")
    public WebElement randomLink;

    @FindBy(css = "a[href='/wiki/Wikipedia:About']")
    public WebElement aboutLink;
}