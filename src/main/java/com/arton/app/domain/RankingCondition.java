package com.arton.app.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RankingCondition {

	private Integer cateCode;
	private String bookingDateFrom;
	private String bookingDateTo;

	public RankingCondition() {}

	public RankingCondition(Integer cateCode, String bookingDateFrom, String bookingDateTo) {
		this.cateCode = cateCode;
		this.bookingDateFrom = bookingDateFrom;
		this.bookingDateTo = bookingDateTo;
	}

	/**
	 * <pre>
	 * 파라미터로 cateCode, rankDate, period 받아서
	 * 날짜 계산하여 랭킹 산출 기간(bookingDateTo, bookingDateFrom)을 구한다.
	 * </pre>
	 *
	 * @return RankingCondition
	 */
	public static RankingCondition toRankingCondition(Integer cateCode, String rankDate, String period) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		Integer cateCodeChk = cateCode == null || "null".equals(cateCode) ? 0 : cateCode;
		String rankDateChk = rankDate == null || "null".equals(rankDate) ? sdf.format(cal.getTime()) : rankDate.replaceAll(
				"-",
				"");
		String periodChk = period == null || "null".equals(period) ? "d" : period;


		try {
			Date dateTmp = sdf.parse(rankDateChk);
			cal.setTime(dateTmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String bookingDateTo = rankDateChk + "23:59:59";
		String dateTmp = "";
		switch (periodChk) {
			case "w":
				cal.add(Calendar.DATE, -7);
				dateTmp = sdf.format(cal.getTime());
				break;
			case "m":
				cal.add(Calendar.DATE, -30);
				dateTmp = sdf.format(cal.getTime());
				break;
			case "y":
				cal.add(Calendar.YEAR, -1);
				dateTmp = sdf.format(cal.getTime());
				break;
			default:
				// cal.add(Calendar.DATE, -1);
				dateTmp = sdf.format(cal.getTime());
				break;
		}
		String bookingDateFrom = dateTmp + "00:00:00";

		return new RankingCondition(cateCodeChk, bookingDateFrom, bookingDateTo);
	}

	public Integer getCateCode() {
		return cateCode;
	}

	public void setCateCode(Integer cateCode) {
		this.cateCode = cateCode;
	}

	public String getBookingDateFrom() {
		return bookingDateFrom;
	}

	public void setBookingDateFrom(String bookingDateFrom) {
		this.bookingDateFrom = bookingDateFrom;
	}

	public String getBookingDateTo() {
		return bookingDateTo;
	}

	public void setBookingDateTo(String bookingDateTo) {
		this.bookingDateTo = bookingDateTo;
	}

	@Override
	public String toString() {
		return "RankingCondition{" + "cateCode=" + cateCode + ", bookingDateFrom='" + bookingDateFrom + '\'' + ", bookingDateTo='" + bookingDateTo + '\'' + '}';
	}
}
