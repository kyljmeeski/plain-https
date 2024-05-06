package com.kyljmeeski.plainhttps.request;

public class PostRequestLine implements RequestLine {

    private final String uri;
    public final String version;

    public PostRequestLine(String uri) {
        this(uri, "HTTP/1.1");
    }

    public PostRequestLine(String uri, String version) {
        this.uri = uri;
        this.version = version;
    }

    @Override
    public byte[] bytes() {
        return String.format("POST %s %s\r\n", uri, version).getBytes();
    }

}
