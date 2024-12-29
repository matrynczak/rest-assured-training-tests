import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import builder.contract.Post;
import utils.EnvProperties;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPosts {
    private static final String apiUrl = EnvProperties.getApiUrl();
    private static final int postId = 1;

    @Test
    public void shouldGetSingleItemReturnsSuccessCode() {
         Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + postId);

        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void shouldGetAlreadyExistingItem() {
        String expectedTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String expectedBody = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";

        Post resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + postId)
                .as(Post.class);

        Assert.assertEquals(resp.getId(), 1);
        Assert.assertEquals(resp.getUserId(), 1);
        Assert.assertEquals(resp.getTitle(), expectedTitle);
        Assert.assertEquals(resp.getBody(), expectedBody);
    }

    @Test
    public void shouldGetAllItemsReturnsSuccessCode() {
        Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/");

        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void shouldGetAllExistingItems() {
        Post post = new Post();
        List<Post> posts = List.of(post);

        List<Post> resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/")
                .jsonPath().getList(".");

        Assert.assertEquals(resp.size(), 100);
    }

    @Test
    public void shouldReturnClientErrorIfGetForNonExistingItem() {
        int rndNum = RandomUtils.nextInt(101, 200);
        Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .when()
                .get("/posts/" + rndNum);

        Assert.assertEquals(resp.getStatusCode(), 404);
    }

}
