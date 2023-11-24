package com.arton.app.domain.request;

public class BookingRequest {
	private Integer userIdx;
	private Integer seatInvtId;
	private Integer ticketCnt;
	private String payMethod;
	private Integer perfCode;

	public BookingRequest() {}

	public BookingRequest(Integer userIdx, Integer seatInvtId, Integer ticketCnt, String payMethod, Integer perfCode) {
		this.userIdx = userIdx;
		this.seatInvtId = seatInvtId;
		this.ticketCnt = ticketCnt;
		this.payMethod = payMethod;
		this.perfCode = perfCode;
	}

	public Integer getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(Integer userIdx) {
		this.userIdx = userIdx;
	}

	public Integer getSeatInvtId() {
		return seatInvtId;
	}

	public void setSeatInvtId(Integer seatInvtId) {
		this.seatInvtId = seatInvtId;
	}

	public Integer getTicketCnt() {
		return ticketCnt;
	}

	public void setTicketCnt(Integer ticketCnt) {
		this.ticketCnt = ticketCnt;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getPerfCode() {
		return perfCode;
	}

	public void setPerfCode(Integer perfCode) {
		this.perfCode = perfCode;
	}

	@Override
	public String toString() {
		return "BookingRequest{" + "userIdx=" + userIdx + ", seatInvtId=" + seatInvtId + ", ticketCnt=" + ticketCnt + ", payMethod='" + payMethod + '\'' + ", perfCode=" + perfCode + '}';
	}
}
