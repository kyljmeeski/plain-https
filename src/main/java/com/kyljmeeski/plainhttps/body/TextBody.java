package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;

public class TextBody implements Body {

    private final String text;

    public TextBody(String text) {
        this.text = text;
    }

    @Override
    public byte[] content() {
        return text.getBytes();
    }

    @Override
    public String type() {
        return "text/plain";
    }

}
