package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTests extends BaseTest {
    @Test
    public void UpdateBookingTest(){
        // Create booking
        Response responseCreate = createBooking();
        responseCreate.print();
        // Get bookingId of new booking
        int idOfBooking = responseCreate.jsonPath().getInt("bookingid");
        // Create JSON body

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname" , "James" );
        jsonObject.put("lastname" , "Brown" );

        Response patchUpdateResponce = RestAssured.given(spec).auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON).body(jsonObject.toString())
                .patch("/booking/" + idOfBooking);
        patchUpdateResponce.print();

        Assert.assertEquals(patchUpdateResponce.getStatusCode(), 200, "The code is wrong");
        SoftAssert softAssert = new SoftAssert();
        String name = patchUpdateResponce.jsonPath().getString("firstname");
        softAssert.assertEquals(name, "James");
        String lastName = patchUpdateResponce.jsonPath().getString("lastname");
        softAssert.assertEquals(lastName, "Brown");
        softAssert.assertAll();

    }
}
