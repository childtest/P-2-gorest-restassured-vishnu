package com.gorest.crudtest;

import com.gorest.constant.EndPoints;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static int userId = 0;

    static String name = "Child test";
    static String email = "test" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Test(priority = 1)
    public void createUser() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", System.getProperty("global.token"))
                .when()
                .body(userPojo)
                .post(EndPoints.USERS)
                .then().log().ifValidationFails().statusCode(201);

        userId = response.extract().path("id");
        System.out.println("user id is : " + userId);

    }


    @Test(priority = 2)
    public void readUserDataById() {

        ValidatableResponse response = given().log().ifValidationFails()
                .header("Authorization", System.getProperty("global.token"))
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then().log().ifValidationFails().statusCode(200);

        userId = response.extract().path("id");
        System.out.println("user id is : " + userId);

    }

    @Test(priority = 3)
    public void UpdateUserDataById() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(TestUtils.getRandomValue() + email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        ValidatableResponse response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", System.getProperty("global.token"))
                .pathParam("userId", userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 4)
    public void deleteUserById() {

        given().log().ifValidationFails()
                .header("Authorization", System.getProperty("global.token"))
                .pathParam("userId", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then()
                .statusCode(204);

        given()
                .log()
                .ifValidationFails()
                .header("Authorization", System.getProperty("global.token"))
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then().log().ifValidationFails().statusCode(404);
    }
}
