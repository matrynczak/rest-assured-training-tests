import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EnvProperties;

import static io.restassured.RestAssured.given;

public class DeletePosts {
    private static final int postId = 1;
    private static final String apiUrl = EnvProperties.getApiUrl();

    // No actual test of getting item after delete request since it's fake API and item is not removed in fact
    @Test
    public void shouldDeleteSingleItem() {

        Response resp = given()
                .log().all()
                .baseUri(apiUrl)
                .contentType(ContentType.JSON)
                .delete("/posts/" + postId);

        Assert.assertEquals(resp.getStatusCode(), 200);
    }
}
