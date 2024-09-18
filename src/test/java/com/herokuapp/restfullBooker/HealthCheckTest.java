package com.herokuapp.restfullBooker;

import com.herokuapp.restFull.BaseTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTest extends BaseTest {

    @Test
    public void healthCheckTest(){
        given().
                spec(spec).
                when().get("/ping").
               then().
                    assertThat().
                    statusCode(201);
    }

    @Test
    public void headersAndCookiesTest(){
        Response response = RestAssured.given(spec).
                cookies("Test cookie name", "Test cookie value").log().all().get("/ping");

        //Get headers
        Headers headers = response.getHeaders();
        System.out.println("Headers: " + headers);

        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName()+ ":" + serverHeader1.getValue()) ;

        String serverHeader2 =  response.getHeader("Server");
        System.out.println("Server:" + serverHeader2 );

        //Get cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies:" + cookies);
    }
}
