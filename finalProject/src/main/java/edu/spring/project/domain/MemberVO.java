package edu.spring.project.domain;

public class MemberVO {

	private int mmbId;
	private String mmbPassword;
	private String mmbEmail;
	private String mmbPhone;
	private String mmbBirthday;
	private int mmbAdminCheck;
	private int brcId;

	public MemberVO() {
	}

	public MemberVO(int mmbId, String mmbPassword, String mmbEmail, String mmbPhone, String mmbBirthday,
			int mmbAdminCheck, int brcId) {

		this.mmbId = mmbId;
		this.mmbPassword = mmbPassword;
		this.mmbEmail = mmbEmail;
		this.mmbPhone = mmbPhone;
		this.mmbBirthday = mmbBirthday;
		this.mmbAdminCheck = mmbAdminCheck;
		this.brcId = brcId;
	}

	public int getMmbId() {
		return mmbId;
	}

	public void setMmbId(int mmbId) {
		this.mmbId = mmbId;
	}

	public String getMmbPassword() {
		return mmbPassword;
	}

	public void setMmbPassword(String mmbPassword) {
		this.mmbPassword = mmbPassword;
	}

	public String getMmbEmail() {
		return mmbEmail;
	}

	public void setMmbEmail(String mmbEmail) {
		this.mmbEmail = mmbEmail;
	}

	public String getMmbPhone() {
		return mmbPhone;
	}

	public void setMmbPhone(String mmbPhone) {
		this.mmbPhone = mmbPhone;
	}

	public String getMmbBirthday() {
		return mmbBirthday;
	}

	public void setMmbBirthday(String mmbBirthday) {
		this.mmbBirthday = mmbBirthday;
	}

	public int getMmbAdminCheck() {
		return mmbAdminCheck;
	}

	public void setMmbAdminCheck(int mmbAdminCheck) {
		this.mmbAdminCheck = mmbAdminCheck;
	}

	public int getBrcId() {
		return brcId;
	}

	public void setBrcId(int brcId) {
		this.brcId = brcId;
	}

	@Override
	public String toString() {
		return "MemberVO [mmbId=" + mmbId + ", mmbPassword=" + mmbPassword + ", mmbEmail=" + mmbEmail + ", mmbPhone="
				+ mmbPhone + ", mmbBirthday=" + mmbBirthday + ", mmbAdminCheck=" + mmbAdminCheck + ", brcId=" + brcId
				+ "]";
	}

}
