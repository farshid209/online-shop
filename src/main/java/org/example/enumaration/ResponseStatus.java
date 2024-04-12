package org.example.enumaration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ResponseStatus {
    ACCEPTED(0, "Accepted"),
    FAILURE(1, "Failure");

    private final int type;
    private final String description;

    ResponseStatus(int type, String description) {
        this.type = type;
        this.description = description;
    }

    @JsonValue
    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ResponseStatus of(int type) {
        return Arrays.stream(values())
                .filter(v -> v.type == type)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
