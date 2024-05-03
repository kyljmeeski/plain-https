package com.kyljmeeski.plainhttps;

public interface Response {

    Status status();

    Headers headers();

    Body body();

}
