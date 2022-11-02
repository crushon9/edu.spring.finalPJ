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

// * RESTful url�� �ǹ�
// /replies (POST) : ��� �߰�(insert)
// /replies/all/����(GET) : �ش� �� ��ȣ(boardId)�� ��� ��� �˻�(select)
// /replies/����(PUT) : �ش� ��� ��ȣ(replyId)�� ������ ����(update)
// /replies/����(DELETE) : �ش� ��� ��ȣ(replyId)�� ����� ����(delete)

@RestController // http://localhost:8080/ex03/replies
@RequestMapping(value="/replies")

public class MovieRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(MovieRESTController.class);
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping // POST : ��� �Է�, void �ٲ�
	public ResponseEntity<Integer> createReply(@RequestBody MovieVO vo) {
		// @RequestBOdy
		// - Ŭ��κ��� ���۹��� json �����͸� �ڹٰ�ü�� ��ȯ ������
		logger.info("createReply call : vo = " + vo.toString());				
		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - �����Ϳ� HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0;
		try {
			result = movieService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);			
	}//end create
	
	// responseEntity�� list<replyVO> : ��� �ϳ��ƴϴ�... ����Ÿ�Ը��
	@GetMapping("/all/{mvId}") // GET : ��ۼ���(all)
//	public ResponseEntity<List<MovieVO>> readReplies(
//			@PathVariable("mvId") int mvId) {
		// @PathVariable("boardId") : /all/{boardId} ���� ������ ������ ����		
	//	List<MovieVO> list = movieService.read(mvId);
	//	return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);				
//	}//end readReplies
		
	@PutMapping("/{mvId}") // put : �� ����
	public ResponseEntity<Integer> updateReply(
			@PathVariable("mvId") int mvId,
			@RequestBody MovieVO vo) {
		vo.setMvId(mvId);
		int result = movieService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);	
	}//end put
	
	// replyServiceImple��  boardId�� ������� replyCntī��Ʈ -1 ����...
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
