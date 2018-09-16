package com.javadeep.boot.common.dto;

public class BizDTO {

    public BizDTO(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    private int id;

    private int userId;

    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
