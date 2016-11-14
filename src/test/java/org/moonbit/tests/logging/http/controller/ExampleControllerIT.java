package org.moonbit.tests.logging.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by lmonkiewicz on 14.11.2016.
 */
public class ExampleControllerIT {

    @Test
    public void helloPost() throws Exception {
        HelloRequest request = new HelloRequest();
        request.setName("Łukasz");
        request.setAge(10);

        String json = new ObjectMapper().writeValueAsString(request);

        given().request()
            .contentType("application/json; charset=UTF-8;")
            .body(json)
        .when()
            .post("http://localhost:8080/app/example/hello")
        .then()
            .statusCode(200)
                .body("message", is("Hello Łukasz, you are 10 years old, that's great!"))
                .body("luckyNumbers.size()", is(6));
    }

}