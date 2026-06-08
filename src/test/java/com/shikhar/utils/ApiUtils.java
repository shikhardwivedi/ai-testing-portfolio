package com.shikhar.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static String getAuthToken() {
        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";

        String body = "{" +
                "\"username\": \"admin\"," +
                "\"password\": \"password123\"" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String token = response.jsonPath()
                .getString("token");
        System.out.println("🔐 Token: " + token);
        return token;
    }
}