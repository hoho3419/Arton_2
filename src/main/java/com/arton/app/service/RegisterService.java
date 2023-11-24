package com.arton.app.service;

import com.arton.app.dao.UserDao;
import com.arton.app.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
	@Autowired
	UserDao userDao;

	/** 회원가입 이전에 id 중복확인 */
	public int idDuplicateCheck(String userId) {
		return userDao.countById(userId);
	}

	/** 회원가입 */
	public int join(UserDto userDto) {
		return userDao.insertUser(userDto);
	}
}
