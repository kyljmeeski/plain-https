package com.kyljmeeski.plainhttps.request;

public class PostRequestLine implements RequestLine {

    private final String uri;
    public final String version;

    /**
     * Constructs a new PostRequestLine with the specified URI, using HTTP/1.1 version by default.
     *
     * @param uri the URI of the resource to post to
     */
    public PostRequestLine(String uri) {
        this(uri, "HTTP/1.1");
    }

    /**
     * Constructs a new PostRequestLine with the specified URI and HTTP version.
     *
     * @param uri     the URI of the resource to post to
     * @param version the HTTP version to use (e.g., "HTTP/1.1")
     */
    public PostRequestLine(String uri, String version) {
        this.uri = uri;
        this.version = version;
    }

    /**
     * Converts the request line to a byte array.
     *
     * @return the request line as a byte array
     */
    @Override
    public byte[] bytes() {
        return String.format("POST %s %s\r\n", uri, version).getBytes();
    }

}
