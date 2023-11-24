package com.arton.app.domain.dto;

public class PerfRoundDto {
	private Integer id;
	private Integer perfId;
	private Integer showDateIdx;
	private String showDate;
	private Integer showTimeIdx;
	private String showTime;


	public PerfRoundDto() {}

	public PerfRoundDto(Integer id,
						Integer perfId,
						Integer showDateIdx,
						String showDate,
						Integer showTimeIdx,
						String showTime) {
		this.id = id;
		this.perfId = perfId;
		this.showDateIdx = showDateIdx;
		this.showDate = showDate;
		this.showTimeIdx = showTimeIdx;
		this.showTime = showTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerfId() {
		return perfId;
	}

	public void setPerfId(Integer perfId) {
		this.perfId = perfId;
	}

	public Integer getShowDateIdx() {
		return showDateIdx;
	}

	public void setShowDateIdx(Integer showDateIdx) {
		this.showDateIdx = showDateIdx;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public Integer getShowTimeIdx() {
		return showTimeIdx;
	}

	public void setShowTimeIdx(Integer showTimeIdx) {
		this.showTimeIdx = showTimeIdx;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	@Override
	public String toString() {
		return "PerfRoundDto{" + "id=" + id + ", perfId=" + perfId + ", showDateIdx=" + showDateIdx + ", showDate='" + showDate + '\'' + ", showTimeIdx=" + showTimeIdx + ", showTime='" + showTime + '\'' + '}';
	}
}
