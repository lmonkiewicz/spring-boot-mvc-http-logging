package org.moonbit.tests.logging.http.controller;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by lmonkiewicz on 2016-11-09.
 */
@Data
public class HelloResponse {
    private String message;
    private List<Integer> luckyNumbers;

    public HelloResponse(){
    }

    @Builder
    public HelloResponse(String message, List<Integer> luckyNumbers){
        this.message = message;
        this.luckyNumbers = luckyNumbers;
    }
}
