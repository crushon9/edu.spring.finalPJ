package edu.spring.project.domain;

public class MovieVO {

	private int mvId;
	private String mvTitle;
	private String mvImage;
	private String mvDateStarted;
	private String mvDateEnded;
	private int mvRuningTime;
	private String mvGenre;
	private String mvInfo;
	private int mvTicketSales;
	private int mvRatingTotal;
	private int mvRatingCnt;
	private float mvRatingAvg;

	public MovieVO() {
	}

	public MovieVO(int mvId, String mvTitle, String mvImage, String mvDateStarted, String mvDateEnded, int mvRuningTime,
			String mvGenre, String mvInfo, int mvTicketSales, int mvRatingTotal, int mvRatingCnt, float mvRatingAvg) {
		this.mvId = mvId;
		this.mvTitle = mvTitle;
		this.mvImage = mvImage;
		this.mvDateStarted = mvDateStarted;
		this.mvDateEnded = mvDateEnded;
		this.mvRuningTime = mvRuningTime;
		this.mvGenre = mvGenre;
		this.mvInfo = mvInfo;
		this.mvTicketSales = mvTicketSales;
		this.mvRatingTotal = mvRatingTotal;
		this.mvRatingCnt = mvRatingCnt;
		this.mvRatingAvg = mvRatingAvg;
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

	public String getMvImage() {
		return mvImage;
	}

	public void setMvImage(String mvImage) {
		this.mvImage = mvImage;
	}

	public String getMvDateStarted() {
		return mvDateStarted;
	}

	public void setMvDateStarted(String mvDateStarted) {
		this.mvDateStarted = mvDateStarted;
	}

	public String getMvDateEnded() {
		return mvDateEnded;
	}

	public void setMvDateEnded(String mvDateEnded) {
		this.mvDateEnded = mvDateEnded;
	}

	public int getMvRuningTime() {
		return mvRuningTime;
	}

	public void setMvRuningTime(int mvRuningTime) {
		this.mvRuningTime = mvRuningTime;
	}

	public String getMvGenre() {
		return mvGenre;
	}

	public void setMvGenre(String mvGenre) {
		this.mvGenre = mvGenre;
	}

	public String getMvInfo() {
		return mvInfo;
	}

	public void setMvInfo(String mvInfo) {
		this.mvInfo = mvInfo;
	}

	public int getMvTicketSales() {
		return mvTicketSales;
	}

	public void setMvTicketSales(int mvTicketSales) {
		this.mvTicketSales = mvTicketSales;
	}

	public int getMvRatingTotal() {
		return mvRatingTotal;
	}

	public void setMvRatingTotal(int mvRatingTotal) {
		this.mvRatingTotal = mvRatingTotal;
	}

	public int getMvRatingCnt() {
		return mvRatingCnt;
	}

	public void setMvRatingCnt(int mvRatingCnt) {
		this.mvRatingCnt = mvRatingCnt;
	}

	public float getMvRatingAvg() {
		return mvRatingAvg;
	}

	public void setMvRatingAvg(float mvRatingAvg) {
		this.mvRatingAvg = mvRatingAvg;
	}

	@Override
	public String toString() {
		return "MovieVO [mvId=" + mvId + ", mvTitle=" + mvTitle + ", mvImage=" + mvImage + ", mvDateStarted="
				+ mvDateStarted + ", mvDateEnded=" + mvDateEnded + ", mvRuningTime=" + mvRuningTime + ", mvGenre="
				+ mvGenre + ", mvInfo=" + mvInfo + ", mvTicketSales=" + mvTicketSales + ", mvRatingTotal="
				+ mvRatingTotal + ", mvRatingCnt=" + mvRatingCnt + ", mvRatingAvg=" + mvRatingAvg + "]";
	}

}
