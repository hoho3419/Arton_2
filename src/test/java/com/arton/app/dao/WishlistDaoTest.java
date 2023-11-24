package com.arton.app.dao;

import com.arton.app.domain.dto.WishlistDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class WishlistDaoTest {

	@Autowired
	WishlistDao wishlistDao;


	@Test
	public void selectWishlistByUserIdxAndPerfId() {
		wishlistDao.deleteAllForTest();

		Map map = new HashMap();
		map.put("userIdx", 1);
		map.put("perfId", 1001);
		wishlistDao.insertWishlist(map);

		WishlistDto wishlistDtoDB = wishlistDao.selectWishlistByUserIdxAndPerfId(map);
		assertTrue(wishlistDtoDB.getUserIdx() == 1 && wishlistDtoDB.getPerfId() == 1001);
	}

	@Test
	public void insertWishlist() {
		wishlistDao.deleteAllForTest();

		Map map = new HashMap();
		map.put("userIdx", 1);
		map.put("perfId", 1001);
		wishlistDao.insertWishlist(map);

		assertEquals(1, wishlistDao.countForTest());
	}

	@Test
	public void deleteWishlist() {
		wishlistDao.deleteAllForTest();

		Map map = new HashMap();
		map.put("userIdx", 1);
		map.put("perfId", 1001);
		wishlistDao.insertWishlist(map);

		assertEquals(1, wishlistDao.countForTest());

		assertTrue(wishlistDao.deleteWishlist(map) == 1);

		assertEquals(0, wishlistDao.countForTest());
	}

	@Test
	public void countWishlistByPerfId() {
		wishlistDao.deleteAllForTest();

		assertEquals(0, wishlistDao.countWishlistByPerfId(1001));
	}

	/////////////////////////////////////////////////////////////////////////////////
	/* for Test */

	@Test
	public void deleteAllAndCount() {
		wishlistDao.deleteAllForTest();

		assertEquals(0, wishlistDao.countForTest());
	}

}