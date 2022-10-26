package com.thesis.fixable.technician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotNull;

enum Profession {
    PLUMBER("Plumber"),
    ELECTRICIAN("Electrician"),
    CARPENTER("Carpenter"),
    CLEANING_SERVICES("Cleaning services");

    private final String profession;

    Profession(String key) {
        this.profession = key;
    }

    @JsonCreator
    public static Profession fromString(@NotNull String professionStr) {
        return Profession.valueOf(professionStr.toUpperCase().replace(" ", "_"));
    }

    @JsonValue
    public String getProfession() {
        return profession;
    }
}
