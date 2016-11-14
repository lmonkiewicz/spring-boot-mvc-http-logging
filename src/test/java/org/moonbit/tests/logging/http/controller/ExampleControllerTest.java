package org.moonbit.tests.logging.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.containsString;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lmonkiewicz on 14.11.2016.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ExampleController.class)
public class ExampleControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void helloPost() throws Exception {
        HelloRequest request = new HelloRequest();
        request.setName("Łukasz");
        request.setAge(10);

        String json = new ObjectMapper().writeValueAsString(request);

        this.mvc.perform(post("/example/hello")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Łukasz, you are 10 years old, that's great!")));
    }

}