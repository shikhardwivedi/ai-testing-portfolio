package com.shikhar.tests;

import com.shikhar.utils.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private String token;
    private int bookingId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";
        token = ApiUtils.getAuthToken();
        System.out.println("✅ Auth token obtained");
    }

    @Test(priority = 1)
    public void getAllBookingsTest() {
        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
        System.out.println("✅ GET all bookings passed");
    }

    @Test(priority = 2)
    public void createBookingTest() {
        String body = "{" +
                "\"firstname\": \"Shikhar\"," +
                "\"lastname\": \"Dubey\"," +
                "\"totalprice\": 500," +
                "\"depositpaid\": true," +
                "\"bookingdates\": {" +
                "\"checkin\": \"2024-01-01\"," +
                "\"checkout\": \"2024-01-07\"" +
                "}," +
                "\"additionalneeds\": \"Breakfast\"" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname",
                        equalTo("Shikhar"))
                .extract()
                .response();

        bookingId = response.jsonPath()
                .getInt("bookingid");
        System.out.println("✅ POST booking passed" +
                " — ID: " + bookingId);
    }

    @Test(priority = 3,
            dependsOnMethods = "createBookingTest")
    public void getBookingByIdTest() {
        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Shikhar"))
                .body("lastname", equalTo("Dubey"));
        System.out.println("✅ GET booking by ID passed");
    }

    @Test(priority = 4,
            dependsOnMethods = "createBookingTest")
    public void updateBookingTest() {
        String body = "{" +
                "\"firstname\": \"Shikhar\"," +
                "\"lastname\": \"Dubey\"," +
                "\"totalprice\": 999," +
                "\"depositpaid\": true," +
                "\"bookingdates\": {" +
                "\"checkin\": \"2024-02-01\"," +
                "\"checkout\": \"2024-02-07\"" +
                "}," +
                "\"additionalneeds\": \"Lunch\"" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(body)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("totalprice", equalTo(999))
                .body("additionalneeds",
                        equalTo("Lunch"));
        System.out.println("✅ PUT update booking passed");
    }

    @Test(priority = 5,
            dependsOnMethods = "createBookingTest")
    public void partialUpdateBookingTest() {
        String body = "{" +
                "\"firstname\": \"ShikharUpdated\"," +
                "\"totalprice\": 1500" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(body)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname",
                        equalTo("ShikharUpdated"))
                .body("totalprice", equalTo(1500));
        System.out.println(
                "✅ PATCH partial update passed");
    }

    @Test(priority = 6,
            dependsOnMethods = "createBookingTest")
    public void deleteBookingTest() {
        given()
                .header("Content-Type", "application/json")
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
        System.out.println("✅ DELETE booking passed");
    }

    @Test(priority = 7,
            dependsOnMethods = "deleteBookingTest")
    public void verifyDeletedBookingTest() {
        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);
        System.out.println(
                "✅ Verify DELETE — 404 confirmed");
    }
}