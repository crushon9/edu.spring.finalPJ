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

// @RestController의 모든 콘트롤러 메소드들은, @ResponseBody 어노테이션 없이, 
// 뷰가 아닌 데이터 자체를 클라이언트(브라우저)에게 서비스(리턴)하는 메소드가 됨
@RestController
@RequestMapping(value = "/review")
public class ReviewRESTController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewRESTController.class);

	@Autowired
	private ReviewService reviewService;

	// **POST는 테스트 할때 URL(=GET방식)로는 할수 없으니 API tester를 사용해야함
	@PostMapping // POST : 댓글 입력, void 바꿈
	public ResponseEntity<Integer> registerREST(@RequestBody ReviewVO vo) {
		// @RequestBOdy - 클라로부터 전송받은 json 데이터를 자바객체로 변환 어노따숑
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터와 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0;
		try {
			result = reviewService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end create

	// responseEntity에 list<replyVO> : 댓글 하나아니다... 리턴타입명명
	@GetMapping("/all/{mvId}") // GET : 댓글선택(all)/숫자
	public ResponseEntity<List<ReviewVO>> listREST(@PathVariable("mvId") int mvId) {
		logger.info("listREST() call : mvId = " + mvId);
		List<ReviewVO> list = reviewService.read(mvId);
		return new ResponseEntity<List<ReviewVO>>(list, HttpStatus.OK);
	}// end readReplies

	@PutMapping("/{rvId}") // (PUT)/review/숫자
	public ResponseEntity<Integer> updateREST(@PathVariable("rvId") int rvId, @RequestBody ReviewVO vo) {
		logger.info("updateREST() call : rvId = " + rvId);
		vo.setRvId(rvId);
		int result = reviewService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end put

	// replyServiceImple로 boardId를 보내줘야 replyCnt카운트 -1 가능...
	@DeleteMapping("/{rvId}") // (DELETE)/replies/숫자
	public ResponseEntity<Integer> deleteREST(@PathVariable("rvId") int rvId, @RequestBody ReviewVO vo) {
		logger.info("deleteREST() call : rvId = " + rvId);
		vo.setRvId(rvId);
		// **인자로 받은 vo의 replyId에는 데이터가 안들어가있음
		// 왜냐면 jsp에서 replyId는 url로 받아왔고, vo는 data로 받아오기 때문에
		int result = 0;
		try {
			result = reviewService.delete(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end delete

}// end replyRC
