package org.example.model;

public enum Status {
    finished_airing("Finished"),
    currently_airing("Airing"),
    not_yet_aired("Upcoming");

    private final String string;

    Status(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
