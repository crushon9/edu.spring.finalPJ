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

import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자(GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자(PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자(DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController // http://localhost:8080/ex03/replies
@RequestMapping(value="/replies")

public class MovieRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(MovieRESTController.class);
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping // POST : 댓글 입력, void 바꿈
	public ResponseEntity<Integer> createReply(@RequestBody MovieVO vo) {
		// @RequestBOdy
		// - 클라로부터 전송받은 json 데이터를 자바객체로 변환 어노따숑
		logger.info("createReply call : vo = " + vo.toString());				
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터와 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0;
		try {
			result = movieService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);			
	}//end create
	
	// responseEntity에 list<replyVO> : 댓글 하나아니다... 리턴타입명명
	@GetMapping("/all/{mvId}") // GET : 댓글선택(all)
//	public ResponseEntity<List<MovieVO>> readReplies(
//			@PathVariable("mvId") int mvId) {
		// @PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장		
	//	List<MovieVO> list = movieService.read(mvId);
	//	return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);				
//	}//end readReplies
		
	@PutMapping("/{mvId}") // put : 댓 수정
	public ResponseEntity<Integer> updateReply(
			@PathVariable("mvId") int mvId,
			@RequestBody MovieVO vo) {
		vo.setMvId(mvId);
		int result = movieService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);	
	}//end put
	
	// replyServiceImple로  boardId를 보내줘야 replyCnt카운트 -1 가능...
	@DeleteMapping("/{mvId}")
	public ResponseEntity<Integer> deleteReply(
			@PathVariable("mvId") int mvId,
			@RequestBody MovieVO vo) {
		int result = 0;
//		try {
//			result = movieService.delete(mvId, vo.getMvId());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}//end delete
			
}//end replyRC
