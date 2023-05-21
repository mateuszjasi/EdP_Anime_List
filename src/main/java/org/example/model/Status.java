package org.example.model;

public enum Status {
    finished_airing("Finished"),
    currently_airing("Airing"),
    not_yet_aired("Upcoming"),
    plan_to_watch("Plan to watch"),
    watching("Watching"),
    on_hold("On Hold"),
    completed("Completed"),
    dropped("Dropped");

    private final String string;

    Status(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
