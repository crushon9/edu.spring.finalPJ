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
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

@Controller
@RequestMapping(value = "/movie") // url: /project/movie
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	// main page
	@GetMapping("/main")
	public void mainGET(Model model, String orderChoice, String searchText) {
		logger.info("mainGET() call");
		if (searchText != null) {
			List<MovieVO> mvList = movieService.readSearch(searchText);
			model.addAttribute("mvList", mvList);
		} else if (orderChoice != null) {
			if (orderChoice.equals("ticketSales")) {
				List<MovieVO> mvList = movieService.readOrderTicket();
				model.addAttribute("mvList", mvList);
			} else if (orderChoice.equals("reviewAvg")) {
				List<MovieVO> mvList = movieService.readOrderReview();
				model.addAttribute("mvList", mvList);
			}
		} else { // default
			List<MovieVO> mvList = movieService.readOrderTicket();
			model.addAttribute("mvList", mvList);
		}
		// 영화 전체 예매 합
		int mvTicketSalesTotal = movieService.readMvTicketSalesTotal();
		model.addAttribute("mvTicketSalesTotal", mvTicketSalesTotal);
	}// end mainGET()

	// detail.jsp
	@GetMapping("/detail")
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
		// 영화 전체 예매 합
		int mvTicketSalesTotal = movieService.readMvTicketSalesTotal();
		model.addAttribute("mvTicketSalesTotal", mvTicketSalesTotal);
	}// end detailGET()

	@GetMapping("/mvRatingAvg/{mvId}")
	public ResponseEntity<Float> mvRatingAvgREST(@PathVariable("mvId") int mvId) {
		logger.info("mvRatingAvgREST() call : mvId = " + mvId);
		float mvRatingAvg = movieService.readRatingAvg(mvId);
		return new ResponseEntity<Float>(mvRatingAvg, HttpStatus.OK);
	}// end mvRatingAvgREST()

	// 스케줄에서 쓰는 비동기 검색
	@GetMapping("/list/{inputDateStarted}/{inputDateEnded}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDateStarted") String inputDateStarted,
			@PathVariable("inputDateEnded") String inputDateEnded) {
		logger.info(
				"listREST() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		List<MovieVO> list = movieService.readPeriod(inputDateStarted, inputDateEnded);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

	@GetMapping("/list/{inputDate}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDate") String inputDate) {
		logger.info("listREST() call : inputDate = " + inputDate);
		List<MovieVO> list = movieService.readDate(inputDate);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

}
