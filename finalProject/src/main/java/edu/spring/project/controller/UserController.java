package edu.spring.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;
import edu.spring.project.service.ScheduleService;
import edu.spring.project.util.MediaUtil;

@Controller
@RequestMapping(value = "/user") // url: /project/user
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private MovieService movieService;
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping("/schedule")
	public void scheduleGET() {
		logger.info("scheduleGET() ȣ��");
	}

	// ���ε�� ������
	@Resource(name = "uploadPath")
	private String uploadPath;

	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("listGET() call");
		List<MovieVO> list = movieService.read();
		model.addAttribute("list", list);
	}// end listGet()

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}// end registerGet()

	@PostMapping("/register")
	public void registerPOST(@RequestParam("files") MultipartFile file, Model model, MovieVO vo) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info("���� �̸� : " + file.getOriginalFilename());
		logger.info("���� ũ�� : " + file.getSize());
		logger.info(vo.toString());
		String savedFile = saveUploadFile(file);
		logger.info(savedFile);

		vo.setMvImage(savedFile);

		int result = movieService.create(vo);
		logger.info("vo = " + vo);
	} // end registerPost()

	@GetMapping("/display") 
	public ResponseEntity<byte[]> display(String fileName) throws Exception {
		logger.info("display() ȣ��");

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
	}//end display()

	private String saveUploadFile(MultipartFile file) {
		// UUID : ���ε��ϴ� ���� �̸��� �ߺ����� �ʵ���
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);

		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("���� ���� ����");
			return savedName;
		} catch (IOException e) {
			logger.info("���� ���� ����");
			e.printStackTrace();
			return null;
		}
	}//end saveUploadFile()

	@GetMapping("/detail") // String mvId�� ���� EU
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
	//	MovieVO vo = movieService.read(mvId);
	//	model.addAttribute("vo", vo);
	}// end detail()

	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		// page�� �����Ѵ�
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update") // void ���� String���� �ٲ�
	public void updatePOST(MovieVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = movieService.update(vo);
//		if(result == 1) {
//			// list + ?page=
//			return "redirect:/board/list?page=" + page;
//			// else �κ� return ������ ���� �Ҹ�����
//		} else {
//			return "redirect:/board/update?boardOd=" + vo.getBoardId();		
//		}				
	}// end updatePost()

	@PostMapping("/delete")
	public void deletePOST(int mvId) {
		logger.info("deletePOST() call : boardId = " + mvId);
		int result = movieService.delete(mvId);
//		if(result == 1) {			
//			return "redirect:/board/list";
//		} else {
//			return "redirect:/board/list";
//		}

	}// end deletePost()

	@GetMapping("/list/{inputDateStarted}/{inputDateEnded}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDateStarted") String inputDateStarted,
			@PathVariable("inputDateEnded") String inputDateEnded) {
		logger.info("listREST() ȣ�� : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		List<MovieVO> list = movieService.select(inputDateStarted, inputDateEnded);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

	@GetMapping("/list/{inputDate}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDate") String inputDate) {
		logger.info("listREST() ȣ�� : inputDate = " + inputDate);
		List<MovieVO> list = movieService.select(inputDate);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

}
