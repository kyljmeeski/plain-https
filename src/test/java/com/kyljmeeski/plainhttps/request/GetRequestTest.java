package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetRequestTest {

    @Test
    public void test() {
        Request request = new GetRequest("httpbin.org/get").with("Accept", "*/*");
        Response response = request.execute();
        Body body = response.body();

        assertEquals(
                body.content().length, Integer.parseInt(response.headers().get("Content-Length").orElse(""))
        );
    }

}