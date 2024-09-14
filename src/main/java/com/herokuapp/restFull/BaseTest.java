package com.herokuapp.restFull;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {
    protected  Response createBooking() {
        JSONObject body = new JSONObject();
        body.put("firstname", "Tetiana");
        body.put("lastname", "Testing");
        body.put("totalprice", 345);
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        body.put("bookingdates",bookingdates );
        body.put("additionalneeds", "Breakfast");

        Response response = RestAssured.given().contentType(ContentType.JSON).
                body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        return response;
    }
}
