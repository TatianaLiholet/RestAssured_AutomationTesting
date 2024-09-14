package com.herokuapp.restfullBooker;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingIdsTest {
    @Test
    public void getBookingIdsWithoutFilterTest(){
        Response responce = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        responce.print();


        System.out.println("--------------");
        System.out.println(responce);

        Assert.assertEquals(responce.getStatusCode(), 200, "Status code should be 200n but it not");
        List<Integer> bookingIds = responce.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "The list of booking ids is empty");
        SoftAssert softAssert =  new SoftAssert();
//        softAssert.assertEquals();
    }
}
