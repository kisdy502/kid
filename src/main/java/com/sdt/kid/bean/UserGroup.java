package com.sdt.kid.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * 用户群组
 */
@Entity
@Table(name = "user_group")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String groupId;
    @NotBlank
    private String creatorName;
    @NotBlank
    private String groupName;

    private long createTime;

    private String groupDesc;

    private String groupTag;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
}
