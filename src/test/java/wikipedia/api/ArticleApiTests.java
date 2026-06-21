package wikipedia.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class ArticleApiTests {
  @Test
  public void getArticleContent() {
    given()
      .baseUri("https://en.wikipedia.org/api/rest_v1/page/html")
      .pathParam("title", "HTML")
    .when()
      .get("/HTML")
    .then()
      .statusCode(200)
      .body("html.body.id", equalTo("mw-content-text"));
  }
}