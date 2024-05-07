package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextBodyTest {

    @Test
    public void test() {
        String text = "hello";

        Body body = new TextBody(text);

        assertEquals("text/plain", body.type());
        assertArrayEquals(text.getBytes(), body.content());
    }

}