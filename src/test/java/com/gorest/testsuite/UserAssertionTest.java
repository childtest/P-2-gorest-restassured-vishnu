package com.gorest.testsuite;

import com.gorest.constant.EndPoints;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void verifyUserData() {

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get(EndPoints.USERS)
                .then().log().ifValidationFails().statusCode(200);


        response.body("size()", equalTo(20),
                "find{it.id == 7609177 }.name", equalTo("Chinmayananda Shah"),
                "name", hasItem("Rohana Rana"),
                "name", hasItems("Vaishnavi Pilla", "Tanushree Embranthiri", "Meena Agarwal V"),
                "find{it.id == 7609171 }.email", equalTo("singh_deeptimoyee@turcotte.test"),
                "find{it.name == 'Mohini Verma' }.status", equalTo("active"),
                "find{ it.name == 'Dr. Vaijayanti Guneta'}.gender", equalTo("male"));

    }

}
