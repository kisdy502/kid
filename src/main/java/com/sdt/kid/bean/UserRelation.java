package com.sdt.kid.bean;

import javax.persistence.*;

@Entity
@Table(name = "UserRelation") // 唯一约束name
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long myId;

    private Long friendId;

    public UserRelation() {
    }

    public UserRelation(Long myId, Long friendId) {
        this.myId = myId;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMyId() {
        return myId;
    }

    public Long getFriendId() {
        return friendId;
    }
}
