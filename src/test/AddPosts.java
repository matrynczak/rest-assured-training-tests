import builder.builder.PostBuilder;
import builder.contract.Post;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EnvProperties;

import static io.restassured.RestAssured.given;

public class AddPosts {
    private static final String testTitle = "Test Title";
    private static final String testBody = "Test Body of New Post";
    private static final String apiUrl = EnvProperties.getApiUrl();

    @Test
    public void shouldPostSingleItemWithSuccessCode() {
        Post post = new PostBuilder()
            .withUserId(101)
            .withTitle(testTitle)
            .withBody(testBody)
            .build();

        Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .contentType(ContentType.JSON)
                .body(post)
                .post("/posts/");

        Assert.assertEquals(resp.getStatusCode(), 201);
    }

    @Test
    public void shouldPostSingleItemWithCorrectData() {
        Post post = new PostBuilder()
                .withUserId(101)
                .withTitle(testTitle)
                .withBody(testBody)
                .build();

        Post resp = given()
                .log().all()
                .baseUri(apiUrl)
                .contentType(ContentType.JSON)
                .body(post)
                .post("/posts/")
                .as(Post.class);

        Assert.assertEquals(resp.getId(), 101);
        Assert.assertEquals(resp.getUserId(), 101);
        Assert.assertEquals(resp.getTitle(), testTitle);
        Assert.assertEquals(resp.getBody(), testBody);
    }
}
