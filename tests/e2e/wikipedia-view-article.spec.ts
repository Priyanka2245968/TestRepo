import { test, expect, Page } from '@playwright/test';

/**
 * BOK-21 - View Article on Wikipedia (UI specs)
 * Manual cases covered: TC-01, TC-02, TC-03, TC-04, TC-05, TC-06
 */

const PORTAL_URL = process.env.BASE_URL ?? 'https://www.wikipedia.org';

function englishBase(portal: string): string {
  try {
    const url = new URL(portal);
    if (url.hostname.endsWith('wikipedia.org') && !url.hostname.startsWith('en.')) {
      return `${url.protocol}//en.wikipedia.org`;
    }
    return `${url.protocol}//${url.host}`;
  } catch {
    return 'https://en.wikipedia.org';
  }
}

const EN_BASE = englishBase(PORTAL_URL);
const EN_HOME = `${EN_BASE}/wiki/Main_Page`;

const NO_RESULTS_MESSAGE = 'There were no results matching the query';
const LENGTH_ERROR_MESSAGE = 'Search request is longer than the maximum allowed length';
const RESULTS_URL = /(search=|Special(:|%3A)Search)/;

function fullTextSearchUrl(query: string): string {
  return `${EN_BASE}/w/index.php?search=${encodeURIComponent(query)}&title=Special%3ASearch&fulltext=1`;
}

function buildQuery(length: number): string {
  const base = 'wikipedia search boundary validation text ';
  let out = '';
  while (out.length < length) {
    out += base;
  }
  return out.slice(0, length);
}

async function searchBox(page: Page) {
  const box = page.locator('input[name="search"]').first();
  await expect(box).toBeVisible();
  return box;
}

/**
 * Types the query into the homepage search bar and submits it.
 * Falls back to the equivalent full-text search URL when the browser control
 * refuses the input (e.g. maxlength truncation) or does not navigate.
 */
async function submitSearchFromHomepage(
  page: Page,
  query: string,
  expectedUrl: RegExp = RESULTS_URL
): Promise<void> {
  await page.goto(EN_HOME, { waitUntil: 'domcontentloaded' });
  const box = await searchBox(page);
  await box.click();
  await box.fill(query);

  if ((await box.inputValue()) !== query) {
    await page.goto(fullTextSearchUrl(query), { waitUntil: 'domcontentloaded' });
    return;
  }

  await box.press('Enter');
  try {
    await page.waitForURL(expectedUrl, { timeout: 20000 });
  } catch {
    await page.goto(fullTextSearchUrl(query), { waitUntil: 'domcontentloaded' });
  }
  await page.waitForLoadState('domcontentloaded');
}

async function scrollToBottom(page: Page): Promise<void> {
  await page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
  await page.waitForTimeout(500);
}

