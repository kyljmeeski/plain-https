package com.kyljmeeski.plainhttps.response;

import com.kyljmeeski.plainhttps.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlainResponseTest {

    @Test
    public void test() {
        Response response = new PlainResponse("HTTP/1.1 200 OK\r\nConnection: close\r\n\r\nhello".getBytes());

        assertEquals(200, response.status().code());
        assertEquals("OK", response.status().phrase());

        assertEquals("close", response.headers().get("Connection").orElse(""));
        assertEquals("hello", new String(response.body().content()));
    }

}