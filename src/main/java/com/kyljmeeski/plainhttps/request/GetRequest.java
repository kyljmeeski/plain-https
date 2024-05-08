package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.Headers;
import com.kyljmeeski.plainhttps.Request;
import com.kyljmeeski.plainhttps.Response;
import com.kyljmeeski.plainhttps.response.PlainResponse;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Implementation of the {@link Request} interface for making HTTP GET requests.
 */
public class GetRequest implements Request {

    private final String host;
    private final RequestLine requestLine;
    private final Headers headers;

    /**
     * Constructs a new GetRequest with the specified URL string.
     *
     * @param url the URL string
     */
    public GetRequest(String url) {
        this(new Url(url));
    }

    /**
     * Constructs a new GetRequest with the specified URL object.
     *
     * @param url the Url object representing the request URL
     */
    public GetRequest(Url url) {
        this(
                url.host(),
                new GetRequestLine(url.uri()),
                new Headers()
        );
    }

    /**
     * Constructs a new GetRequest with the specified host, request line, and headers.
     *
     * @param host        the host to send the request to
     * @param requestLine the request line specifying the HTTP method, URI, and version
     * @param headers     the headers to include in the HTTP request
     */
    public GetRequest(String host, RequestLine requestLine, Headers headers) {
        this.host = host;
        this.requestLine = requestLine;
        this.headers = headers;
    }

    /**
     * Adds a header to the GET request.
     *
     * @param key   the header key
     * @param value the header value
     * @return the updated GetRequest object with the added header
     */
    @Override
    public Request with(String key, String value) {
        headers.add(key, value);
        return this;
    }

    /**
     * Executes the HTTP GET request with current settings and returns the response.
     *
     * @return an implementation of {@link Response} representing the result of the GET request execution
     */
    @Override
    public Response execute() {
        headers.add("Host", host);
        headers.add("Connection", "close");
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (SSLSocket socket = (SSLSocket) factory.createSocket(host, 443)) {
            OutputStream output = socket.getOutputStream();
            output.write(requestLine.bytes());
            output.write(headers.bytes());
            output.write("\r\n".getBytes());
            output.flush();
            InputStream input = socket.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            output.close();
            input.close();
            return new PlainResponse(buffer.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
