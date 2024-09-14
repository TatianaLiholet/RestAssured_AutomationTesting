package com.herokuapp.restfullBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTest {
    @Test
    public void getBookingTest(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
        response.print();
        Assert.assertEquals(response.getStatusCode(), 200, "The code is not 200");
        //Verify All Fields
        SoftAssert softAssert = new SoftAssert();
        String actualfirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualfirstName, "Susan", "The value is wrong" );
        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Smith", "The value is wrong" );
         int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 793, "The value is wrong" );
        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid, "depositPaid should be true, but it is not");
        String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckin, "2015-04-21", "The value is wrong" );
        String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckout, "2022-12-18", "The value is wrong" );

        softAssert.assertAll();

        /*{"firstname":"Eric","lastname":"Wilson",
        "totalprice":873,"depositpaid":true,
        "bookingdates":{"checkin":"2023-01-13","checkout":"2024-03-03"}} */


    }
}
