package com.kyljmeeski.plainhttps;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeadersTest {

    @Test
    public void test() {
        Headers headers = new Headers();
        headers.add("Host", "example.com");
        headers.add("Connection", "close");

        assertEquals("example.com", headers.get("Host").orElse(""));
        assertEquals("close", headers.get("Connection").orElse(""));
    }

}