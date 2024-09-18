package com.herokuapp.restFull;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
   protected RequestSpecification spec;

    @BeforeMethod
    public void setUp(){
         spec =  new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();

    }



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
