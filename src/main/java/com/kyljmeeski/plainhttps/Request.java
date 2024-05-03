package com.kyljmeeski.plainhttps;

public interface Request {

    Request with(String key, String value);

    Response execute();

}
