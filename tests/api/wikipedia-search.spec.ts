import { test, expect } from '@playwright/test';

/**
 * BOK-21 - View Article on Wikipedia (API specs)
 * Validates the search + article endpoints backing the manual cases TC-01..TC-06.
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
const ACTION_API = `${EN_BASE}/w/api.php`;

const HEADERS = {
  Accept: 'application/json',
  'Api-User-Agent': 'BOK-21-playwright-tests/1.0 (qa-automation)'
};

function buildQuery(length: number): string {
  const base = 'wikipedia search boundary validation text ';
  let out = '';
  while (out.length < length) {
    out += base;
  }
  return out.slice(0, length);
}

type SearchResponse = {
  error?: { code?: string; info?: string };
  query?: {
    searchinfo?: { totalhits?: number };
    search?: Array<{ title: string; snippet?: string }>;
    userinfo?: { id?: number; name?: string; anon?: boolean | string };
  };
};

async function search(
  requestContext: import('@playwright/test').APIRequestContext,
  srsearch: string
) {
  const response = await requestContext.get(ACTION_API, {
    headers: HEADERS,
    params: {
      action: 'query',
      list: 'search',
      srsearch,
      srlimit: '10',
      format: 'json',
      formatversion: '2'
    }
  });
  const body = (await response.json()) as SearchResponse;
  return { response, body };
}

test.describe('BOK-21 View Article on Wikipedia - API', () => {
  test('TC-01 Valid topic search returns the matching article', async ({ request }) => {
    const { response, body } = await search(request, 'Machine learning');

    expect(response.status()).toBe(200);
    expect(body.error).toBeUndefined();
    expect(body.query?.search?.length ?? 0).toBeGreaterThan(0);
    expect(body.query?.searchinfo?.totalhits ?? 0).toBeGreaterThan(0);

    const titles = (body.query?.search ?? []).map((hit) => hit.title);
    expect(titles).toContain('Machine learning');
  });

  test('TC-01 Article content endpoint returns readable content with HTTP 200', async ({ request }) => {
    const summary = await request.get(`${EN_BASE}/api/rest_v1/page/summary/Machine_learning`, {
      headers: HEADERS
    });
    expect(summary.status()).toBe(200);
    const summaryBody = (await summary.json()) as { title?: string; extract?: string };
    expect(summaryBody.title).toMatch(/Machine learning/i);
    expect((summaryBody.extract ?? '').length).toBeGreaterThan(100);

    const html = await request.get(`${EN_BASE}/wiki/Machine_learning`, { headers: HEADERS });
    expect(html.status()).toBe(200);
    const markup = await html.text();
    expect(markup).toContain('Machine learning');
    expect(markup).not.toMatch(/subscribe to read|paywall|you must log in to view/i);
  });

  test('TC-02 Requests without credentials are served as anonymous', async ({ request }) => {
    const response = await request.get(ACTION_API, {
      headers: HEADERS,
      params: { action: 'query', meta: 'userinfo', format: 'json', formatversion: '2' }
    });
    expect(response.status()).toBe(200);

    const body = (await response.json()) as SearchResponse;
    expect(body.error).toBeUndefined();
    expect(body.query?.userinfo).toBeTruthy();
    expect(Boolean(body.query?.userinfo?.anon)).toBe(true);
    expect(body.query?.userinfo?.id).toBe(0);

    const article = await request.get(`${EN_BASE}/wiki/Solar_System`, { headers: HEADERS });
    expect(article.status()).toBe(200);
    expect(await article.text()).toContain('Solar System');
  });

  test('TC-03 Empty search term returns no results and no crash', async ({ request }) => {
    const { response, body } = await search(request, '');

    expect(response.status()).toBe(200);
    const emptyOutcome = Boolean(body.error) || (body.query?.search?.length ?? 0) === 0;
    expect(emptyOutcome).toBe(true);

    if (body.error) {
      expect(String(body.error.info ?? '')).not.toMatch(/longer than the maximum allowed length/i);
    } else {
      expect(body.query?.searchinfo?.totalhits ?? 0).toBe(0);
    }
  });

  test('TC-04 Invalid query returns zero hits (no results matching the query)', async ({ request }) => {
    const { response, body } = await search(request, 'qwrtzxplmvbnhjklzzz123');

    expect(response.status()).toBe(200);
    expect(body.error).toBeUndefined();
    expect(body.query?.search).toEqual([]);
    expect(body.query?.searchinfo?.totalhits ?? 0).toBe(0);
  });

  test('TC-04 Non-existent article path returns HTTP 404', async ({ request }) => {
    const response = await request.get(`${EN_BASE}/wiki/Qwrtzxplmvbnhjklzzz123`, {
      headers: HEADERS,
      failOnStatusCode: false
    });
    expect(response.status()).toBe(404);
    expect((await response.text()).length).toBeGreaterThan(0);
  });

  test('TC-05 Query of 600 characters returns the maximum-length error', async ({ request }) => {
    const longQuery = buildQuery(600);
    expect(longQuery).toHaveLength(600);

    const { response, body } = await search(request, longQuery);

    expect(response.status()).toBeLessThan(500);
    expect(body.error).toBeTruthy();
    expect(String(body.error?.info ?? '')).toMatch(/Search request is longer than the maximum allowed length/i);
    expect(body.query?.search ?? []).toEqual([]);
  });

  test('TC-06 Boundary: 500 characters accepted, 501 characters rejected', async ({ request }) => {
    const boundaryQuery = buildQuery(500);
    expect(boundaryQuery).toHaveLength(500);

    const allowed = await search(request, boundaryQuery);
    expect(allowed.response.status()).toBe(200);
    expect(String(allowed.body.error?.info ?? '')).not.toMatch(
      /Search request is longer than the maximum allowed length/i
    );

    const overLimitQuery = buildQuery(501);
    expect(overLimitQuery).toHaveLength(501);

    const rejected = await search(request, overLimitQuery);
    expect(rejected.response.status()).toBeLessThan(500);
    expect(rejected.body.error).toBeTruthy();
    expect(String(rejected.body.error?.info ?? '')).toMatch(
      /Search request is longer than the maximum allowed length/i
    );
  });
});
