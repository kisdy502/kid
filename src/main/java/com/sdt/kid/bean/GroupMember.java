package com.sdt.kid.bean;

import javax.persistence.*;

/**
 * 用户群组
 */
@Entity
@Table(name = "group_member")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long creatorId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private UserGroup userGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long createUserId) {
        this.creatorId = createUserId;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public GroupMember() {
    }
}
