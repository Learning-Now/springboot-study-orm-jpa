package com.livenow.week6.domain;

public class CustomDelimiter {

    private String value = ",|:";

    public CustomDelimiter(String value) {
        this.value += "|" + value;
    }

    public String getValue() {
        return value;
    }
}
