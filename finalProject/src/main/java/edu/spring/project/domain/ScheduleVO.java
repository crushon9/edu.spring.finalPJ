package edu.spring.project.domain;

public class ScheduleVO {
	private int scdId;
	private int brcId;
	private int mvId;
	private String mvTitle;
	private int mvRuningTime;
	private String scdDate;
	private int scdTime;
	private int scdTheater;
	private int scdSeatTotal;
	private int scdSeatBookedCnt;
	private int scdPrice;

	public ScheduleVO() {
	}

	public ScheduleVO(int scdId, int brcId, int mvId, String mvTitle, int mvRuningTime, String scdDate, int scdTime,
			int scdTheater, int scdSeatTotal, int scdSeatBookedCnt, int scdPrice) {
		this.scdId = scdId;
		this.brcId = brcId;
		this.mvId = mvId;
		this.mvTitle = mvTitle;
		this.mvRuningTime = mvRuningTime;
		this.scdDate = scdDate;
		this.scdTime = scdTime;
		this.scdTheater = scdTheater;
		this.scdSeatTotal = scdSeatTotal;
		this.scdSeatBookedCnt = scdSeatBookedCnt;
		this.scdPrice = scdPrice;
	}

	public int getScdId() {
		return scdId;
	}

	public void setScdId(int scdId) {
		this.scdId = scdId;
	}

	public int getBrcId() {
		return brcId;
	}

	public void setBrcId(int brcId) {
		this.brcId = brcId;
	}

	public int getMvId() {
		return mvId;
	}

	public void setMvId(int mvId) {
		this.mvId = mvId;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public int getMvRuningTime() {
		return mvRuningTime;
	}

	public void setMvRuningTime(int mvRuningTime) {
		this.mvRuningTime = mvRuningTime;
	}

	public String getScdDate() {
		return scdDate;
	}

	public void setScdDate(String scdDate) {
		this.scdDate = scdDate;
	}

	public int getScdTime() {
		return scdTime;
	}

	public void setScdTime(int scdTime) {
		this.scdTime = scdTime;
	}

	public int getScdTheater() {
		return scdTheater;
	}

	public void setScdTheater(int scdTheater) {
		this.scdTheater = scdTheater;
	}

	public int getScdSeatTotal() {
		return scdSeatTotal;
	}

	public void setScdSeatTotal(int scdSeatTotal) {
		this.scdSeatTotal = scdSeatTotal;
	}

	public int getScdSeatBookedCnt() {
		return scdSeatBookedCnt;
	}

	public void setScdSeatBookedCnt(int scdSeatBookedCnt) {
		this.scdSeatBookedCnt = scdSeatBookedCnt;
	}

	public int getScdPrice() {
		return scdPrice;
	}

	public void setScdPrice(int scdPrice) {
		this.scdPrice = scdPrice;
	}

	@Override
	public String toString() {
		return "ScheduleVO [scdId=" + scdId + ", brcId=" + brcId + ", mvId=" + mvId + ", mvTitle=" + mvTitle
				+ ", mvRuningTime=" + mvRuningTime + ", scdDate=" + scdDate + ", scdTime=" + scdTime + ", scdTheater="
				+ scdTheater + ", scdSeatTotal=" + scdSeatTotal + ", scdSeatBookedCnt=" + scdSeatBookedCnt
				+ ", scdPrice=" + scdPrice + "]";
	}

}
