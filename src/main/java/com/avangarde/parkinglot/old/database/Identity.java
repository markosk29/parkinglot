package com.avangarde.parkinglot.old.database;

public abstract class Identity {
    private int id;

    public Identity(int id) {
        this.id = id;
    }

    public Identity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
