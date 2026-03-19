package com.automation.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class to download Playwright browser binaries
 */
public class PlaywrightBrowserDownloader {
    
    public static void main(String[] args) throws Exception {
        System.out.println("========================================");
        System.out.println("Playwright Browser Downloader");
        System.out.println("========================================");
        System.out.println();
        
        String os = System.getProperty("os.name").toLowerCase();
        String javaHome = System.getProperty("java.home");
        String classPath = System.getProperty("java.class.path");
        
        System.out.println("System Information:");
        System.out.println("  OS: " + os);
        System.out.println("  Java Home: " + javaHome);
        System.out.println();
        
        System.out.println("Downloading Playwright browsers...");
        System.out.println();
        
        try {
            // Create a temporary test to trigger browser downloads
            System.out.println("[1/3] Initializing Playwright...");
            com.microsoft.playwright.Playwright playwright = com.microsoft.playwright.Playwright.create();
            
            System.out.println("[2/3] Downloading Chromium browser...");
            com.microsoft.playwright.Browser chromium = playwright.chromium().launch();
            System.out.println("✓ Chromium browser ready");
            chromium.close();
            
            System.out.println("[3/3] Closing Playwright...");
            playwright.close();
            
            System.out.println();
            System.out.println("========================================");
            System.out.println("✓ Browser download completed successfully!");
            System.out.println("========================================");
            System.out.println();
            System.out.println("You can now run tests with:");
            System.out.println("  mvn test");
            System.out.println("  mvn test -Dtest=SimpleHeadlessTest");
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("✗ Error downloading browsers:");
            System.err.println();
            e.printStackTrace();
            System.err.println();
            System.err.println("SOLUTION:");
            System.err.println("1. Ensure you have internet connection");
            System.err.println("2. Check that the CDN is reachable:");
            System.err.println("   https://cdn.playwright.dev/");
            System.err.println("3. Try running the download again");
            System.exit(1);
        }
    }
}
