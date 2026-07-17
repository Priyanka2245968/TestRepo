@Test
public void viewArticleOnWikipedia() {
    page.navigate("https://en.wikipedia.org/wiki/Main_Page");
    page.waitForLoadState(LoadState.NETWORKIDLE);
    WikipediaArticlePage articlePage = new WikipediaArticlePage(this);
    articlePage.searchForArticle("Automation");
    String articleText = articlePage.getArticleText();
    assertTrue(articleText.contains("Automation"));
}    @Test
    public void testViewArticle() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        page.navigate("https://en.wikipedia.org/wiki/Main_Page");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.searchBox.fill("Automation");
        pageObject.searchButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        pageObject.firstSearchResult.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String articleText = pageObject.articleContent.textContent();
        assertTrue(articleText.contains("Automation"));
    }
}    @Test
    public void testHappyPathSuccessfulWikipediaArticleSearchAndView() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToWikipedia();
        pageObject.searchForArticle("HTML");
        pageObject.clickSearchButton();
        pageObject.waitForSearchResults();
        pageObject.clickArticleLink("HTML");
        assertThat(page).hasTitle("HTML - Wikipedia");
        pageObject.takeScreenshot("html-article.png");
@Test
    public void testViewArticleOnWikipedia() {
        WikipediaArticlePage pageObject = new WikipediaArticlePage(this);
        pageObject.navigateToArticle("Java_(programming_language)");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        String articleTitle = pageObject.getArticleTitle();
        assertThat(pageObject.getArticleTitleLocator()).containsText("Java (programming language)");
    }    WikipediaArticlePage articlePage = new WikipediaArticlePage(testManager);
    articlePage.searchForArticle("Automation");
    articlePage.verifyArticleTitle("Automation");
}