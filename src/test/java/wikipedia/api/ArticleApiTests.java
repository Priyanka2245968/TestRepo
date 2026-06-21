package wikipedia.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.http.ContentType;

public class ArticleApiTests {
  @Test
  public void getArticleContent() {
    given()
      .baseUri("https://en.wikipedia.org/api/rest_v1/page/html")
      .pathParam("title", "HTML")
      .contentType(ContentType.JSON)
    .when()
      .get("/HTML")
    .then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("html.body.id", equalTo("mw-content-text"))
      .body("html.body.content", equalTo("<div id=\"toc\" ...")); // Add more assertions
  }
}