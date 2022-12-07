package edu.spring.project.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCompareUtil {

	private static final Logger logger = LoggerFactory.getLogger(TimeCompareUtil.class);

	private static ArrayList<String> scdTimeArray = new ArrayList<String>(
			Arrays.asList("00:00", "00:30", "01:00", "01:30", "02:00", "07:00", "07:30", "08:00", "08:30", "09:00",
					"09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
					"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
					"20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"));
	// "23:30", "24:00" - js publicScdTimeArray에는 없지만 현재시간과 비교를 위해 추가

	// 시간 포함 비교
	public static String compareToNow(String inputDate, int scdTime) {
		logger.info("compareToNow() 호출 scdTime=" + scdTime);
		// 현재 날짜 시간 변수 now 생성
		LocalDateTime now = LocalDateTime.now();
		// 전달된 date와 scdTime를 LocalDateTime 타입으로 변환
		String inputStr = inputDate + "T" + scdTimeArray.get(scdTime) + ":00.000";
		LocalDateTime input = LocalDateTime.parse(inputStr);
		// input 값과 now 비교
		String result = "";
		if (input.isBefore(now)) {
			result = "before";
		}
		if (input.isAfter(now)) {
			result = "after";
		}
		if (input.isEqual(now)) {
			result = "equals";
		}
		logger.info("시간 비교 결과:" + result);
		return result;
	}

	// 날짜 비교
	public static String compareToToday(String inputDate) {
		logger.info("compareToToday() 호출 inputDate=" + inputDate);
		// 오늘 날짜 변수 today 생성
		LocalDate today = LocalDate.now();
		// inputDate를 split 년 월 일로 분할
		String[] split = inputDate.split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);
		int dayOfMonth = Integer.parseInt(split[2]);
		// LocalDate 타입으로 변환
		LocalDate inputDay = LocalDate.of(year, month, dayOfMonth);
		// inputDay 와 today 비교
		String result = "";
		if (inputDay.isEqual(today)) {
			result = "equals";
		}
		if (inputDay.isBefore(today)) {
			result = "before";
		}
		if (inputDay.isAfter(today)) {
			result = "after";
		}
		logger.info("날짜 비교 결과:" + result);
		return result;
	}

	// 현재 시간을 인덱스로 변환
	public static int nowConvertToScdIndex() {
		// 현재시간 변수 now 생성
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter hourfmt = DateTimeFormatter.ofPattern("HH");
		DateTimeFormatter minutefmt = DateTimeFormatter.ofPattern("mm");
		// now에서 시간과 분을 추출
		Integer hour = Integer.parseInt(now.format(hourfmt));
		Integer minute = Integer.parseInt(now.format(minutefmt));
		// 분이 30분 보다 작으면 30분으로 세팅하고
		if (minute <= 30) {
			minute = 3;
			// 30분보다 크면 시간을 1증가시키며 00분으로 세팅
		} else {
			hour++;
			minute = 0;
		}
		// scdTimeArray와 비교 하기 위해 시간과 분을 String 형태로 변환
		String result = "";
		// 시간이 10보다 작으면 앞에 0을 붙이고
		if (hour < 10) {
			result = "0" + hour + ":" + minute.toString() + "0";
		} else {
			result = hour + ":" + minute.toString() + "0";
		}
		int scdTime = scdTimeArray.indexOf(result);
		// 만약 일치하는 인덱스를 발견하지 못하면 07:00으로 처리한다
		if (scdTime == -1) {
			scdTime = scdTimeArray.indexOf("07:00");
		}
		logger.info("현재시간변환String=" + result + ", scdTime=" + scdTime);
		return scdTime;
	}
}
