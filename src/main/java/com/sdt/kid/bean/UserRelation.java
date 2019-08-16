package com.sdt.kid.bean;

import javax.persistence.*;

@Entity
@Table(name = "UserRelation") // 唯一约束name
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String myName;

    private String friendName;

    public UserRelation() {
    }

    public UserRelation(String myName, String friendName) {
        this.myName = myName;
        this.friendName = friendName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
