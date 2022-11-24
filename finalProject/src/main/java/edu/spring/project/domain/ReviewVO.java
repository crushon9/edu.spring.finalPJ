package edu.spring.project.domain;

import java.util.Date;

public class ReviewVO {

	private int rvId;
	private int mvId;
	private String mmbId;
	private String rvContent;
	private int rvRating;
	private Date rvDateCreated;
	// rvRating 수정시 movie에 반영하기 위한 임시 멤버변수
	private int rvRatingBefore;

	public ReviewVO() {

	}

	public ReviewVO(int rvId, int mvId, String mmbId, String rvContent, int rvRating, Date rvDateCreated,
			int rvRatingBefore) {
		this.rvId = rvId;
		this.mvId = mvId;
		this.mmbId = mmbId;
		this.rvContent = rvContent;
		this.rvRating = rvRating;
		this.rvDateCreated = rvDateCreated;
		this.rvRatingBefore = rvRatingBefore;
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

	@Override
	public String toString() {
		return "ReviewVO [rvId=" + rvId + ", mvId=" + mvId + ", mmbId=" + mmbId + ", rvContent=" + rvContent
				+ ", rvRating=" + rvRating + ", rvDateCreated=" + rvDateCreated + ", rvRatingBefore=" + rvRatingBefore
				+ "]";
	}

}
