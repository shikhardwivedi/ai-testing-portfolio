package com.shikhar.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void getAllBookings() {
        given()
                .when().get("/booking")
                .then().statusCode(200)
                .body("size()", greaterThan(0));
        System.out.println("✅ Get all bookings passed");
    }

    @Test
    public void createBooking() {
        String body = "{" +
                "\"firstname\": \"Shikhar\"," +
                "\"lastname\": \"Dubey\"," +
                "\"totalprice\": 500," +
                "\"depositpaid\": true," +
                "\"bookingdates\": {" +
                "\"checkin\": \"2024-01-01\"," +
                "\"checkout\": \"2024-01-07\"" +
                "}" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().post("/booking")
                .then().statusCode(200)
                .body("booking.firstname", equalTo("Shikhar"));
        System.out.println("✅ Get booking by ID passed");
    }
}