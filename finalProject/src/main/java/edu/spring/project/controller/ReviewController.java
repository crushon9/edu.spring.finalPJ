package edu.spring.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

	@PostMapping // (POST)/review
	public ResponseEntity<Integer> registerREST(@RequestBody ReviewVO vo) {
		// @RequestBody - 클라로부터 전송받은 json 데이터를 자바객체로 변환 어노따숑
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체 (<T>:보내고자 하는 데이터 타입)
		// - 데이터와 HttpStatus를 전송
		int result = reviewService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/{mvId}") // (GET)/review/mvId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mvId") int mvId) {
		logger.info("listREST() call : mvId = " + mvId);
		List<ReviewVO> list = reviewService.read(mvId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}

	// 마이페이지의 유저리뷰조회 list.jsp 반환
	@GetMapping("/list") 
	public void listGET() {
		logger.info("listGET() call");
	}

	@GetMapping("/list/{mmbId}") // (GET)/review/list/mmbId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mmbId") String mmbId) {
		logger.info("listREST() 호출 : mmbId = " + mmbId);
		List<ReviewVO> list = reviewService.read(mmbId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}

	@PutMapping // (PUT)/review
	public ResponseEntity<Integer> updateREST(@RequestBody ReviewVO vo) {
		logger.info("updateREST() call" + vo.toString());
		int result = reviewService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping // (DELETE)/review
	public ResponseEntity<Integer> deleteREST(@RequestBody ReviewVO vo) {
		logger.info("deleteREST() call" + vo.toString());
		int result = reviewService.delete(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
