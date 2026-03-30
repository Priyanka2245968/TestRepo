import { test, expect } from '@playwright/test';

/**
 * Test Case: TC_1
 * Title: Precondition
 * Auto-generated from normalized test case with live browser execution
 */
test.describe('Precondition', () => {
  test('TC_1 - Precondition', async ({ page }) => {
    // Step 1: Navigate to https://www.w3schools.com
    await page.goto('https://www.w3schools.com');
    await page.waitForLoadState('networkidle');

    // --- Assertions ---
    // Expected: Precondition User has an active internet connection Browser is opened (Chrome/Edge/Firefox) Test Steps Navigate to https://www.w3schools.com Click on the Search input box at the top Enter the keyword “HTML” Press Enter Expected Result Search results should be displayed Results should contain HTML-related tutorials No error or broken page should appear
    await expect(page.getByText(/search/i)).toBeVisible({ timeout: 10000 });
  });
});