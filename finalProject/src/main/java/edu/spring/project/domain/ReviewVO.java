package edu.spring.project.domain;

import java.util.Date;

public class ReviewVO {

	private int rvId;
	private int mvId;
	private String mmbId;
	private String rvContent;
	private int rvRating;
	private Date rvDateCreated;
	// if 'rvRating' changed...
	private int rvRatingBefore;
	// Join data
	private String mvTitle;
	private String mvImage;

	public ReviewVO() {
	}

	public ReviewVO(int rvId, int mvId, String mmbId, String rvContent, int rvRating, Date rvDateCreated,
			int rvRatingBefore, String mvTitle, String mvImage) {
		this.rvId = rvId;
		this.mvId = mvId;
		this.mmbId = mmbId;
		this.rvContent = rvContent;
		this.rvRating = rvRating;
		this.rvDateCreated = rvDateCreated;
		this.rvRatingBefore = rvRatingBefore;
		this.mvTitle = mvTitle;
		this.mvImage = mvImage;
	}

	public int getRvId() {
		return rvId;
	}

	public void setRvId(int rvId) {
		this.rvId = rvId;
	}

	public int getMvId() {
		return mvId;
	}

	public void setMvId(int mvId) {
		this.mvId = mvId;
	}

	public String getMmbId() {
		return mmbId;
	}

	public void setMmbId(String mmbId) {
		this.mmbId = mmbId;
	}

	public String getRvContent() {
		return rvContent;
	}

	public void setRvContent(String rvContent) {
		this.rvContent = rvContent;
	}

	public int getRvRating() {
		return rvRating;
	}

	public void setRvRating(int rvRating) {
		this.rvRating = rvRating;
	}

	public Date getRvDateCreated() {
		return rvDateCreated;
	}

	public void setRvDateCreated(Date rvDateCreated) {
		this.rvDateCreated = rvDateCreated;
	}

	public int getRvRatingBefore() {
		return rvRatingBefore;
	}

	public void setRvRatingBefore(int rvRatingBefore) {
		this.rvRatingBefore = rvRatingBefore;
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

	@Override
	public String toString() {
		return "ReviewVO [rvId=" + rvId + ", mvId=" + mvId + ", mmbId=" + mmbId + ", rvContent=" + rvContent
				+ ", rvRating=" + rvRating + ", rvDateCreated=" + rvDateCreated + ", rvRatingBefore=" + rvRatingBefore
				+ ", mvTitle=" + mvTitle + "]";
	}

}
