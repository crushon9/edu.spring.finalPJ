package edu.spring.project.domain;

public class TicketVO {
	// DB data
	private int tkId;
	private String mmbId;
	private int scdId;
	private int mvId;
	private String tkPeopleList;
	private String tkSeatList;
	private int tkPriceTotal;
	// Join data
	private String mvTitle;
	private String mvImage;
	private String brcName;
	private String scdDate;
	private int scdTime;
	private int scdTheater;
	private int scdSeatTotal;
	private int scdSeatBookedCnt;
	private int scdPrice;

	public TicketVO() {
	}

	public TicketVO(int tkId, String mmbId, int scdId, int mvId, String tkPeopleList, String tkSeatList,
			int tkPriceTotal, String mvTitle, String mvImage, String brcName, String scdDate, int scdTime,
			int scdTheater, int scdSeatTotal, int scdSeatBookedCnt, int scdPrice) {
		this.tkId = tkId;
		this.mmbId = mmbId;
		this.scdId = scdId;
		this.mvId = mvId;
		this.tkPeopleList = tkPeopleList;
		this.tkSeatList = tkSeatList;
		this.tkPriceTotal = tkPriceTotal;
		this.mvTitle = mvTitle;
		this.mvImage = mvImage;
		this.brcName = brcName;
		this.scdDate = scdDate;
		this.scdTime = scdTime;
		this.scdTheater = scdTheater;
		this.scdSeatTotal = scdSeatTotal;
		this.scdSeatBookedCnt = scdSeatBookedCnt;
		this.scdPrice = scdPrice;
	}

	public int getTkId() {
		return tkId;
	}

	public void setTkId(int tkId) {
		this.tkId = tkId;
	}

	public String getMmbId() {
		return mmbId;
	}

	public void setMmbId(String mmbId) {
		this.mmbId = mmbId;
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

	public String getTkPeopleList() {
		return tkPeopleList;
	}

	public void setTkPeopleList(String tkPeopleList) {
		this.tkPeopleList = tkPeopleList;
	}

	public String getTkSeatList() {
		return tkSeatList;
	}

	public void setTkSeatList(String tkSeatList) {
		this.tkSeatList = tkSeatList;
	}

	public int getTkPriceTotal() {
		return tkPriceTotal;
	}

	public void setTkPriceTotal(int tkPriceTotal) {
		this.tkPriceTotal = tkPriceTotal;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getMvImage() {
		return mvImage;
	}

	public void setMvImage(String mvImage) {
		this.mvImage = mvImage;
	}

	public String getBrcName() {
		return brcName;
	}

	public void setBrcName(String brcName) {
		this.brcName = brcName;
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
		return "TicketVO [tkId=" + tkId + ", mmbId=" + mmbId + ", scdId=" + scdId + ", mvId=" + mvId + ", tkPeopleList="
				+ tkPeopleList + ", tkSeatList=" + tkSeatList + ", tkPriceTotal=" + tkPriceTotal + ", mvTitle="
				+ mvTitle + ", mvImage=" + mvImage + ", brcName=" + brcName + ", scdDate=" + scdDate + ", scdTime="
				+ scdTime + ", scdTheater=" + scdTheater + ", scdSeatTotal=" + scdSeatTotal + ", scdSeatBookedCnt="
				+ scdSeatBookedCnt + ", scdPrice=" + scdPrice + "]";
	}

}
