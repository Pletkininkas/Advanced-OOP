package com.enorkus.academy.entity;

public enum CountryCode {
    LT("Lithuania"), LV("Latvia"), EE("Estonia"), SE("Sweden");

    private final String code;

    CountryCode(String code) {
        this.code = code;
    }

    public String display() {
        return code;
    }
}
