package edu.spring.project.domain;

import java.util.Arrays;

public class BranchVO {
	// DB data
	private int brcId;
	private int brcArea;
	private String brcName;
	private int brcTheaterNumbers;
	private String brcTheaterSeats;
	private String brcInfo;
	private int brcImmutableCheck;
	// view에서 보내온 데이터를 가공하기 위한 임시 멤버 변수
	private int[] arrBrcTheaterSeats;

	public BranchVO() {
	}

	public BranchVO(int brcId, int brcArea, String brcName, int brcTheaterNumbers, String brcTheaterSeats,
			String brcInfo, int brcImmutableCheck) {
		this.brcId = brcId;
		this.brcArea = brcArea;
		this.brcName = brcName;
		this.brcTheaterNumbers = brcTheaterNumbers;
		this.brcTheaterSeats = brcTheaterSeats;
		this.brcInfo = brcInfo;
		this.brcImmutableCheck = brcImmutableCheck;
	}

	public int getBrcId() {
		return brcId;
	}

	public void setBrcId(int brcId) {
		this.brcId = brcId;
	}

	public int getBrcArea() {
		return brcArea;
	}

	public void setBrcArea(int brcArea) {
		this.brcArea = brcArea;
	}

	public String getBrcName() {
		return brcName;
	}

	public void setBrcName(String brcName) {
		this.brcName = brcName;
	}

	public int getBrcTheaterNumbers() {
		return brcTheaterNumbers;
	}

	public void setBrcTheaterNumbers(int brcTheaterNumbers) {
		this.brcTheaterNumbers = brcTheaterNumbers;
	}

	public String getBrcTheaterSeats() {
		// int[] arrBrcTheaterSeats를 toString하여 [ ] 떼고 DB에 저장
		return Arrays.toString(arrBrcTheaterSeats).replace("[", "").replace("]", "");
	}

	public void setBrcTheaterSeats(String brcTheaterSeats) {
		// String brcTheaterSeats을 set한 뒤
		this.brcTheaterSeats = brcTheaterSeats;
		// arrBrcTheaterSeats도 set
		String splitArr[] = brcTheaterSeats.split(", ");
		int[] intArr = new int[splitArr.length];
		// String을 int로 변환하여 배열에 저장
		for (int i = 0; i < splitArr.length; i++) {
			intArr[i] = Integer.parseInt(splitArr[i]);
		}
		this.arrBrcTheaterSeats = intArr;
	}

	public int[] getArrBrcTheaterSeats() {
		return arrBrcTheaterSeats;
	}

	public void setArrBrcTheaterSeats(int[] arrBrcTheaterSeats) {
		this.arrBrcTheaterSeats = arrBrcTheaterSeats;
	}

	public String getBrcInfo() {
		return brcInfo;
	}

	public void setBrcInfo(String brcInfo) {
		this.brcInfo = brcInfo;
	}

	public int getBrcImmutableCheck() {
		return brcImmutableCheck;
	}

	public void setBrcImmutableCheck(int brcImmutableCheck) {
		this.brcImmutableCheck = brcImmutableCheck;
	}

	@Override
	public String toString() {
		return "BranchVO [brcId=" + brcId + ", brcArea=" + brcArea + ", brcName=" + brcName + ", brcTheaterNumbers="
				+ brcTheaterNumbers + ", brcTheaterSeats=" + brcTheaterSeats + ", brcInfo=" + brcInfo
				+ ", brcImmutableCheck=" + brcImmutableCheck + ", arrBrcTheaterSeats="
				+ Arrays.toString(arrBrcTheaterSeats) + "]";
	}

}
