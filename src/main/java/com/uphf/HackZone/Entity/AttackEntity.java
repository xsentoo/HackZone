package com.uphf.HackZone.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Attacks")
public class AttackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int attId;
    private String title;
    private String description;
    private String difficulty;
    private String flag;
    private int points;

    public AttackEntity(int attId, String title, String description, String difficulty, String flag) {
        this.attId = attId;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.flag = flag;
        this.points = points;
    }

    public AttackEntity() {

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int point) {
        this.points = points;
    }

    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
