package com.kyljmeeski.plainhttps;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusTest {

    @Test
    public void test() {
        int code = 200;
        String phrase = "OK";
        String statusLine = "HTTP/1.1 " + code + " " + phrase;

        Status status = new Status(statusLine.getBytes());

        assertEquals(200, status.code());
        assertEquals("OK", status.phrase());
    }

}