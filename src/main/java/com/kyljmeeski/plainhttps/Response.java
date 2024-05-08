package com.kyljmeeski.plainhttps;

/**
 * Interface representing an HTTP response.
 */
public interface Response {

    /**
     * Returns the status of the HTTP response.
     *
     * @return the status of the response as a {@link Status} object
     */
    Status status();

    /**
     * Returns the headers of the HTTP response.
     *
     * @return the headers of the response as a {@link Headers} object
     */
    Headers headers();

    /**
     * Returns the body of the HTTP response.
     *
     * @return the body of the response as an implementation of {@link Body}
     */
    Body body();

}
