package com.uphf.HackZone.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="UserHack")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String userMail;
    private String userPWD;
    private String level;
    private String userBadge;

    @Column(name="userDate")
    private LocalDate userDate;


    @Column(name = "point")
    private int point = 0;

    public UserEntity() {
    }

    public UserEntity(int userId, String userName, String userMail, String userPWD, String level, String userBadge, LocalDate userDate, int point) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPWD = userPWD;
        this.level = level;
        this.userBadge = userBadge;
        this.userDate = userDate;
        this.point = point;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserBadge() {
        return userBadge;
    }

    public void setUserBadge(String userBadge) {
        this.userBadge = userBadge;
    }

    public LocalDate getUserDate() {
        return userDate;
    }

    public void setUserDate(LocalDate userDate) {
        this.userDate = userDate;
    }

    // --- GETTERS/SETTERS POUR LES POINTS ---
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}