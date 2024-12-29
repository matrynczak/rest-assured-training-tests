import builder.builder.PostBuilder;
import builder.contract.Post;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EnvProperties;

import static io.restassured.RestAssured.given;

public class UpdatePosts {
    private static final String testTitle = "Test Title Updated";
    private static final String testBody = "Test Body of New Post Updated";
    private static final int postId = 1;
    private static final String apiUrl = EnvProperties.getApiUrl();

    @Test
    public void shouldEditSingleItemWithCorrectData() {
        GetPosts getTests = new GetPosts();
        builder.contract.Post post = new PostBuilder()
                .withUserId(1)
                .withTitle(testTitle)
                .withBody(testBody)
                .build();

        getTests.shouldGetAlreadyExistingItem();

        Post resp = given()
                .log().all()
                .baseUri(apiUrl)
                .contentType(ContentType.JSON)
                .body(post)
                .put("/posts/" + postId)
                .as(Post.class);

        Assert.assertEquals(resp.getId(), 1);
        Assert.assertEquals(resp.getUserId(), 1);
        Assert.assertEquals(resp.getTitle(), testTitle);
        Assert.assertEquals(resp.getBody(), testBody);
    }
}
