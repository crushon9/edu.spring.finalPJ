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
	public void mainGET(Model model, String orderChoice) {
		logger.info("mainGET() call");
		if (orderChoice == "ts") {
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		} else if (orderChoice == "ra") {
			List<MovieVO> mvList = movieService.readRa();
			model.addAttribute("mvList", mvList);
		} else {
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		}
	}// end mainGet()

	@GetMapping("/admin/list") // 리스트 쇼하기
	public void listGET(Model model) {
		logger.info("listGET() call");
		List<MovieVO> mvList = movieService.readTs();
		model.addAttribute("mvList", mvList);
	}// end listGET()

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

	// mvId로 전체 내용 show, -> detail.jsp
	@GetMapping("/detail")
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}// end detail()

	@GetMapping("/admin/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);		
		MovieVO vo = movieService.read(mvId);
		logger.info(vo.toString());
		// page로 전송한다
		model.addAttribute("vo", vo);		
	}// end updateGET()

	@PostMapping("/admin/update") // void 에서 String으로 바꿈
	public String updatePOST(MovieVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = movieService.update(vo);
		if (result == 1) {
			// "list?page=" + page
			// else 부분 return 빠지면 오류 쫘르르를
			return "redirect:/movie/admin/list";
		} else {
			return "redirect:/movie/admin/update?mvId=" + vo.getMvId();			
		}
	}// end updatePost()

	@GetMapping("/admin/delete")
	public String deleteGET(int mvId) {
		logger.info("deleteGET() call : boardId = " + mvId);
		int result = movieService.delete(mvId);
		if(result == 1) {			
			return "redirect:/movie/admin/list";
		} else {
			return "redirect:/movie/admin/update?mvId=" + mvId;
		}
	}// end deleteGET()

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

	// 다시보자
	@GetMapping("/review/register")
	public void reviewRegister(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}// end reviewRegister
	
}
