package org.moonbit.tests.logging.http.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by lmonkiewicz on 2016-11-09.
 */
@RestController
@RequestMapping("/example")
public class ExampleController {


    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ResponseEntity<HelloResponse> helloPost(@RequestBody HelloRequest request){

        final HelloResponse response = HelloResponse.builder()
                .message(String.format("Hello %s, you are %d years old, that's great!", request.getName(), request.getAge()))
                .luckyNumbers(new Random().ints(6, 1, 49).boxed().collect(Collectors.toList()))
                .build();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(response);

    }

}
