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
		// @RequestBody - Ŭ��κ��� ���۹��� json �����͸� �ڹٰ�ü�� ��ȯ ������
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü (<T>:�������� �ϴ� ������ Ÿ��)
		// - �����Ϳ� HttpStatus�� ����
		int result = reviewService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/{mvId}") // (GET)/review/mvId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mvId") int mvId) {
		logger.info("listREST() call : mvId = " + mvId);
		List<ReviewVO> list = reviewService.read(mvId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}

	// ������������ ����������ȸ list.jsp ��ȯ
	@GetMapping("/list") 
	public void listGET() {
		logger.info("listGET() call");
	}

	@GetMapping("/list/{mmbId}") // (GET)/review/list/mmbId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mmbId") String mmbId) {
		logger.info("listREST() ȣ�� : mmbId = " + mmbId);
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
