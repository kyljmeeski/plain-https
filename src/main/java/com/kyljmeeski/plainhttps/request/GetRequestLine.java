package com.kyljmeeski.plainhttps.request;

/**
 * Implementation of {@link RequestLine} for creating a GET request line in an HTTP request.
 */
public class GetRequestLine implements RequestLine {

    private final String uri;
    public final String version;

    /**
     * Constructs a new GetRequestLine with the specified URI, using HTTP/1.1 version by default.
     *
     * @param uri the URI of the resource being requested
     */
    public GetRequestLine(String uri) {
        this(uri, "HTTP/1.1");
    }

    /**
     * Constructs a new GetRequestLine with the specified URI and HTTP version.
     *
     * @param uri     the URI of the resource being requested
     * @param version the HTTP version to use (e.g., "HTTP/1.1")
     */
    public GetRequestLine(String uri, String version) {
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
        return String.format("GET %s %s\r\n", uri, version).getBytes();
    }
}
