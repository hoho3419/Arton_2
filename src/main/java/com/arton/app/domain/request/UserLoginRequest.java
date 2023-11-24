package com.arton.app.domain.request;

public class UserLoginRequest {
	private String userId;
	private String userPw;
	private boolean rememberId;

	public UserLoginRequest() {}

	public UserLoginRequest(String userId, String userPw, boolean rememberId) {
		this.userId = userId;
		this.userPw = userPw;
		this.rememberId = rememberId;
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

	public boolean isRememberId() {
		return rememberId;
	}

	public void setRememberId(boolean rememberId) {
		this.rememberId = rememberId;
	}

	@Override
	public String toString() {
		return "UserLoginRequest{" + "userId='" + userId + '\'' + ", userPw='" + userPw + '\'' + ", rememberId=" + rememberId + '}';
	}
}
