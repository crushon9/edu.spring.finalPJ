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

	// site main page => movie list show
	@GetMapping("/main")
	public void mainGET(Model model, String orderChoice, String searchText) {
		logger.info("mainGET() call");
		// searchText : keyword search
		if (searchText != null) {
			List<MovieVO> mvList = movieService.readSearch(searchText);
			model.addAttribute("mvList", mvList);
		} else if (orderChoice != null) {
			// order by ticketSales(main.jsp의 select value)
			if (orderChoice.equals("ticketSales")) {
				List<MovieVO> mvList = movieService.readOrderTicket();
				model.addAttribute("mvList", mvList);
			// order by reviewAvg(main.jsp의 select value)
			} else if (orderChoice.equals("reviewAvg")) {
				List<MovieVO> mvList = movieService.readOrderReview();
				model.addAttribute("mvList", mvList);
			}
		// default : order by ticketSales
		} else { 
			List<MovieVO> mvList = movieService.readOrderTicket();
			model.addAttribute("mvList", mvList);
		}
		// 각 영화마다 예매율을 구하기 위해 영화 전체 판매수를 DB에서 가져옴
		int mvTicketSalesTotal = movieService.readMvTicketSalesTotal();
		model.addAttribute("mvTicketSalesTotal", mvTicketSalesTotal);
	}// end mainGET()

	// detail.jsp
	@GetMapping("/detail")
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
		// 각 영화마다 예매율을 구하기 위해 영화 전체 판매수를 DB에서 가져옴
		int mvTicketSalesTotal = movieService.readMvTicketSalesTotal();
		model.addAttribute("mvTicketSalesTotal", mvTicketSalesTotal);
	}// end detailGET()

	@GetMapping("/mvRatingAvg/{mvId}")
	public ResponseEntity<Float> mvRatingAvgREST(@PathVariable("mvId") int mvId) {
		logger.info("mvRatingAvgREST() call : mvId = " + mvId);
		float mvRatingAvg = movieService.readRatingAvg(mvId);
		return new ResponseEntity<Float>(mvRatingAvg, HttpStatus.OK);
	}// end mvRatingAvgREST()

	// Asynchronous : for schedule table
	@GetMapping("/list/{inputDateStarted}/{inputDateEnded}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDateStarted") String inputDateStarted,
			@PathVariable("inputDateEnded") String inputDateEnded) {
		logger.info("listREST() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		List<MovieVO> list = movieService.readPeriod(inputDateStarted, inputDateEnded);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

	// Asynchronous : for schedule table
	@GetMapping("/list/{inputDate}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDate") String inputDate) {
		logger.info("listREST() call : inputDate = " + inputDate);
		List<MovieVO> list = movieService.readDate(inputDate);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}// end listREST()

}
