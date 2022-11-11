package edu.spring.project.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

@Controller
@RequestMapping(value = "/movie") // url: /project/movie
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	// 업로드용 어노따숑
	@Resource(name = "uploadPath")
	private String uploadPath;

	@GetMapping("/main") // 메인페이지에 리스트 예매율 기준 쇼하기
	public void mainGET(Model model, int orderChoice) {
		logger.info("mainGET() call");
		if (orderChoice == 1) {
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		} else if (orderChoice == 2) {
			List<MovieVO> mvList = movieService.readRa();
			model.addAttribute("mvList", mvList);
		}
	}// end mainGet()
	
	// 해당 주소jsp를 호출 기능
	@GetMapping("/admin/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}// end registerGet()

	// 데이터 전달
	@PostMapping("/admin/register")
	public void registerPOST(Model model, MovieVO vo) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());

		int result = movieService.create(vo);
		logger.info("result = " + result);
		model.addAttribute("vo", vo);
	} // end registerPost()

	// mvId로 전체 내용
	@GetMapping("/detail")
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
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
