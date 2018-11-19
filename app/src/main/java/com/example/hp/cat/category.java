package com.example.hp.cat;

import java.util.Date;

public class category {
    private String name;
    private String email;
    private String category1;
    private String pin;
    private Date created;
    private Date updated;
    private String objectId;
    

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }



    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }





}
