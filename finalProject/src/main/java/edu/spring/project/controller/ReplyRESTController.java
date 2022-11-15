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

//* RESTful URL�� �ǹ�
// (POST)	/replies  		 : ����߰� (=insert) /replies (POST)
// (GET) 	/replies/all/���� : �ش� �� ��ȣ(boardId)�� ��� ��� �˻� (=select) /replies/all/����(GET)
// (PUT) 	/replies/����  	 : �ش� ��� ��ȣ(replyId)�� ������ ���� (=update) /replies/����(PUT)
// (DELETE)	/replies/���� 	 : �ش� ��� ��ȣ(replyId)�� ��� ���� (=delete) /replies/����(DELETE)

@RestController // http://localhost:8080/ex03/replies
// @RestController�� ��� ��Ʈ�ѷ� �޼ҵ����, @ResponseBody ������̼� ����, 
// �䰡 �ƴ� ������ ��ü�� Ŭ���̾�Ʈ(������)���� ����(����)�ϴ� �޼ҵ尡 ��
// = @Controller + @ResponseBody ������
@RequestMapping(value="/replies")

public class ReplyRESTController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ReplyRESTController.class);
	
//	@Autowired
//	private ReplyService replyService;
//	
//	// **POST�� �׽�Ʈ �Ҷ� URL(=GET���)�δ� �Ҽ� ������ API tester�� ����ؾ���
//	@PostMapping // POST : ��� �Է�, void �ٲ�
//	public ResponseEntity<Integer> createReply(@RequestBody ReplyVO vo) {
//		// @RequestBOdy - Ŭ��κ��� ���۹��� json �����͸� �ڹٰ�ü�� ��ȯ ������
//		logger.info("createReply call : vo = " + vo.toString());				
//		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü
//		// - �����Ϳ� HttpStatus�� ����
//		// - <T> : �������� �ϴ� ������ Ÿ��
//		int result = 0;
//		try {
//			result = replyService.create(vo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);			
//	}//end create
//	
//	// responseEntity�� list<replyVO> : ��� �ϳ��ƴϴ�... ����Ÿ�Ը��
//	@GetMapping("/all/{boardId}") // GET : ��ۼ���(all)/����
//	public ResponseEntity<List<ReplyVO>> readReplies(@PathVariable("boardId") int boardId) {
//		// @PathVariable("boardId") : /all/{boardId} ���� ������ ������ ����		
//		List<ReplyVO> list = replyService.read(boardId);
//		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);				
//	}//end readReplies
//	
//	@PutMapping("/{replyId}") // (PUT)/replies/����
//	public ResponseEntity<Integer> updateReply(@PathVariable("replyId") int replyId,
//			@RequestBody ReplyVO vo) {
//		vo.setReplyId(replyId);
//		int result = replyService.update(vo);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}//end put
//	
//	// replyServiceImple�� boardId�� ������� replyCntī��Ʈ -1 ����...
//	@DeleteMapping("/{replyId}")  // (DELETE)/replies/����
//	public ResponseEntity<Integer> deleteReply(@PathVariable("replyId") int replyId,
//			@RequestBody ReplyVO vo) {
//		// **���ڷ� ���� vo�� replyId���� �����Ͱ� �ȵ�����
//		// �ֳĸ� jsp���� replyId�� url�� �޾ƿ԰�, vo�� data�� �޾ƿ��� ������
//		int result = 0;
//		try {
//			result = replyService.delete(replyId, vo.getBoardId());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}//end delete
	
}//end replyRC
