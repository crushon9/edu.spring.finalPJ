package edu.spring.project.domain;

import java.util.Date;

public class MovieVO {

	private int mvId;
	private String mvTitle;
	private Date mvDateStarted;
	private Date mvDateEnded;
	private String mvGenre;
	private float mvRating;
	private int mvTicketSales;
	private String mvImage;

	public MovieVO() {
	}

	public MovieVO(int mvId, String mvTitle, Date mvDateStarted, Date mvDateEnded, String mvGenre, float mvRating,
			int mvTicketSales, String mvImage) {
		this.mvId = mvId;
		this.mvTitle = mvTitle;
		this.mvDateStarted = mvDateStarted;
		this.mvDateEnded = mvDateEnded;
		this.mvGenre = mvGenre;
		this.mvRating = mvRating;
		this.mvTicketSales = mvTicketSales;
		this.mvImage = mvImage;
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

	public Date getMvDateStarted() {
		return mvDateStarted;
	}

	public void setMvDateStarted(Date mvDateStarted) {
		this.mvDateStarted = mvDateStarted;
	}

	public Date getMvDateEnded() {
		return mvDateEnded;
	}

	public void setMvDateEnded(Date mvDateEnded) {
		this.mvDateEnded = mvDateEnded;
	}

	public String getMvGenre() {
		return mvGenre;
	}

	public void setMvGenre(String mvGenre) {
		this.mvGenre = mvGenre;
	}

	public float getMvRating() {
		return mvRating;
	}

	public void setMvRating(float mvRating) {
		this.mvRating = mvRating;
	}

	public int getMvTicketSales() {
		return mvTicketSales;
	}

	public void setMvTicketSales(int mvTicketSales) {
		this.mvTicketSales = mvTicketSales;
	}

	public String getMvImage() {
		return mvImage;
	}

	public void setMvImage(String mvImage) {
		this.mvImage = mvImage;
	}

	@Override
	public String toString() {
		return "MovieVO [mvId=" + mvId + ", mvTitle=" + mvTitle + ", mvDateStarted=" + mvDateStarted + ", mvDateEnded="
				+ mvDateEnded + ", mvGenre=" + mvGenre + ", mvRating=" + mvRating + ", mvTicketSales=" + mvTicketSales
				+ ", mvImage=" + mvImage + "]";
	}

}
