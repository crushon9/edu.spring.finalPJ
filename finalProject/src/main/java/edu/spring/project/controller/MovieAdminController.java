package edu.spring.project.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

@Controller
@RequestMapping(value = "/movie/admin") // url: /project/movie/admin
public class MovieAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MovieAdminController.class);

	@Autowired
	private MovieService movieService;

	// for upload images
	@Resource(name = "uploadPath")
	private String uploadPath;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGET()

	@PostMapping("/register")
	public String registerPOST(MovieVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		logger.info("registerPOST() call");
		int result = movieService.create(vo);
		logger.info("result = " + result);
		if (result == 1) {
			return "redirect:/movie/admin/list";
		} else {
			reAttr.addFlashAttribute("alertMovieRegister", "movieRegisterFail");
			return "redirect:/movie/admin/register";
		}
	} // end registerPOST()

	@GetMapping("/list") 
	public void listGET(Model model, String searchText, String inputDateStarted, String inputDateEnded) {
		logger.info("listGET() call");
		// searchText : keyword search
		if (searchText != null) {
			List<MovieVO> mvList = movieService.readSearch(searchText);
			model.addAttribute("mvList", mvList);
		} else if (inputDateStarted != null && inputDateEnded != null) {
			List<MovieVO> mvList = movieService.readPeriod(inputDateStarted, inputDateEnded);
			model.addAttribute("mvList", mvList);
		// default : order by ticketSales
		} else { 
			List<MovieVO> mvList = movieService.readOrderTicket();
			model.addAttribute("mvList", mvList);
		}
		// 각 영화마다 예매율을 구하기 위해 영화 전체 판매수를 DB에서 가져옴
		int mvTicketSalesTotal = movieService.readMvTicketSalesTotal();
		model.addAttribute("mvTicketSalesTotal", mvTicketSalesTotal);
	}// end listGET()

	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		logger.info(vo.toString());
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update") 
	public String updatePOST(MovieVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = movieService.update(vo);
		if (result == 1) {
			return "redirect:/movie/admin/list";
		} else {
			reAttr.addFlashAttribute("alertMovieUpdate", "movieUpdateFail");
			return "redirect:/movie/admin/update?mvId=" + vo.getMvId();
		}
	}// end updatePOST()

	@GetMapping("/delete")
	public String deleteGET(int mvId) {
		logger.info("deleteGET() call : boardId = " + mvId);
		int result = movieService.delete(mvId);
		if (result == 1) {
			return "redirect:/movie/admin/list";
		} else {
			return "redirect:/movie/admin/update?mvId=" + mvId;
		}
	}// end deleteGET()

}
