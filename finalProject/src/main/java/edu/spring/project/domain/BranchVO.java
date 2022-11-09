package edu.spring.project.domain;

import java.util.Arrays;

public class BranchVO {

	private int brcId;
	private int brcArea;
	private String brcName;
	private int brcTheaterNumbers;
	private String brcTheaterSeats;
	private int[] arrBrcTheaterSeats;

	public BranchVO() {
	}

	public BranchVO(int brcId, int brcArea, String brcName, int brcTheaterNumbers, String brcTheaterSeats) {
		this.brcId = brcId;
		this.brcArea = brcArea;
		this.brcName = brcName;
		this.brcTheaterNumbers = brcTheaterNumbers;
		this.brcTheaterSeats = brcTheaterSeats;
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

	// int[] arrBrcTheaterSeats 배열을 문자열로 변환
	public String getBrcTheaterSeats() {
		return Arrays.toString(arrBrcTheaterSeats).replace("[", "").replace("]", "");
	}

	public void setBrcTheaterSeats(String brcTheaterSeats) {
		this.brcTheaterSeats = brcTheaterSeats;
		// String을 Array에 담기
		String tempArr[] = brcTheaterSeats.split(", ");
		int[] intArr = new int[tempArr.length];
		for (int i = 0; i < tempArr.length; i++) {
			intArr[i] = Integer.parseInt(tempArr[i]);
		}
		this.arrBrcTheaterSeats = intArr;
	}

	public int[] getArrBrcTheaterSeats() {
		return arrBrcTheaterSeats;
	}

	public void setArrBrcTheaterSeats(int[] arrBrcTheaterSeats) {
		this.arrBrcTheaterSeats = arrBrcTheaterSeats;
	}

	@Override
	public String toString() {
		return "BranchVO [brcId=" + brcId + ", brcArea=" + brcArea + ", brcName=" + brcName + ", brcTheaterNumbers="
				+ brcTheaterNumbers + ", brcTheaterSeats=" + brcTheaterSeats + ", arrBrcTheaterSeats="
				+ Arrays.toString(arrBrcTheaterSeats) + "]";
	}
}
