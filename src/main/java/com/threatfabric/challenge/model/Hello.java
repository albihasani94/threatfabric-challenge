package com.threatfabric.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hello {

    @Id
    private Long id;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
