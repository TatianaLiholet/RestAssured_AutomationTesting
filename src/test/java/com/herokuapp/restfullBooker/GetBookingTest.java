package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;




    public class GetBookingTest extends BaseTest {
        @Test(enabled = false)
        public void getBookingTest() {
            Response responseCreation = createBooking();
            responseCreation.print();

            //Add param:
            spec.pathParam("bookingId", responseCreation.jsonPath().getInt("bookingid"));
            Response response = RestAssured.given(spec).get("/booking/{bookingId}");
            response.print();
            Assert.assertEquals(response.getStatusCode(), 200, "The code is not 200");
            //Verify All Fields
            SoftAssert softAssert = new SoftAssert();
            String actualfirstName = response.jsonPath().getString("firstname");
            softAssert.assertEquals(actualfirstName, "Tetiana", "The value is wrong");
            String actualLastName = response.jsonPath().getString("lastname");
            softAssert.assertEquals(actualLastName, "Testing", "The value is wrong");
            int price = response.jsonPath().getInt("totalprice");
            softAssert.assertEquals(price, 345, "The value is wrong");
            boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
            softAssert.assertFalse(depositPaid, "depositPaid should be false, but it is not");
            String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
            softAssert.assertEquals(actualCheckin, "2018-01-01", "The value is wrong");
            String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
            softAssert.assertEquals(actualCheckout, "2019-01-01", "The value is wrong");

            softAssert.assertAll();

        }
         @Test

        public void getBookingXMLTest() {

            Response responseCreation = createBooking();
            responseCreation.print();

            //Add param:
            spec.pathParam("bookingId", responseCreation.jsonPath().getInt("bookingid"));

            Header xml = new Header("Accept", "application/xml");
            spec.header(xml);


            Response response = RestAssured.given(spec).get("/booking/{bookingId}");
            response.print();
            Assert.assertEquals(response.getStatusCode(), 200, "The code is not 200");
            //Verify All Fields
            SoftAssert softAssert = new SoftAssert();
            String actualfirstName = response.xmlPath().getString("booking.firstname");
            softAssert.assertEquals(actualfirstName, "Tetiana", "The value is wrong");
            String actualLastName = response.xmlPath().getString("booking.lastname");
            softAssert.assertEquals(actualLastName, "Testing", "The value is wrong");
            int price = response.xmlPath().getInt("booking.totalprice");
            softAssert.assertEquals(price, 345, "The value is wrong");
            boolean depositPaid = response.xmlPath().getBoolean("booking.depositpaid");
            softAssert.assertFalse(depositPaid, "depositPaid should be false, but it is not");
            String actualCheckin = response.xmlPath().getString("booking.bookingdates.checkin");
            softAssert.assertEquals(actualCheckin, "2018-01-01", "The value is wrong");
            String actualCheckout = response.xmlPath().getString("booking.bookingdates.checkout");
            softAssert.assertEquals(actualCheckout, "2019-01-01", "The value is wrong");

            softAssert.assertAll();

        }
    }
