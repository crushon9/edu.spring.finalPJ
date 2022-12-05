package edu.spring.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCompareUtil {

	private static final Logger logger = LoggerFactory.getLogger(TimeCompareUtil.class);

	private ArrayList<String> scdTimeArray = new ArrayList<String>(Arrays.asList("07:00", "07:30", "08:00", "08:30",
			"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
			"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
			"21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00"));

	public TimeCompareUtil() {
	}

	// 날짜 비교
	public String compareToToday(String inputDate) {
		logger.info("compareToToday() 호출 inputDate=" + inputDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date input = null;
		try {
			input = sdf.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date today = new Date();

		String result = "";
		if (input.before(today)) {
			result = "before";
		}
		if (input.after(today)) {
			result = "after";
		}
		if (input.equals(today)) {
			result = "equals";
		}
		return result;
	}

	// 시간 포함 비교
	public String compareToNow(String inputDate, int scdTime) {
		logger.info("compareToNow() 호출 scdTime=" + scdTime);
		String inputStr = inputDate + "T" + scdTimeArray.get(scdTime) + ":00.000";
		LocalDateTime input = LocalDateTime.parse(inputStr);
		LocalDateTime now = LocalDateTime.now();

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
		return result;
	}

	// 현재 시간을 인덱스로 변환
	public int nowConvertToScdIndex() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter hourfmt = DateTimeFormatter.ofPattern("HH");
		DateTimeFormatter minutefmt = DateTimeFormatter.ofPattern("mm");
		Integer hour = Integer.parseInt(now.format(hourfmt));
		Integer minute = Integer.parseInt(now.format(minutefmt));
		if (30 >= minute) {
			minute = 3;
		} else {
			hour++;
			minute = 0;
		}
		String result = hour + ":" + minute.toString() + "0";
		int scdTime = scdTimeArray.indexOf(result);
		return scdTime;
	}
}
