package com.sdt.kid.bean;


import javax.persistence.*;

@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String conversationId;

    private int conversationType;

    private String createUser;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationType() {
        return conversationType;
    }

    public void setConversationType(int conversationType) {
        this.conversationType = conversationType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
