package com.hormigo.david.parkingmanager.draw.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private Status status;
    private final Date creationDate;

    /**
     * Fecha en la que ha sido sorteado
     */
    private Date drawDate;

    public Draw() {
        this("");
    }

    public Draw(String description) {
        this(description,Status.NEW);
    }

    public Draw(String description, Status status) {
        this.description = description;
        this.status = status;
        this.creationDate = new Date();
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
}
