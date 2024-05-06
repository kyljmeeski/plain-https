package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.*;
import com.kyljmeeski.plainhttps.response.PlainResponse;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PostRequest implements Request, RequestWithBody {

    private final String host;
    private final RequestLine requestLine;
    private final Headers headers;
    private Body body;

    public PostRequest(String url) {
        this(new Url(url));
    }

    public PostRequest(Url url) {
        this(url.host(), new PostRequestLine(url.uri()), new Headers());
    }

    public PostRequest(String host, RequestLine requestLine, Headers headers) {
        this.host = host;
        this.requestLine = requestLine;
        this.headers = headers;
    }

    @Override
    public Request with(String key, String value) {
        headers.add(key, value);
        return this;
    }

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
            if (body != null) {
                output.write(body.content());
            }
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

    @Override
    public Request containing(Body body) {
        headers.add("Content-Type", body.type());
        headers.add("Content-Length", String.valueOf(body.content().length));
        this.body = body;
        return this;
    }

}
