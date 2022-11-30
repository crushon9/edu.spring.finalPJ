package edu.spring.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.ReviewVO;
import edu.spring.project.service.ReviewService;

@Controller
@RequestMapping(value = "/review") // url: /project/review
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/register")
	public void registerGET(Model model, int mvId, String mvTitle, String mvImage) {
		logger.info("registerGET() call");
		model.addAttribute("mvId", mvId);
		model.addAttribute("mvTitle", mvTitle);
		model.addAttribute("mvImage", mvImage);
	}// end listGET()

	@PostMapping("/register")
	public void registerPOST(ReviewVO vo, Model model) {
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = reviewService.create(vo);
		if (result == 1) {
			logger.info(result + " data added");
			model.addAttribute("alertMassage", "reviewRegisterSuccess");
		} else {
			model.addAttribute("alertMassage", "reviewRegisterFail");
		}
	}// end registerPOST()

	@GetMapping("/{mvId}") // (GET)/review/mvId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mvId") int mvId) {
		logger.info("listREST() call : mvId = " + mvId);
		List<ReviewVO> list = reviewService.read(mvId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}// end listREST()

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() call");
	}// end listGET()

	@GetMapping("/list/{mmbId}") // (GET)/review/list/mmbId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mmbId") String mmbId) {
		logger.info("listREST() call : mmbId = " + mmbId);
		List<ReviewVO> list = reviewService.read(mmbId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}// end listREST()

	@PutMapping // (PUT)/review
	public ResponseEntity<Integer> updateREST(@RequestBody ReviewVO vo) {
		logger.info("updateREST() call" + vo.toString());
		int result = reviewService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateREST()

	@DeleteMapping // (DELETE)/review
	public ResponseEntity<Integer> deleteREST(@RequestBody ReviewVO vo) {
		logger.info("deleteREST() call" + vo.toString());
		int result = reviewService.delete(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end deleteREST()

	@GetMapping("/check/{mmbId}/{mvId}") // (GET)/review/check/mmbId/mvId
	public ResponseEntity<Integer> checkREST(@PathVariable("mmbId") String mmbId, @PathVariable("mvId") int mvId) {
		logger.info("checkREST() call" + mmbId + mvId);
		Integer result = reviewService.check(mmbId, mvId);
		logger.info(result.toString());
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end checkREST()

}
