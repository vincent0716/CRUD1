package com.example.crud_task;

public class Task {
    String name,date,developer,position,status,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task(String name, String date, String developer, String position, String status, String id) {
        this.name = name;
        this.date = date;
        this.developer = developer;
        this.position = position;
        this.status = status;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
