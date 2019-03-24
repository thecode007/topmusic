package com.topmusic.model;

public class Song {

    private String name;
    private String artist;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public Song(String name, String artist, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
