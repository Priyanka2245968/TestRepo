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

    @FindBy(xpath = "//button[contains(normalize-space(.),'HTML')]")
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

    @FindBy(css = "a[href='//en.wikipedia.org/wiki/Wikipedia:Contact_us']")
    public WebElement contactUsLink;

    @FindBy(css = "a[href='/wiki/Help:Contents']")
    public WebElement helpContentsLink;

    @FindBy(css = "a[href='/wiki/Help:Introduction']")
    public WebElement introductionLink;

    @FindBy(css = "a[href='/wiki/Wikipedia:Community_portal']")
    public WebElement communityPortalLink;

    @FindBy(css = "a[href='/wiki/Special:RecentChanges']")
    public WebElement recentChangesLink;

    @FindBy(css = "a[href='/wiki/Wikipedia:File_upload_wizard']")
    public WebElement fileUploadWizardLink;

    @FindBy(css = "a[href='/wiki/Special:SpecialPages']")
    public WebElement specialPagesLink;

    @FindBy(css = "a[href='/wiki/Special:Search']")
    public WebElement searchLink;

    @FindBy(css = "input[name='title']")
    public WebElement titleInput;

    @FindBy(css = "#vector-appearance-dropdown-checkbox")
    public WebElement appearanceDropdown;

    @FindBy(css = "a[href='https://donate.wikimedia.org/?wmf_source=donate&amp;wmf_medium=sidebar&amp;wmf_campaign=en.wikipedia.org&amp;uselang=en']")
    public WebElement donateLink;

    @FindBy(css = "a[href='/w/index.php?title=Special:CreateAccount&amp;returnto=Special%3ASearch&amp;returntoquery=go%3DGo%26search%3DA%2Bvery%2Blong%2Bquery%2Bof%2B500%252B%2Bcharacters']")
    public WebElement createAccountLink;

    @FindBy(css = "a[href='/w/index.php?title=Special:UserLogin&amp;returnto=Special%3ASearch&amp;returntoquery=go%3DGo%26search%3DA%2Bvery%2Blong%2Bquery%2Bof%2B500%252B%2Bcharacters']")
    public WebElement loginLink;

    @FindBy(css = "#vector-user-links-dropdown-checkbox")
    public WebElement personalToolsDropdown;

    @FindBy(css = "a[href='/wiki/Help:Searching']")
    public WebElement searchHelpLink;

    @FindBy(css = "#vector-variants-dropdown-checkbox")
    public WebElement languageVariantDropdown;

    @FindBy(css = "#vector-page-tools-dropdown-checkbox")
    public WebElement toolsDropdown;

    @FindBy(css = "button[name='pinnable-header.vector-page-tools.pin']")
    public WebElement pinToolsButton;
}