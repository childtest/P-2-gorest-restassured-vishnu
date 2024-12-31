package com.gorest.testbase;

import com.gorest.utils.PropertyReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeMethod
    public void inIT() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = propertyReader.getProperty("port");

        System.setProperty("global.token", "Bearer 46937b84327183120224abec9519be701f9a9efb1d94382f1f900983d8b0ec20");
    }

}