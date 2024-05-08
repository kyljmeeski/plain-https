package com.kyljmeeski.plainhttps;

/**
 * Interface representing an HTTP request.
 */
public interface Request {

    /**
     * Adds a header to the request.
     *
     * @param key   the header key
     * @param value the header value
     * @return the updated Request object with the added header
     */
    Request with(String key, String value);

    /**
     * Executes the HTTP request with current settings and returns the response.
     *
     * @return an implementation of {@link Response} representing the result of the request execution
     */
    Response execute();

}
