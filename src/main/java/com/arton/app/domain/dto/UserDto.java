package com.arton.app.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

public class UserDto {
	private Integer idx;

	@Pattern(regexp = "^[a-z0-9_-]{5,20}$")
	private String userId;

	@Pattern(regexp = "(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}")
	private String userPw;

	@NotBlank
	private String userName;

	@Email
	private String userEmail;

	@Pattern(regexp = "^(19\\d{2}|20\\d{2})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$")
	private String birthDate;

	@Pattern(regexp = "^(M|F|D)?$")
	private String gender;

	@Pattern(regexp = "^[0-9]{8,}$")
	private String userPhone;

	private Date joinTime;
	private Boolean promoChk;
	private Date lastLoginTime;
	private Integer userStatus;

	public UserDto() {}

	public UserDto(Integer idx,
				   String userId,
				   String userPw,
				   String userName,
				   String userEmail,
				   String birthDate,
				   String gender,
				   String userPhone,
				   Date joinTime,
				   Boolean promoChk,
				   Date lastLoginTime,
				   Integer userStatus) {
		this.idx = idx;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.birthDate = birthDate;
		this.gender = gender;
		this.userPhone = userPhone;
		this.joinTime = joinTime;
		this.promoChk = promoChk;
		this.lastLoginTime = lastLoginTime;
		this.userStatus = userStatus;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public Boolean getPromoChk() {
		return promoChk;
	}

	public void setPromoChk(Boolean promoChk) {
		this.promoChk = promoChk;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "UserDto{" + "idx=" + idx + ", userId='" + userId + '\'' + ", userPw='" + userPw + '\'' + ", userName='" + userName + '\'' + ", userEmail='" + userEmail + '\'' + ", birthDate='" + birthDate + '\'' + ", gender='" + gender + '\'' + ", userPhone='" + userPhone + '\'' + ", joinTime=" + joinTime + ", promoChk=" + promoChk + ", lastLoginTime=" + lastLoginTime + ", userStatus=" + userStatus + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDto userDto = (UserDto) o;
		return idx.equals(userDto.idx);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idx);
	}
}
