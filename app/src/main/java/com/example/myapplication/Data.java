package com.example.myapplication;

import java.io.Serializable;

public class Data implements Serializable {

    private String name;
    private String kinhDo;
    private String viDo;
    private String url;

    public String getName() {
        return name;
    }

    public String getKinhDo() {
        return kinhDo;
    }

    public String getViDo() {
        return viDo;
    }

    public String getUrl() {
        return url;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public Data(String name, String kinhDo, String viDo, String url) {
        this.name = name;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
        this.url = url;
    }
}
