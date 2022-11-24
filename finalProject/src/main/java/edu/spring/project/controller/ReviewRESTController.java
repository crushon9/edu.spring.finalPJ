package edu.spring.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.project.domain.ReviewVO;
import edu.spring.project.service.ReviewService;

// @RestController�� ��� ��Ʈ�ѷ� �޼ҵ����, @ResponseBody ������̼� ����, 
// �䰡 �ƴ� ������ ��ü�� Ŭ���̾�Ʈ(������)���� ����(����)�ϴ� �޼ҵ尡 ��
@RestController
@RequestMapping(value = "/review")
public class ReviewRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewRESTController.class);

	@Autowired
	private ReviewService reviewService;

	// **POST�� �׽�Ʈ �Ҷ� URL(=GET���)�δ� �Ҽ� ������ API tester�� ����ؾ���
	@PostMapping // (POST)/review
	public ResponseEntity<Integer> registerREST(@RequestBody ReviewVO vo) {
		// @RequestBOdy - Ŭ��κ��� ���۹��� json �����͸� �ڹٰ�ü�� ��ȯ ������
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - �����Ϳ� HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = reviewService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end create

	// responseEntity�� list<replyVO> : ��� �ϳ��ƴϴ�... ����Ÿ�Ը��
	@GetMapping("/{mvId}") // (GET)/review/mvId
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mvId") int mvId) {
		logger.info("listREST() call : mvId = " + mvId);
		List<ReviewVO> list = reviewService.read(mvId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}// end readReplies

	@PutMapping // (PUT)/review
	public ResponseEntity<Integer> updateREST(@RequestBody ReviewVO vo) {
		logger.info("updateREST() call" + vo.toString());
		int result = reviewService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end put

	// replyServiceImple�� boardId�� ������� replyCntī��Ʈ -1 ����...
	@DeleteMapping // (DELETE)/review
	public ResponseEntity<Integer> deleteREST(@RequestBody ReviewVO vo) {
		logger.info("deleteREST() call" + vo.toString());
		int result = reviewService.delete(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end delete

}// end replyRC
