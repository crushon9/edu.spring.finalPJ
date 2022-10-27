package edu.spring.project.domain;

import java.util.Arrays;

public class BranchVO {

	private int brcId;
	private int brcArea;
	private String brcName;
	private int brcTheaterNumbers;
	private int[] brcTheaterSeats;

	public BranchVO() {
	}

	public BranchVO(int brcId, int brcArea, String brcName, int brcTheaterNumbers, int[] brcTheaterSeats) {
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

	public int[] getBrcTheaterSeats() {
		return brcTheaterSeats;
	}

	public void setBrcTheaterSeats(int[] brcTheaterSeats) {
		this.brcTheaterSeats = brcTheaterSeats;
	}

	@Override
	public String toString() {
		return "BranchVO [brcId=" + brcId + ", brcArea=" + brcArea + ", brcName=" + brcName + ", brcTheaterNumbers="
				+ brcTheaterNumbers + ", brcTheaterSeats=" + Arrays.toString(brcTheaterSeats) + "]";
	}

}
