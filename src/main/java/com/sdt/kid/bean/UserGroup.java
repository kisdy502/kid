package com.sdt.kid.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户群组
 */
@Entity
@Table(name = "user_group")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long creatorId;

    private String groupName;

    private long createTime;

    private String groupDesc;

    private String groupTag;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GroupMember> groupMemberList = new ArrayList<GroupMember>();

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupTag() {
        return groupTag;
    }

    public void setGroupTag(String groupTag) {
        this.groupTag = groupTag;
    }

    public List<GroupMember> getGroupMemberList() {
        return groupMemberList;
    }

    public void addGroupMember(GroupMember groupMember) {
        groupMemberList.add(groupMember);
        groupMember.setUserGroup(this);
    }

    public void removeGroupMember(GroupMember groupMember) {
        groupMemberList.remove(groupMember);
        groupMember.setUserGroup(null);
    }

    public UserGroup() {
    }
}
