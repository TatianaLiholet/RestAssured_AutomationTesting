package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingIdsTest  extends BaseTest {
    @Test
    public void getBookingIdsWithoutFilterTest(){

        Response responce = RestAssured.given(spec).get("/booking");
        responce.print();
        Assert.assertEquals(responce.getStatusCode(), 200, "Status code should be 200n but it not");
        List<Integer> bookingIds = responce.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "The list of booking ids is empty");
    }

//?firstname=sally&lastname=brown
    @Test
    public  void getBookingIdsWithFilterTest(){
        //Add query parameter:
        spec.queryParam("firstname", "Sally");
        spec.queryParam("lastname", "Brown");
        Response responce = RestAssured.given(spec).get("/booking");
        responce.print();
        Assert.assertEquals(responce.getStatusCode(), 200, "Status code should be 200n but it not");
        List<Integer> bookingIds = responce.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "The list of booking ids is empty");
    }


    }

