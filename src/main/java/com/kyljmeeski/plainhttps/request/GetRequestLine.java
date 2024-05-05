package com.kyljmeeski.plainhttps.request;

public class GetRequestLine implements RequestLine {

    private final String uri;
    public final String version;

    public GetRequestLine(String uri) {
        this(uri, "HTTP/1.1");
    }

    public GetRequestLine(String uri, String version) {
        this.uri = uri;
        this.version = version;
    }

    @Override
    public byte[] bytes() {
        return String.format("GET %s %s\r\n", uri, version).getBytes();
    }
}
