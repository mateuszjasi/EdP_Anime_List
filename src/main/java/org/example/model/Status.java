package org.example.model;

public enum Status {
    finished_airing("finished"),
    currently_airing("airing"),
    not_yet_aired("upcoming");

    private final String string;

    Status(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
