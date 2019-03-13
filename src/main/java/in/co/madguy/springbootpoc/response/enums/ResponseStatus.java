package in.co.madguy.springbootpoc.response.enums;

import lombok.Getter;

public enum ResponseStatus {
    SUCCESS ("success"),
    FAIL ("fail"),
    ERROR ("error");

    @Getter
    private String code;

    ResponseStatus(String code) {
        this.code = code;
    }
}