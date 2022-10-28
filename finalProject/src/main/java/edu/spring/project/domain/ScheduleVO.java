package edu.spring.project.domain;

import java.util.Date;

public class ScheduleVO {
	private int scdId;
	private int mvId;
	private int brcId;
	private Date scdDate;
	private int scdTime;
	private int scdTheater;
	private int scdSeatTotal; // scdTheater에 종속된 값
	private int scdSeatBookedCnt; // 생성시 0
	private int scdPrice; // scdDate와 scdTime에 영향받는 값??????????

	public ScheduleVO() {
	}

	public ScheduleVO(int scdId, int mvId, int brcId, Date scdDate, int scdTime, int scdTheater, int scdSeatTotal,
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

	public Date getScdDate() {
		return scdDate;
	}

	public void setScdDate(Date scdDate) {
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
