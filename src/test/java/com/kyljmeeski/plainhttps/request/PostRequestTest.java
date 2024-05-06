package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.Request;
import com.kyljmeeski.plainhttps.Response;
import com.kyljmeeski.plainhttps.body.PlainBody;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostRequestTest {

    @Test
    public void test() {
        Request request = new PostRequest("httpbin.org/post")
                .containing(new PlainBody("hello".getBytes(), "text/plain"));
        Response response = request.execute();

        assertEquals(
                response.body().content().length,
                Integer.parseInt(response.headers().get("Content-Length").orElse(""))
        );

    }

}