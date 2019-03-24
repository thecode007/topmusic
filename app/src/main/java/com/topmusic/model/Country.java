package com.topmusic.model;

public class Country {

    private String name;
    private int flagResource;

    public String getName() {
        return name;
    }

    public int getFlagResource() {
        return flagResource;
    }
    public Country(String name, int flagResource) {
        this.name = name;
        this.flagResource = flagResource;
    }
}