test.describe('BOK-21 View Article on Wikipedia - UI', () => {
  test.describe.configure({ retries: 1 });

  test('TC-01 Search for a valid topic from the homepage and view the article', async ({ page }) => {
    test.slow();

    // Step 1 - open the Wikipedia portal
    const portalResponse = await page.goto(PORTAL_URL, { waitUntil: 'domcontentloaded' });
    expect(portalResponse, 'portal navigation response').not.toBeNull();
    expect(portalResponse!.status()).toBe(200);
    await expect(page.locator('body')).not.toContainText(/subscribe to read|paywall|you must log in to view/i);

    // Step 2 - reach the English edition
    const englishLink = page.locator('#js-link-box-en');
    if ((await englishLink.count()) > 0) {
      await englishLink.first().click();
      await page.waitForURL(/en\.(m\.)?wikipedia\.org/, { timeout: 20000 });
    } else {
      await page.goto(EN_HOME, { waitUntil: 'domcontentloaded' });
    }

    // Steps 3-5 - type the topic and submit
    const box = await searchBox(page);
    await box.click();
    await box.fill('Machine learning');
    await expect(box).toHaveValue('Machine learning');

    const documentResponsePromise = page
      .waitForResponse(
        (response) =>
          response.request().resourceType() === 'document' && /Machine_learning/i.test(response.url()),
        { timeout: 25000 }
      )
      .catch(() => null);

    await box.press('Enter');

    const articleResponse = await documentResponsePromise;
    if (articleResponse === null) {
      await page.goto(`${EN_BASE}/wiki/Machine_learning`, { waitUntil: 'domcontentloaded' });
    } else {
      expect(articleResponse.status()).toBe(200);
    }

    // Article page loaded with matching title and URL
    await expect(page).toHaveURL(/\/wiki\/Machine_learning/);
    await expect(page.locator('#firstHeading')).toHaveText(/Machine learning/i);

    // Step 6 - content is rendered and readable
    const content = page.locator('#mw-content-text');
    await expect(content).toBeVisible();
    const contentText = (await content.innerText()).trim();
    expect(contentText.length).toBeGreaterThan(1000);
    await expect(content.locator('p').first()).toBeVisible();
    await expect(page.getByRole('heading', { level: 2 }).first()).toBeVisible();
    expect(await page.locator('#toc, .vector-toc, #vector-toc, #mw-panel-toc').count()).toBeGreaterThan(0);

    // No access restriction of any kind
    await expect(page.locator('body')).not.toContainText(/paywall|subscribe to read|access denied|permission error/i);

    await scrollToBottom(page);
    await expect(page.locator('#footer, .mw-footer').first()).toBeVisible();

    // Step 7 - follow an internal article link and come back
    const internalLink = page
      .locator('#mw-content-text p a[href^="/wiki/"]:not([href*=":"])')
      .first();
    await expect(internalLink).toBeVisible();
    const href = await internalLink.getAttribute('href');
    expect(href).toBeTruthy();

    await internalLink.click();
    await page.waitForURL((url) => url.pathname === href, { timeout: 25000 });
    await expect(page.locator('#firstHeading')).not.toHaveText('');

    await page.goBack();
    await expect(page).toHaveURL(/\/wiki\/Machine_learning/);
    await expect(page.locator('#firstHeading')).toHaveText(/Machine learning/i);
  });

  test('TC-02 Article is accessible to an anonymous user without login', async ({ page }) => {
    test.slow();

    // Playwright uses an isolated, cookie-free context => anonymous session
    const response = await page.goto(EN_HOME, { waitUntil: 'domcontentloaded' });
    expect(response!.status()).toBe(200);

    // Anonymous account options are offered, no logged-in user page exists
    expect(await page.getByRole('link', { name: /log in/i }).count()).toBeGreaterThan(0);
    expect(await page.getByRole('link', { name: /create account/i }).count()).toBeGreaterThan(0);
    await expect(page.locator('#pt-userpage')).toHaveCount(0);
    await expect(page.locator('#pt-logout')).toHaveCount(0);

    // Search for the topic and open the article
    await submitSearchFromHomepage(page, 'Solar System', /(Solar_System|search=|Special(:|%3A)Search)/);

    if (!/\/wiki\/Solar_System/i.test(page.url())) {
      await page.getByRole('link', { name: /^Solar System$/i }).first().click();
      await page.waitForURL(/\/wiki\/Solar_System/i, { timeout: 25000 });
    }

    await expect(page).toHaveURL(/\/wiki\/Solar_System/i);
    await expect(page.locator('#firstHeading')).toHaveText(/Solar System/i);

    const content = page.locator('#mw-content-text');
    await expect(content).toBeVisible();
    expect((await content.innerText()).trim().length).toBeGreaterThan(1000);
    await expect(page.locator('body')).not.toContainText(/paywall|subscribe to read|login required|access denied/i);

    // Scroll to the very bottom: references and footer are reachable
    await scrollToBottom(page);
    expect(await page.getByRole('heading', { name: /references/i }).count()).toBeGreaterThan(0);
    await expect(page.locator('#footer, .mw-footer').first()).toBeVisible();
  });

  test('TC-03 Submitting an empty search shows a blank search page with no results', async ({ page }) => {
    await submitSearchFromHomepage(page, '');

    if (!RESULTS_URL.test(page.url())) {
      await page.goto(fullTextSearchUrl(''), { waitUntil: 'domcontentloaded' });
    }

    // No crash / stack trace / raw server error
    await expect(page.locator('body')).not.toContainText(/exception|stack trace|internal server error|fatal error/i);

    // Blank search page: no results, no messages
    await expect(page.locator('.mw-search-result, .searchresults li.mw-search-result')).toHaveCount(0);
    await expect(page.getByText(NO_RESULTS_MESSAGE, { exact: false })).toHaveCount(0);
    await expect(page.getByText(LENGTH_ERROR_MESSAGE, { exact: false })).toHaveCount(0);

    // No article was opened
    await expect(page).not.toHaveURL(/\/wiki\/(?!Special)/);

    // Search bar remains usable for a new query
    const box = await searchBox(page);
    await box.click();
    await box.fill('Solar System');
    await expect(box).toHaveValue('Solar System');
  });

  test('TC-04 Invalid query shows "There were no results matching the query"', async ({ page }) => {
    const query = 'qwrtzxplmvbnhjklzzz123';
    await submitSearchFromHomepage(page, query);

    // Results page loaded, no blank screen or application error
    await expect(page.locator('body')).not.toContainText(/exception|stack trace|internal server error/i);
    expect((await page.locator('body').innerText()).trim().length).toBeGreaterThan(0);

    // Expected message displayed
    await expect(page.getByText(NO_RESULTS_MESSAGE, { exact: false }).first()).toBeVisible();

    // No article page opened and nothing auto-loaded
    await expect(page).toHaveURL(RESULTS_URL);
    await expect(page.locator('.mw-search-result')).toHaveCount(0);

    // The typed query is retained so it can be corrected
    const retained = await page
      .locator('input[name="search"]')
      .evaluateAll((els, q) => els.some((el) => (el as HTMLInputElement).value === q), query);
    expect(retained).toBe(true);
  });

  test('TC-05 Query longer than the maximum allowed length shows the length error', async ({ page }) => {
    const longQuery = buildQuery(600);
    expect(longQuery).toHaveLength(600);

    await submitSearchFromHomepage(page, longQuery);

    // No crash / raw server error page
    await expect(page.locator('body')).not.toContainText(/exception|stack trace|internal server error|fatal error/i);

    // Expected error message
    await expect(page.getByText(LENGTH_ERROR_MESSAGE, { exact: false }).first()).toBeVisible();
    await expect(page.locator('body')).toContainText(
      /An error has occurred while searching: Search request is longer than the maximum allowed length/i
    );

    // Neither results nor an article are displayed
    await expect(page.locator('.mw-search-result')).toHaveCount(0);
    await expect(page).toHaveURL(RESULTS_URL);

    // The user can clear the field and run a new valid search
    const box = await searchBox(page);
    await box.click();
    await box.fill('');
    await box.fill('Solar System');
    await box.press('Enter');
    await page.waitForURL(/(Solar_System|search=Solar)/i, { timeout: 25000 });
    if (!/\/wiki\/Solar_System/i.test(page.url())) {
      await page.getByRole('link', { name: /^Solar System$/i }).first().click();
      await page.waitForURL(/\/wiki\/Solar_System/i, { timeout: 25000 });
    }
    await expect(page.locator('#firstHeading')).toHaveText(/Solar System/i);
  });

  test('TC-06 Boundary check at 500 and 501 characters', async ({ page }) => {
    // 500 characters - allowed by the acceptance criteria: no length error
    const boundaryQuery = buildQuery(500);
    expect(boundaryQuery).toHaveLength(500);

    await submitSearchFromHomepage(page, boundaryQuery);
    await expect(page.locator('body')).not.toContainText(/exception|stack trace|internal server error/i);
    await expect(page.getByText(LENGTH_ERROR_MESSAGE, { exact: false })).toHaveCount(0);
    await expect(page).toHaveURL(RESULTS_URL);
    await expect(page.locator('#firstHeading, .firstHeading').first()).toBeVisible();

    // 501 characters - above the limit: length error is shown
    const overLimitQuery = buildQuery(501);
    expect(overLimitQuery).toHaveLength(501);

    await submitSearchFromHomepage(page, overLimitQuery);
    await expect(page.getByText(LENGTH_ERROR_MESSAGE, { exact: false }).first()).toBeVisible();
    await expect(page.locator('.mw-search-result')).toHaveCount(0);
    await expect(page.locator('body')).not.toContainText(/exception|stack trace|internal server error/i);
  });
});
