package com.kyljmeeski.plainhttps.request;

public class Url {

    private final String url;

    public Url(String url) {
        this.url = url;
    }

    public String host() {
        if (url.startsWith("https://")) {
            return url.substring(8).split("/", 2)[0];
        } else if (url.startsWith("http://")) {
            return url.substring(7).split("/", 2)[0];
        }
        return url.split("/", 2)[0];
    }

    public String uri() {
        if (url.startsWith("https://")) {
            return "/" + url.substring(8).split("/", 2)[1];
        } else if (url.startsWith("http://")) {
            return "/" + url.substring(7).split("/", 2)[1];
        }
        String[] parts = url.split("/", 2);
        if (parts.length > 1) {
            return "/" + url.split("/", 2)[1];
        }
        return "/";
    }

}
