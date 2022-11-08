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
import edu.spring.project.util.MediaUtil;

@Controller // admin, user 둘다 접속 가능
@RequestMapping(value = {"/admin/movie", "/user"}) // url: /project/admin/movie or /project/user/movie
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	// 업로드용 어노따숑
	@Resource(name = "uploadPath")
	private String uploadPath;


	@GetMapping("/main") // 메인페이지에 리스트 쇼하기
	public void mainGET(Model model) {
		logger.info("mainGET() call");
	//	List<MovieVO> list = movieService.read();
	//	model.addAttribute("list", list);
	}// end mainGet()

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}// end registerGet()

	@PostMapping("/register")
	public void registerPOST(Model model, MovieVO vo) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());

		int result = movieService.create(vo);
		logger.info("result = " + result);
		model.addAttribute("vo", vo);
	} // end registerPost()

	@GetMapping("/imgDisplay") // 파일 이미지 업로드용
	public ResponseEntity<byte[]> imgDisplay(String fileName) throws Exception {
		logger.info("imgDisplay() 호출 : fileName = " + fileName);

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
	}//end saveUploadFile()

	@GetMapping("/detail") 
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
	//	MovieVO vo = movieService.read(mvId);
	//	model.addAttribute("vo", vo);
	}// end detail()

	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		// page로 전송한다
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update") // void 에서 String으로 바꿈
	public void updatePOST(MovieVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = movieService.update(vo);
//		if(result == 1) {
//			// list + ?page=
//			return "redirect:/board/list?page=" + page;
//			// else 부분 return 빠지면 오류 쫘르르를
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
		logger.info("listREST() 호출 : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		List<MovieVO> list = movieService.select(inputDateStarted, inputDateEnded);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

	@GetMapping("/list/{inputDate}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDate") String inputDate) {
		logger.info("listREST() 호출 : inputDate = " + inputDate);
		List<MovieVO> list = movieService.select(inputDate);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

}
