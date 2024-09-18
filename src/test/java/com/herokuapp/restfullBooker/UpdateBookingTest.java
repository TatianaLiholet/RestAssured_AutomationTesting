package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTest extends BaseTest{
    @Test
    public void updateBookingTest(){
        //Create booking
        Response responseCreate = createBooking();
        responseCreate.print();
        //Get bookingId:
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("firstname", "Olga");
        body.put("lastname", "Testing");
        body.put("totalprice", 123);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        body.put("bookingdates",bookingdates );
        body.put("additionalneeds", "Breakfast");

        //Update booking:
        Response responseUpdate = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).
                body(body.toString()).put("/booking/" + bookingid);

        responseUpdate.print();







        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "The code is not 200");
        //Verify All Fields
        SoftAssert softAssert = new SoftAssert();
        String actualfirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualfirstName, "Olga", "The value is wrong" );
        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Testing", "The value is wrong" );
        int price = responseUpdate.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 123, "The value is wrong" );
        boolean depositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid, "depositPaid should be false, but it is not");
        String actualCheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2018-01-01", "The value is wrong" );
        String actualCheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2019-01-01", "The value is wrong" );

        softAssert.assertAll();
    }
    }


