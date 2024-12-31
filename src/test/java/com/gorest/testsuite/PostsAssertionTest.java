package com.gorest.testsuite;

import com.gorest.constant.EndPoints;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void verifyPostsData() {

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get(EndPoints.POSTS)
                .then().log().ifValidationFails().statusCode(200);


        response.body("size()", equalTo(25),
                "find { it.id == 184639 }.title", equalTo("Aperio conatus vitium concido congregatio omnis demens spoliatio eos."),
                "user_id", hasItem(7609173),
                "user_id", hasItems(7609171, 7609169, 7609167),
                "find { it.user_id == 7609164 }.body", equalTo("Et vulgo dolore. Atavus et decipio. Non virga cavus. Stipes degero nesciunt. Urbanus coniuratio curriculum. Ipsam succedo allatus. Necessitatibus est solio. Occaecati summisse sint. Nemo decipio terga. Beatus crebro terminatio."));
    }

}
