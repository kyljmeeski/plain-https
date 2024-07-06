package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;
import org.junit.Test;

import static org.junit.Assert.*;

public class DechunkedBodyTest {

    static class MockBody implements Body {
        private final byte[] content;
        private final String type;

        public MockBody(byte[] content, String type) {
            this.content = content;
            this.type = type;
        }

        @Override
        public byte[] content() {
            return content;
        }

        @Override
        public String type() {
            return type;
        }
    }

    @Test
    public void testDechunkedContent() {
        byte[] chunkedContent = {
                '5', '\r', '\n', 'H', 'e', 'l', 'l', 'o', '\r', '\n',
                '6', '\r', '\n', 'W', 'o', 'r', 'l', 'd', '!', '\r', '\n',
                '0', '\r', '\n', '\r', '\n'
        };
        Body mockBody = new MockBody(chunkedContent, "text/plain");
        DechunkedBody dechunkedBody = new DechunkedBody(mockBody);

        assertArrayEquals("HelloWorld!".getBytes(), dechunkedBody.content());
    }

    @Test
    public void testEmptyContent() {
        byte[] chunkedContent = {'0', '\r', '\n', '\r', '\n'};
        Body mockBody = new MockBody(chunkedContent, "text/plain");
        DechunkedBody dechunkedBody = new DechunkedBody(mockBody);

        assertArrayEquals(new byte[0], dechunkedBody.content());
    }

    @Test
    public void testSingleChunkContent() {
        byte[] chunkedContent = {
                '4', '\r', '\n', 'T', 'e', 's', 't', '\r', '\n',
                '0', '\r', '\n', '\r', '\n'
        };
        Body mockBody = new MockBody(chunkedContent, "text/plain");
        DechunkedBody dechunkedBody = new DechunkedBody(mockBody);

        assertArrayEquals("Test".getBytes(), dechunkedBody.content());
    }

    @Test
    public void testType() {
        Body mockBody = new MockBody(new byte[0], "application/json");
        DechunkedBody dechunkedBody = new DechunkedBody(mockBody);

        assertEquals("application/json", dechunkedBody.type());
    }

}