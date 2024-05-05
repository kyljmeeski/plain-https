package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;

public class PlainBody implements Body {

    private final byte[] content;
    private final String type;

    public PlainBody(byte[] content, String type) {
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
