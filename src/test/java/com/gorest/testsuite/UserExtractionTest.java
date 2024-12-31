package com.gorest.testsuite;

import com.gorest.constant.EndPoints;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void extractUserData() {

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get(EndPoints.USERS)
                .then().log().ifValidationFails().statusCode(200);

        System.out.println("All IDs : " +
                response.extract().path("id").toString());

        System.out.println("All Names : " +
                response.extract().path("name").toString());

        System.out.println("Name of 5th Object : " +
                response.extract().path("get(4).name").toString());

        System.out.println("Names of Inactive Users: " +
                response.extract().path("findAll{it.status == 'inactive'}.name"));

        System.out.println("Genders of Active Users: " +
                response.extract().path("findAll{it.status == 'active'}.gender"));

        System.out.println("Names of Female Users: " +
                response.extract().path("findAll{it.gender == 'female'}.name"));

        System.out.println("IDs of Male Users: " +
                response.extract().path("findAll { it.gender == 'male' }.id"));

        System.out.println("All Statuses: " +
                response.extract().path("status"));

        System.out.println("Email of Chandranath Chopra : " +
                response.extract().path("find { it.name == 'Chandranath Chopra' }.email"));

        System.out.println("Gender of ID 7609160: " +
                response.extract().path("find { it.id == 7609160 }.gender"));

    }
}
