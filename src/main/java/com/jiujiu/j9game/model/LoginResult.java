package com.jiujiu.j9game.model;

public class LoginResult {

    private Integer s2c_code = 200;
    private String s2c_msg = "Successfully!";
    private String newUid = "";
    private String board = "";
    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getS2c_code() {
        return s2c_code;
    }

    public void setS2c_code(Integer s2c_code) {
        this.s2c_code = s2c_code;
    }

    public String getS2c_msg() {
        return s2c_msg;
    }

    public void setS2c_msg(String s2c_msg) {
        this.s2c_msg = s2c_msg;
    }

    public String getNewUid() {
        return newUid;
    }

    public void setNewUid(String newUid) {
        this.newUid = newUid;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

}
