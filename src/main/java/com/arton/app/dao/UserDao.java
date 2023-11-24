package com.arton.app.dao;

import com.arton.app.domain.dto.UserDto;
import com.arton.app.domain.request.ChangePwRequest;
import com.arton.app.domain.request.UserLoginRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private static String namespace = "com.arton.app.dao.UserMapper.";
	@Autowired
	private SqlSession session;

	/** 회원 가입 시 id 중복체크 */
	public int countById(String userId) {
		return session.selectOne(namespace + "countById", userId);
	}

	/** 회원 가입 */
	public int insertUser(UserDto userDto) {
		return session.insert(namespace + "insertUser", userDto);
	}


	/** id, pw로 회원 확인 */
	public UserDto selectUserByUserIdAndUserPw(UserLoginRequest userLoginRequest) {
		return session.selectOne(namespace + "selectUserByUserIdAndUserPw", userLoginRequest);
	}

	/** 로그인 성공 시 마지막 로그인 시간 갱신 */
	public int updateUserLastLoginTime(Integer userIdx) {
		return session.update(namespace + "updateUserLastLoginTime", userIdx);
	}

	/** 회원 비밀번호 변경 */
	public int updateUserPw(ChangePwRequest changePwRequest) {
		return session.update(namespace + "updateUserPw", changePwRequest);
	}

	/** 회원 정보 변경 */
	public int updateUserByUserDto(UserDto userDto) {
		return session.update(namespace + "updateUserByUserDto", userDto);
	}

	/** 회원 탈퇴 */
	public int deleteUserByUserIdAndUserPw(UserLoginRequest userLoginRequest) {
		return session.delete(namespace + "deleteUserByUserIdAndUserPw", userLoginRequest);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */

	public int deleteAllForTest() {
		return session.delete(namespace + "deleteAllForTest");
	}

	public int countForTest() {
		return session.selectOne(namespace + "countForTest");
	}

	public UserDto selectForTest(String userId) {
		return session.selectOne(namespace + "selectForTest", userId);
	}
}
