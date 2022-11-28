package edu.spring.project.domain;

import java.util.Arrays;

public class BranchVO {

	private int brcId;
	private int brcArea;
	private String brcName;
	private int brcTheaterNumbers;
	private String brcTheaterSeats;
	private int[] arrBrcTheaterSeats;
	private String brcInfo;
	
	public BranchVO() {
	}

	public BranchVO(int brcId, int brcArea, String brcName, int brcTheaterNumbers, String brcTheaterSeats, String brcInfo) {
		this.brcId = brcId;
		this.brcArea = brcArea;
		this.brcName = brcName;
		this.brcTheaterNumbers = brcTheaterNumbers;
		this.brcTheaterSeats = brcTheaterSeats;
		this.brcInfo = brcInfo;
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

	// int[] arrBrcTheaterSeats 
	public String getBrcTheaterSeats() {
		return Arrays.toString(arrBrcTheaterSeats).replace("[", "").replace("]", "");
	}

	public void setBrcTheaterSeats(String brcTheaterSeats) {
		this.brcTheaterSeats = brcTheaterSeats;
		// String Array
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
	
	public String getBrcInfo() {
		return brcInfo;
	}

	public void setBrcInfo(String brcInfo) {
		this.brcInfo = brcInfo;
	}
	
	@Override
	public String toString() {
		return "BranchVO [brcId=" + brcId + ", brcArea=" + brcArea + ", brcName=" + brcName + ", brcTheaterNumbers="
				+ brcTheaterNumbers + ", brcTheaterSeats=" + brcTheaterSeats + ", arrBrcTheaterSeats="
				+ Arrays.toString(arrBrcTheaterSeats) + "]" + ", brcInfo=" + brcInfo;
	}
}
