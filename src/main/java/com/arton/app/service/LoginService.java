package com.arton.app.service;

import com.arton.app.dao.PerfDao;
import com.arton.app.dao.UserDao;
import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.UserDto;
import com.arton.app.domain.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

	@Autowired
	UserDao userDao;

	@Autowired
	PerfDao perfDao;

	/** 랜덤 포스터 */
	public PerfDto getRandomPerf() {
		Map map = new HashMap();
		map.put("order", "ORDER BY RAND()");
		map.put("limit", "LIMIT 0, 1");
		List<PerfDto> list = perfDao.selectPerfsByCustomizedCondition(map);
		return list.get(0);
	}

	/** 로그인 */
	public UserDto login(UserLoginRequest userLoginRequest) {
		return userDao.selectUserByUserIdAndUserPw(userLoginRequest);
	}

	/** 최종 로그인 시간 갱신 */
	public int updateLoginTime(Integer userIdx) {
		return userDao.updateUserLastLoginTime(userIdx);
	}

	/** 회원가입 */
	public int signUp(UserDto userDto) {
		return userDao.insertUser(userDto);
	}
}
