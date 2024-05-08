package com.kyljmeeski.plainhttps.request;

/**
 * Represents a URL and provides methods to extract host and URI parts.
 */
public class Url {

    private final String url;

    /**
     * Constructs a new Url object with the specified URL string.
     *
     * @param url the URL string
     */
    public Url(String url) {
        this.url = url;
    }

    /**
     * Extracts and returns the host part of the URL.
     *
     * @return the host part of the URL
     */
    public String host() {
        if (url.startsWith("https://")) {
            return url.substring(8).split("/", 2)[0];
        } else if (url.startsWith("http://")) {
            return url.substring(7).split("/", 2)[0];
        }
        return url.split("/", 2)[0];
    }

    /**
     * Extracts and returns the URI part of the URL.
     *
     * @return the URI part of the URL
     */
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
