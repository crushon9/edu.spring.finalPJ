package edu.spring.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import edu.spring.project.util.FileUploadUtil;
import edu.spring.project.util.MediaUtil;

@Controller
@RequestMapping(value = "/img")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	// servlet-context.xml 파일 설정된 문자열 리소스 주입
	@Resource(name = "uploadPath")
	private String uploadPath;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadREST(MultipartFile[] files) throws IOException {
		logger.info("uploadREST() call");

		// single file saving
		String result = null;
		// save returns 'the file path'
		result = FileUploadUtil.saveUploadedFile(uploadPath, files[0].getOriginalFilename(), files[0].getBytes());
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}// end uploadAjaxPost()
	
	@GetMapping("/display") // 파일 이미지 업로드용
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		logger.info("display() 호출 : fileName = " + fileName);

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadPath + fileName;
		logger.info(filePath);
		in = new FileInputStream(filePath);

		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info(extension);

		HttpHeaders httpsHeader = new HttpHeaders();
		httpsHeader.setContentType(MediaUtil.geMediaType(extension));

		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpsHeader, HttpStatus.OK);

		return entity;
	}// end display()

	/*private String saveUploadFile(MultipartFile file) {
		// UUID : 업로드하는 파일 이름이 중복되지 않도록
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);

		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("파일 저장 성공");
			return savedName;
		} catch (IOException e) {
			logger.info("파일 저장 실패");
			e.printStackTrace();
			return null;
		}
	}// end saveUploadFile()

	// display function call : can take the images from the server
	// sh be sent file path
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		logger.info("display call");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		// 저장 경로 업로드패쓰(C:\Study\FileUploadTest) + 파일명
		String filePath = uploadPath + fileName; // absol path
		in = new FileInputStream(filePath);

		// file extension
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info(extension);

		// 응답 헤더/response header에 content-type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.geMediaType(extension));

		// send data
		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpHeaders, HttpStatus.OK);
		return entity;
		// http://localhost:8080/project/display?fileName=/ds.JPG
	}// end display*/

}// end File UC
