package com.example.myapplication;

import java.io.Serializable;

public class Data implements Serializable {
    private String name;
    private String kinhDo;
    private String viDo;
    private String id;
    private  String secret;
     private String server;

    public String getName() {
        return name;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKinhDo(String kinhDo) {
        this.kinhDo = kinhDo;
    }

    public void setViDo(String viDo) {
        this.viDo = viDo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Data(String name, String kinhDo, String viDo, String id, String secret, String server) {
        this.name = name;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.id = id;
        this.secret = secret;
        this.server = server;
    }
}
