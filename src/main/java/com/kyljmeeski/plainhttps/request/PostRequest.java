package com.kyljmeeski.plainhttps.request;

import com.kyljmeeski.plainhttps.Body;
import com.kyljmeeski.plainhttps.Request;
import com.kyljmeeski.plainhttps.RequestWithBody;
import com.kyljmeeski.plainhttps.Response;

public class PostRequest implements Request, RequestWithBody {

    @Override
    public Request with(String key, String value) {
        return null;
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public Request containing(Body body) {
        return null;
    }

}
