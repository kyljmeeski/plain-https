package com.kyljmeeski.plainhttps.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlTest {

    @Test
    public void testUriWithNoPath() {
        Url url = new Url("example.com");
        assertEquals("/", url.uri());
    }

    @Test
    public void shouldWorkWithNoScheme() {
        Url url = new Url("example.com/page?day=monday");
        assertEquals("example.com", url.host());
        assertEquals("/page?day=monday", url.uri());
    }

    @Test
    public void shouldWorkWithHttp() {
        Url url = new Url("http://example.com/page?day=monday");
        assertEquals("example.com", url.host());
        assertEquals("/page?day=monday", url.uri());
    }

    @Test
    public void shouldWorkWithHttps() {
        Url url = new Url("https://example.com/page?day=monday");
        assertEquals("example.com", url.host());
        assertEquals("/page?day=monday", url.uri());
    }

}