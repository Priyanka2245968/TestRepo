package com.automation.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * Base Test Manager - Core Playwright wrapper with intelligent selectors
 * Supports relative XPath, generic iterators, and auto-healing
 */
public class BaseTestManager {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTestManager.class);
    
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    
    private final Properties config;
    private final String browserType;
    private final boolean headless;
    
    public BaseTestManager() {
        this.config = loadConfiguration();
        this.browserType = config.getProperty("browser", "chromium");
        // Set headless to true by default for CI/CD environments (GitHub Actions, etc.)
        // Override with -Dheadless=false for local testing with visible browser
        this.headless = Boolean.parseBoolean(config.getProperty("headless", "true"));
        
        logger.info("=====================================");
        logger.info("BaseTestManager Configuration:");
        logger.info("  Browser Type: {}", browserType);
        logger.info("  Headless Mode: {} ({})", headless, headless ? "No GUI" : "With GUI");
        logger.info("  System Property 'headless': {}", config.getProperty("headless", "NOT SET - using default"));
        logger.info("=====================================");
    }
    
    /**
     * Initialize Playwright browser and context
     */
    @BeforeMethod(alwaysRun = true)
    public void initializeBrowser() {
        logger.info("Initializing Playwright browser: {}", browserType);
        
        try {
            // Set environment variables for browser download
            if (System.getenv("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD") == null) {
                logger.info("Setting PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD to 0 to enable browser downloads");
                // Note: Environment variables set in Java will not affect subprocess downloads
                // Users should set this before running: $env:PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=0
            }
            
            // Add timeout mechanism for Playwright creation
            long startTime = System.currentTimeMillis();
            logger.info("Creating Playwright instance...");
            
            playwright = Playwright.create();
            
            long playwrightCreationTime = System.currentTimeMillis() - startTime;
            logger.info("Playwright instance created in {} ms", playwrightCreationTime);
            
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(headless)  // Use headless variable from configuration
                    .setSlowMo(100);     // Slight delay for better visibility
            
            logger.info("Launching {} browser in {} MODE...", 
                    browserType, 
                    headless ? "HEADLESS" : "HEADED (VISIBLE)");
            startTime = System.currentTimeMillis();
            
            browser = switch (browserType.toLowerCase()) {
                case "firefox" -> playwright.firefox().launch(launchOptions);
                case "webkit", "safari" -> playwright.webkit().launch(launchOptions);
                case "edge" -> playwright.chromium().launch(launchOptions.setChannel("msedge"));
                default -> playwright.chromium().launch(launchOptions);
            };
            
            long browserLaunchTime = System.currentTimeMillis() - startTime;
            logger.info("{} browser launched successfully in {} ms", browserType, browserLaunchTime);
            
            // Create context with common options
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080)
                    .setRecordVideoDir(Paths.get("target/videos"))
                    .setRecordVideoSize(1920, 1080);
            
            context = browser.newContext(contextOptions);
            
            // Enable tracing for debugging
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
            
            page = context.newPage();
            
            // Set default timeouts
            page.setDefaultTimeout(30000);
            page.setDefaultNavigationTimeout(60000);
            
            logger.info("Browser initialized successfully");
            
        } catch (Exception e) {
            logger.error("Failed to initialize browser. This might be due to browser binary download issues.", e);
            logger.error("SOLUTION: Before running tests, execute this command in PowerShell:");
            logger.error("$env:PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=0 ; java -cp path\\to\\playwright.jar com.microsoft.playwright.CLI install");
            logger.error("Or install browsers manually: playwright install");
            throw new RuntimeException("Browser initialization failed. Please ensure browser binaries are downloaded.", e);
        }
    }
    
    /**
     * Navigate to URL with intelligent waiting
     */
    public void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        
        try {
            page.navigate(url);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            
            // Additional wait for dynamic content
            page.waitForTimeout(1000);
            
        } catch (Exception e) {
            logger.error("Navigation failed to: {}", url, e);
            throw new RuntimeException("Failed to navigate to: " + url, e);
        }
    }
    
    /**
     * Smart form field filling with multiple selector strategies
     */
    public void fillFormField(String fieldIdentifier, String value) {
        logger.info("Filling field '{}' with value: {}", fieldIdentifier, value);
        
        String[] selectors = generateSmartSelectors(fieldIdentifier, "input,textarea,select");
        
        for (String selector : selectors) {
            try {
                Locator element = page.locator(selector);
                if (element.count() > 0 && element.first().isVisible()) {
                    element.first().clear();
                    element.first().fill(value);
                    logger.info("Successfully filled field using selector: {}", selector);
                    return;
                }
            } catch (Exception e) {
                logger.debug("Selector failed: {} - {}", selector, e.getMessage());
            }
        }
        
        throw new RuntimeException("Could not find form field: " + fieldIdentifier);
    }
    
    /**
     * Smart button clicking with multiple strategies
     */
    public void clickButton(String buttonIdentifier) {
        logger.info("Clicking button: {}", buttonIdentifier);
        
        String[] selectors = generateButtonSelectors(buttonIdentifier);
        
        for (String selector : selectors) {
            try {
                Locator element = page.locator(selector);
                if (element.count() > 0 && element.first().isVisible()) {
                    element.first().click();
                    logger.info("Successfully clicked button using selector: {}", selector);
                    return;
                }
            } catch (Exception e) {
                logger.debug("Button selector failed: {} - {}", selector, e.getMessage());
            }
        }
        
        throw new RuntimeException("Could not find button: " + buttonIdentifier);
    }
    
    /**
     * Generic table finder with relative XPath and iterators
     */
    public Locator findInTable(String tableSelector, String columnHeader, String searchText) {
        logger.info("Searching table for '{}' in column '{}' using table selector '{}'", 
                   searchText, columnHeader, tableSelector);
        
        try {
            // Find all table rows
            Locator table = page.locator(tableSelector);
            Locator headerRow = table.locator("thead tr, tr").first();
            Locator headers = headerRow.locator("th, td");
            
            // Find column index
            int columnIndex = -1;
            for (int i = 0; i < headers.count(); i++) {
                String headerText = headers.nth(i).textContent().trim();
                if (headerText.toLowerCase().contains(columnHeader.toLowerCase())) {
                    columnIndex = i;
                    break;
                }
            }
            
            if (columnIndex == -1) {
                throw new RuntimeException("Column '" + columnHeader + "' not found in table");
            }
            
            // Search in data rows
            Locator dataRows = table.locator("tbody tr, tr").nth(1).locator(".. tr");
            
            for (int i = 0; i < dataRows.count(); i++) {
                Locator row = dataRows.nth(i);
                Locator cell = row.locator("td").nth(columnIndex);
                
                if (cell.textContent().toLowerCase().contains(searchText.toLowerCase())) {
                    logger.info("Found matching row at index: {}", i);
                    return row;
                }
            }
            
            throw new RuntimeException("Text '" + searchText + "' not found in column '" + columnHeader + "'");
            
        } catch (Exception e) {
            logger.error("Table search failed", e);
            throw new RuntimeException("Table search failed: " + e.getMessage(), e);
        }
    }
    
    /**
     * Wait for text to appear with flexible selectors
     */
    public void waitForText(String text, int timeoutMs) {
        logger.info("Waiting for text to appear: {}", text);
        
        String[] selectors = {
            String.format("//*[contains(text(), '%s')]", text),
            String.format("text=%s", text),
            String.format("[title*='%s' i]", text),
            String.format("[alt*='%s' i]", text)
        };
        
        for (String selector : selectors) {
            try {
                page.locator(selector).first().waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs));
                logger.info("Text found using selector: {}", selector);
                return;
            } catch (Exception e) {
                logger.debug("Text selector failed: {} - {}", selector, e.getMessage());
            }
        }
        
        throw new RuntimeException("Text not found within timeout: " + text);
    }
    
    /**
     * Verify text is present on page
     */
    public boolean isTextPresent(String text) {
        try {
            waitForText(text, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Take screenshot
     */
    public String takeScreenshot(String name) {
        String screenshotPath = String.format("target/screenshots/%s_%d.png", name, System.currentTimeMillis());
        
        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));
            
            logger.info("Screenshot saved: {}", screenshotPath);
            return screenshotPath;
            
        } catch (Exception e) {
            logger.error("Screenshot failed", e);
            return null;
        }
    }
    
    /**
     * Close browser and clean up
     */
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        logger.info("Closing browser");
        
        try {
            if (context != null) {
                context.tracing().stop(new Tracing.StopOptions()
                        .setPath(Paths.get("target/traces/trace.zip")));
                context.close();
            }
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            logger.error("Error during browser cleanup", e);
        }
    }
    
    // Getters
    public Page getPage() { return page; }
    public BrowserContext getContext() { return context; }
    public Browser getBrowser() { return browser; }
    
    /**
     * Generate smart selectors for form fields
     */
    private String[] generateSmartSelectors(String identifier, String tagHint) {
        String lowerIdentifier = identifier.toLowerCase();
        
        return new String[] {
            // Direct attribute matches
            String.format("[name='%s']", identifier),
            String.format("[name='%s' i]", identifier),
            String.format("[id='%s']", identifier),
            String.format("[id='%s' i]", identifier),
            String.format("[placeholder*='%s' i]", identifier),
            
            // Relative XPath - following patterns
            String.format("//label[contains(text(), '%s')]/following-sibling::%s", identifier, tagHint.split(",")[0]),
            String.format("//label[contains(text(), '%s')]/parent::*/%s", identifier, tagHint.split(",")[0]),
            String.format("//text()[contains(., '%s')]/following::%s[1]", identifier, tagHint.split(",")[0]),
            
            // Common field patterns
            String.format("[data-testid*='%s']", lowerIdentifier),
            String.format("[class*='%s']", lowerIdentifier),
            String.format("%s[name*='%s']", tagHint.split(",")[0], lowerIdentifier),
            
            // Flexible text-based
            String.format("//*[@placeholder and contains(@placeholder, '%s')]", identifier),
            String.format("//*[@title and contains(@title, '%s')]", identifier)
        };
    }
    
    /**
     * Generate smart selectors for buttons
     */
    private String[] generateButtonSelectors(String identifier) {
        return new String[] {
            // Direct button text
            String.format("button:has-text('%s')", identifier),
            String.format("//button[contains(text(), '%s')]", identifier),
            String.format("//input[@type='submit' and contains(@value, '%s')]", identifier),
            
            // Link buttons
            String.format("//a[contains(text(), '%s')]", identifier),
            String.format("a:has-text('%s')", identifier),
            
            // Attribute-based
            String.format("[value*='%s' i]", identifier),
            String.format("[title*='%s' i]", identifier),
            String.format("[aria-label*='%s' i]", identifier),
            
            // Generic patterns
            String.format("button[class*='%s']", identifier.toLowerCase()),
            String.format("[data-testid*='%s']", identifier.toLowerCase()),
            String.format("[role='button']:has-text('%s')", identifier)
        };
    }
    
    /**
     * Load configuration from properties with system property override support
     */
    private Properties loadConfiguration() {
        Properties props = new Properties();
        try {
            // Set defaults
            props.setProperty("browser", "chromium");
            props.setProperty("headless", "true");  // Default to headless for CI/CD
            props.setProperty("timeout", "30000");
            
            // Override with system properties if provided (e.g., -Dbrowser=firefox -Dheadless=false)
            String browserSystem = System.getProperty("browser");
            if (browserSystem != null && !browserSystem.trim().isEmpty()) {
                props.setProperty("browser", browserSystem);
                logger.info("Browser overridden via system property: {}", browserSystem);
            }
            
            String headlessSystem = System.getProperty("headless");
            if (headlessSystem != null && !headlessSystem.trim().isEmpty()) {
                props.setProperty("headless", headlessSystem);
                logger.info("Headless mode overridden via system property: {}", headlessSystem);
            }
            
            // Load from file if exists
            // props.load(getClass().getResourceAsStream("/config.properties"));
            
        } catch (Exception e) {
            logger.warn("Could not load configuration, using defaults", e);
        }
        
        return props;
    }
}