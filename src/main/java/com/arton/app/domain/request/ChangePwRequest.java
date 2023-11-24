package com.arton.app.domain.request;

public class ChangePwRequest {
	private String userId;
	private String userPw;
	private String newPw;
	private String newPwChk;

	public ChangePwRequest() {}

	public ChangePwRequest(String userId, String userPw, String newPw, String newPwChk) {
		this.userId = userId;
		this.userPw = userPw;
		this.newPw = newPw;
		this.newPwChk = newPwChk;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getNewPwChk() {
		return newPwChk;
	}

	public void setNewPwChk(String newPwChk) {
		this.newPwChk = newPwChk;
	}

	@Override
	public String toString() {
		return "ChangePwRequest{" + "userId='" + userId + '\'' + ", userPw='" + userPw + '\'' + ", newPw='" + newPw + '\'' + ", newPwChk='" + newPwChk + '\'' + '}';
	}
}
