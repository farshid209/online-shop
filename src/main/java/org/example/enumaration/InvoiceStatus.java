package org.example.enumaration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum InvoiceStatus {
    PAYED(0, "Payed"),
    NOT_PAYED(1, "NotPayed");

    private final int type;
    private final String description;

    InvoiceStatus(int type, String description) {
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
    public static InvoiceStatus of(int type) {
        return Arrays.stream(values())
                .filter(v -> v.type == type)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
