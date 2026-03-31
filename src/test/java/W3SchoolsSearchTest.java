import { test, expect } from '@playwright/test';

test('Search for HTML tutorials on W3Schools', async ({ page }) => {
  await page.goto('https://www.w3schools.com');
  await page.waitForLoadState('networkidle');

  await page.getByRole('button', { name: 'Services' }).click();
  await page.locator('#tnb-google-search-input').fill('HTML');
  await page.keyboard.press('Enter');
  await page.waitForLoadState('networkidle');

  expect(page.locator('body')).toContainText('HTML Tutorial');
  expect(page.locator('body')).toContainText('HTML Introduction');
  expect(page.locator('body')).toContainText('HTML Editors');
  expect(page.locator('body')).not.toContainText('Error');
});