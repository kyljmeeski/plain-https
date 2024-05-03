package com.kyljmeeski.plainhttps;

public class Status {

    private final byte[] status;

    public Status(byte[] status) {
        this.status = status;
    }

    public int code() {
        return 0;
    }

    public String phrase() {
        return null;
    }

}
