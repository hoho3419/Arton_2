package com.arton.app.dao;

import com.arton.app.domain.dto.UserDto;
import com.arton.app.domain.request.ChangePwRequest;
import com.arton.app.domain.request.UserLoginRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	@Test
	public void countById() {
		userDao.deleteAllForTest();

		assertEquals(0, userDao.countById("any"));
	}

	@Test
	public void insertUser() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();

		assertTrue(userDao.insertUser(userDto) == 1);

		assertEquals(1, userDao.countForTest());
	}

	@Test
	public void selectUserByUserIdAndUserPw() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();
		userDao.insertUser(userDto);

		UserLoginRequest userLoginRequest = new UserLoginRequest("test", "asdf", false);
		UserDto userDtoDB = userDao.selectUserByUserIdAndUserPw(userLoginRequest);

		assertTrue(userDto.getUserId().equals(userDtoDB.getUserId()));
	}

	@Test
	public void updateUserLastLoginTime() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();
		userDao.insertUser(userDto);

		UserDto dtoBefore = userDao.selectForTest(userDto.getUserId());

		try {
			Thread.sleep(1000); // updateLoginTime 하기 전 1초 지연 시키기
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		assertTrue(userDao.updateUserLastLoginTime(dtoBefore.getIdx()) == 1);
		UserDto dtoAfter = userDao.selectForTest(userDto.getUserId());

		assertNotEquals(dtoBefore.getLastLoginTime(), dtoAfter.getLastLoginTime());

	}

	@Test
	public void updateUserPw() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();
		userDao.insertUser(userDto);

		String newPw = "newPw";
		ChangePwRequest request = new ChangePwRequest(userDto.getUserId(), userDto.getUserPw(), newPw, newPw);

		UserDto before = userDao.selectForTest(userDto.getUserId());
		assertTrue(userDao.updateUserPw(request) == 1);
		UserDto after = userDao.selectForTest(userDto.getUserId());

		assertNotEquals(before.getUserPw(), after.getUserPw());
	}

	@Test
	public void updateUserByUserDto() {
		userDao.deleteAllForTest();

		UserDto init = createUserDto();
		userDao.insertUser(init);

		UserDto userDto = userDao.selectForTest(init.getUserId());

		String newUserName = "이름테스트";
		userDto.setUserName(newUserName);
		assertTrue(userDao.updateUserByUserDto(userDto) == 1);

		assertEquals(newUserName, userDao.selectForTest(userDto.getUserId()).getUserName());
	}

	@Test
	public void deleteUserByUserIdAndUserPw() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();
		userDao.insertUser(userDto);

		UserLoginRequest request = new UserLoginRequest();
		request.setUserId(userDto.getUserId());
		request.setUserPw(userDto.getUserPw());

		assertEquals(1, userDao.countForTest());

		assertTrue(userDao.deleteUserByUserIdAndUserPw(request) == 1);

		assertEquals(0, userDao.countForTest());

	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	/* for Test */

	@Test
	public void deleteAllAndCount() {
		userDao.deleteAllForTest();
		assertEquals(0, userDao.countForTest());
	}

	@Test
	public void selectForTest() {
		userDao.deleteAllForTest();

		UserDto userDto = createUserDto();
		userDao.insertUser(userDto);

		assertTrue(userDao.selectForTest(userDto.getUserId()).getUserId().equals(userDto.getUserId()));
	}


	/* Methods for Test*/

	/**
	 * 테스트용 UserDto 생성
	 *
	 * @return UserDto
	 */
	private UserDto createUserDto() {
		return new UserDto(null,
				"test",
				"asdf",
				"name",
				"email@email.com",
				"19990101",
				"D",
				"01012345678",
				null,
				false,
				null,
				null);
	}
}