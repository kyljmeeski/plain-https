package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.*;

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
//        todo: implement it
        return null;
    }

    @Override
    public Request containing(Body body) {
        return null;
    }

}
