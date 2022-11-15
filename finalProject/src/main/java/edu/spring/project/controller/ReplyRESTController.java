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

//* RESTful URL과 의미
// (POST)	/replies  		 : 댓글추가 (=insert) /replies (POST)
// (GET) 	/replies/all/숫자 : 해당 글 번호(boardId)의 모든 댓글 검색 (=select) /replies/all/숫자(GET)
// (PUT) 	/replies/숫자  	 : 해당 댓글 번호(replyId)의 내용을 수정 (=update) /replies/숫자(PUT)
// (DELETE)	/replies/숫자 	 : 해당 댓글 번호(replyId)의 댓글 삭제 (=delete) /replies/숫자(DELETE)

@RestController // http://localhost:8080/ex03/replies
// @RestController의 모든 콘트롤러 메소드들은, @ResponseBody 어노테이션 없이, 
// 뷰가 아닌 데이터 자체를 클라이언트(브라우저)에게 서비스(리턴)하는 메소드가 됨
// = @Controller + @ResponseBody 섞은거
@RequestMapping(value="/replies")

public class ReplyRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyRESTController.class);
	
//	@Autowired
//	private ReplyService replyService;
//	
//	// **POST는 테스트 할때 URL(=GET방식)로는 할수 없으니 API tester를 사용해야함
//	@PostMapping // POST : 댓글 입력, void 바꿈
//	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo) {
//		// @RequestBOdy - 클라로부터 전송받은 json 데이터를 자바객체로 변환 어노따숑
//		logger.info("createReply call : vo = " + vo.toString());				
//		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
//		// - 데이터와 HttpStatus를 전송
//		// - <T> : 보내고자 하는 데이터 타입
//		int result = 0;
//		try {
//			result = replyService.create(vo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);			
//	}//end create
//	
//	// responseEntity에 list<replyVO> : 댓글 하나아니다... 리턴타입명명
//	@GetMapping("/all/{boardId}") // GET : 댓글선택(all)/숫자
//	public ResponseEntity<List<ReplyVO>> readReplies(@PathVariable("boardId") int boardId) {
//		// @PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장		
//		List<ReplyVO> list = replyService.read(boardId);
//		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);				
//	}//end readReplies
//	
//	@PutMapping("/{replyId}") // (PUT)/replies/숫자
//	public ResponseEntity<Integer> updateReply(@PathVariable("replyId") int replyId,
//			@RequestBody ReplyVO vo) {
//		vo.setReplyId(replyId);
//		int result = replyService.update(vo);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}//end put
//	
//	// replyServiceImple로 boardId를 보내줘야 replyCnt카운트 -1 가능...
//	@DeleteMapping("/{replyId}")  // (DELETE)/replies/숫자
//	public ResponseEntity<Integer> deleteReply(@PathVariable("replyId") int replyId,
//			@RequestBody ReplyVO vo) {
//		// **인자로 받은 vo의 replyId에는 데이터가 안들어가있음
//		// 왜냐면 jsp에서 replyId는 url로 받아왔고, vo는 data로 받아오기 때문에
//		int result = 0;
//		try {
//			result = replyService.delete(replyId, vo.getBoardId());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}//end delete
	
}//end replyRC
