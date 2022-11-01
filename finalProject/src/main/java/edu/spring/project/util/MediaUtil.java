package edu.spring.project.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtil {

	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		mediaMap.put("JFIF", MediaType.IMAGE_JPEG); // 신규 확장자명 추가
	}
	
	public static MediaType geMediaType(String type) {
		return mediaMap.get(type.toUpperCase()); // 소문자 -> 대문자
	}
	
}
