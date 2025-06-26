package com.example.servingwebcontent.model;

public class ObjectGeneral {
    protected String id;
    protected String name;

    public ObjectGeneral() {
        this.id = "";
        this.name = "";
    }

    public ObjectGeneral(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
