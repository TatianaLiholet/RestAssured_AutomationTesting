package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest extends BaseTest{
    @Test
    public void createBookingTest(){
        Response response = createBooking();
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "The code is not 200");
        //Verify All Fields
        SoftAssert softAssert = new SoftAssert();
        String actualfirstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(actualfirstName, "Tetiana", "The value is wrong" );
        String actualLastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName, "Testing", "The value is wrong" );
        int price = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(price, 345, "The value is wrong" );
        boolean depositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositPaid, "depositPaid should be false, but it is not");
        String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2018-01-01", "The value is wrong" );
        String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2019-01-01", "The value is wrong" );

        softAssert.assertAll();
    }
    }


