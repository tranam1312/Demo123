package com.example.myapplication;

public class Photo {
    String id;
    String owner;
    String secret;
    String server;
     String title;

    public Photo(String id, String owner, String secret, String server,  String title) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.title =  title;
    }

    public Photo() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getTitle() {
        return title;
    }
}



