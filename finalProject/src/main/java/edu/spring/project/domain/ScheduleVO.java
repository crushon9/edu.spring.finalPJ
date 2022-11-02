package edu.spring.project.domain;

import java.util.Date;

public class ScheduleVO {
	private int scdId;
	private int mvId;
	private int brcId;
	private String scdDate;
	private int scdTime;
	private int scdTheater;
	private int scdSeatTotal;
	private int scdSeatBookedCnt;
	private int scdPrice;

	public ScheduleVO() {
	}

	public ScheduleVO(int scdId, int mvId, int brcId, String scdDate, int scdTime, int scdTheater, int scdSeatTotal,
			int scdSeatBookedCnt, int scdPrice) {
		this.scdId = scdId;
		this.mvId = mvId;
		this.brcId = brcId;
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

	public int getMvId() {
		return mvId;
	}

	public void setMvId(int mvId) {
		this.mvId = mvId;
	}

	public int getBrcId() {
		return brcId;
	}

	public void setBrcId(int brcId) {
		this.brcId = brcId;
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
		return "ScheduleVO [scdId=" + scdId + ", mvId=" + mvId + ", brcId=" + brcId + ", scdDate=" + scdDate
				+ ", scdTime=" + scdTime + ", scdTheater=" + scdTheater + ", scdSeatTotal=" + scdSeatTotal
				+ ", scdSeatBookedCnt=" + scdSeatBookedCnt + ", scdPrice=" + scdPrice + "]";
	}

}
