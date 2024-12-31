package com.gorest.testsuite;

import com.gorest.constant.EndPoints;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;
import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void extractPostsData(){

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get(EndPoints.POSTS)
                .then().log().ifValidationFails().statusCode(200);

        System.out.println("All Titles : " +
                response.extract().path("title").toString());

        List<String> total =  response.extract().path("data");
        System.out.println("Total Records : " +
               total.size());

        System.out.println("Body of 15th Record: " +
                response.extract().path("get(14).body"));

        System.out.println("User IDs: " +
                response.extract().path("user_id"));

        System.out.println("Titles of All Records: " +
                response.extract().path("title"));

        System.out.println("Titles for User ID 7609175: " +
                response.extract().path("findAll{ it.user_id == 7609175 }.title"));

        System.out.println("Body for ID 184660: " +
                response.extract().path("find { it.id == 184660 }.body"));
    }
}
