package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseTest {
    @Test
    public void deleteBooking(){
        Response response = createBooking();
        response.print();
        int bookingid = response.jsonPath().getInt("bookingid");
        //Delete booking

        Response deleteResponce = RestAssured.given(spec).auth().preemptive().basic("admin", "password123")
                .delete("/booking/" + bookingid);
        Assert.assertEquals(deleteResponce.getStatusCode(),201, "It wasn't delete");

        deleteResponce.print();

       //CheckIfWeDeleteTheBooking

        Response responseCheking = RestAssured.given(spec).get("/booking/" + bookingid);
        responseCheking.print();
        Assert.assertEquals(responseCheking.getBody().asString(), "Not Found", "Body should be 'Not Found', but it;s not");

    }

}
