package com.kyljmeeski.plainhttps;

import java.util.Arrays;

public class Status {

    private final byte[] status;

    public Status(byte[] status) {
        this.status = status;
    }

    public int code() {
        int start = -1;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 32) {
                if (start != -1) {
                    byte[] bytes = Arrays.copyOfRange(status, start + 1, i);
                    return ((bytes[0] - '0') * 100) + ((bytes[1] - '0') * 10) + (bytes[2] - '0');
                } else {
                    start = i;
                }
            }
        }
        return 0;
    }

    public String phrase() {
        int start = -1;
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 32) {
                if (start != -1) {
                    byte[] bytes = Arrays.copyOfRange(status, i + 1, status.length);
                    return new String(bytes);
                } else {
                    start = i;
                }
            }
        }
        return null;
    }

}
