import builder.contract.Comment;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EnvProperties;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCommentsOfPostTest {
    private static final String apiUrl = EnvProperties.getApiUrl();
    private static final int postId = 1;

    @Test
    public void shouldGetCommentsOfPostWithSuccesCode() {
        Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + postId + "/comments");

        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void shouldGetCorrectNumberOfCommentsOfPost() {
        Comment comment = new Comment();

        List <Comment> resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + postId + "/comments")
                .jsonPath().getList(".");

        Assert.assertEquals(resp.size(), 5);
    }

    @Test
    public void shouldGetCommentsOfPostWithCorrectValuesOfFirstComment() {
        int expectedPostId = 1;
        int expectedId = 1;
        String expectedName = "id labore ex et quam laborum";
        String expectedEmail = "Eliseo@gardner.biz";
        String expectedBody = "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium";

        List <Comment> resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + postId + "/comments")
                .jsonPath().getList(".", Comment.class);

        Comment firstComment = resp.getFirst();

        Assert.assertEquals(firstComment.getPostId(), expectedPostId);
        Assert.assertEquals(firstComment.getId(), expectedId);
        Assert.assertEquals(firstComment.getName(), expectedName);
        Assert.assertEquals(firstComment.getEmail(), expectedEmail);
        Assert.assertEquals(firstComment.getBody(), expectedBody);
    }
}
