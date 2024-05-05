package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.Headers;
import com.kyljmeeski.plainhttps.Request;
import com.kyljmeeski.plainhttps.Response;

public class GetRequest implements Request {

    private final String host;
    private final RequestLine requestLine;
    private final Headers headers;

    public GetRequest(String url) {
        this(new Url(url));
    }

    public GetRequest(Url url) {
        this(
                url.host(),
                new GetRequestLine(url.uri()),
                new Headers()
        );
    }

    public GetRequest(String host, RequestLine requestLine, Headers headers) {
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
//        todo: implement executing of get request
        return null;
    }
}
