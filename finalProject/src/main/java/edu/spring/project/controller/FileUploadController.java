package edu.spring.project.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

	// servlet-context.xml
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
	}// end uploadAjaxPOST()

	@GetMapping("/display")
	public ResponseEntity<byte[]> displayREST(String fileName) throws Exception {
		logger.info("displayREST() ȣ�� : fileName = " + fileName);
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		String filePath = uploadPath + "/" + fileName;
		logger.info(filePath);
		in = new FileInputStream(filePath);
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		logger.info(extension);
		HttpHeaders httpsHeader = new HttpHeaders();
		httpsHeader.setContentType(MediaUtil.geMediaType(extension));
		entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), httpsHeader, HttpStatus.OK);
		return entity;
	}// end display()

}
