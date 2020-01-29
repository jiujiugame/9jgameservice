package com.jiujiu.j9game.model;

public class JiuJiuUser {

    private String uid;

    private String gender;

    private String avatarUrl;

    private String userName;

    public String getUid() {
        return uid;
    }

    public void setUuid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	@Override
	public String toString() {
		return "JiuJiuUser [uid=" + uid + ", gender=" + gender + ", avatarUrl=" + avatarUrl + ", userName=" + userName
				+ "]";
	}
    
}
