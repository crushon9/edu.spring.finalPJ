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
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.project.util.FileUploadUtil;
import edu.spring.project.util.MediaUtil;

@Controller
@RequestMapping
public class FileUploadController {

	private static final Logger logger =
			LoggerFactory.getLogger(FileUploadController.class);
	
	// servlet-context.xml 파일 설정된 문자열 리소스 주입
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/upload")
	public void uploadGET() {
		logger.info("uploadGET() call : " + uploadPath);			
	}//end uploadget
	
	// singlefile handler
	@PostMapping("/upload")
	public void uploadPOST(MultipartFile file, Model model) {
		logger.info("uploadPOST() call");
		logger.info("filename : " + file.getOriginalFilename());
		logger.info("filesize(byte) : " + file.getSize());			
		String savedFile = saveUploadFile(file);	
		logger.info(savedFile);
	}//end uploadPost
	
	// multiple file handler
	@PostMapping("/upload2")
	public String uploadPost2(MultipartFile[] files, Model model) {
		String savedFiles = "";
		for(MultipartFile f : files) {
			savedFiles += saveUploadFile(f) + " ";
		}		
		logger.info("savedFiles = " + savedFiles);
		return "upload";
	}//end uploadPost2
		
	@GetMapping("/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET call");			
	}//end uploadAjaxG -> http://localhost:8080/project/upload-ajax
		
	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] files) throws IOException {
		logger.info("uploadAjaxPorst call");
		
		// single file saving
		String result = null;
		// save returns 'the file path'
		result = FileUploadUtil.saveUploadedFile(uploadPath, 
				files[0].getOriginalFilename(), files[0].getBytes());		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}//end uploadAjaxPost()
	
	// display function call : can take the images from the server 
	// sh be sent file path 
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		logger.info("display call");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		// 저장 경로 업로드패쓰(C:\Study\FileUploadTest) + 파일명
		String filePath = uploadPath + fileName; //absol path
		in = new FileInputStream(filePath);
		
		// file extension
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info(extension);
		
		// 응답 헤더/response header에 content-type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.geMediaType(extension));
		
		// send data 
		entity = new ResponseEntity<byte[]>	(IOUtils.toByteArray(in),
			httpHeaders, HttpStatus.OK);
		return entity;
		// http://localhost:8080/project/display?fileName=/ds.JPG
	}//end display 
		
	private String saveUploadFile(MultipartFile file) {		
		// UUID : 업로드 파일명 중복 방지
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		
		try {
			// actually save files
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("saved");
			return savedName;
		} catch (IOException e) {
			logger.info("failed");			
			e.printStackTrace();
			return null;
		}		
		
	}//end saveUploadFile
			
}//end File UC
