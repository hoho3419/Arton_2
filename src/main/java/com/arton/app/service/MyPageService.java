package com.arton.app.service;

import com.arton.app.dao.BookingDao;
import com.arton.app.dao.PerfDao;
import com.arton.app.dao.UserDao;
import com.arton.app.domain.dto.BookingDto;
import com.arton.app.domain.dto.PerfDto;
import com.arton.app.domain.dto.UserDto;
import com.arton.app.domain.request.ChangePwRequest;
import com.arton.app.domain.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

	@Autowired
	UserDao userDao;
	@Autowired
	PerfDao perfDao;
	@Autowired
	BookingDao bookingDao;


	/** id, pw 재확인 */
	public UserDto userCheck(UserLoginRequest userLoginRequest) {
		return userDao.selectUserByUserIdAndUserPw(userLoginRequest);
	}

	/** 회원 비밀번호 변경 */
	public int changePw(ChangePwRequest changePwRequest) {
		return userDao.updateUserPw(changePwRequest);
	}

	/** 회원 정보 변경 */
	public int changeUserInfo(UserDto userDto) {
		return userDao.updateUserByUserDto(userDto);
	}

	/** 회원 탈퇴 */
	public int withdrawal(UserLoginRequest userLoginRequest) {
		return userDao.deleteUserByUserIdAndUserPw(userLoginRequest);
	}

	/** 마이페이지 예매내역 목록 읽기 */
	public List<BookingDto> readMyBookings(Integer userIdx) {
		return bookingDao.selectBookingsByUserIdx(userIdx);
	}

	/** 마이페이지 찜 목록 */
	public List<PerfDto> readMyWishlists(Integer userIdx) {
		return perfDao.selectWishlistsByUserIdx(userIdx);
	}

}
