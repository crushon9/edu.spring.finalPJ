package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="/member") // url : /project/member
public class MemberContorller {
	private static final Logger logger = LoggerFactory.getLogger(MemberContorller.class);
//	@Autowired
//	private BoardService boardService;
//
//	@GetMapping("/list") // return���� �����ϱ� list.jsp�� ��ȯ
//	public void list(Model model, Integer page, Integer numsPerPage) {
//		logger.info("list() ȣ��");
//		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
//
//		// Paging ó��
//		PageCriteria criteria = new PageCriteria();
//		if (page != null) {
//			criteria.setPage(page);
//		}
//		if (numsPerPage != null) {
//			criteria.setNumsPerPage(numsPerPage);
//		}
//
//		List<BoardVO> list = boardService.read(criteria);
//		if (!list.isEmpty()) { // list�� ������ ����ó��
//			model.addAttribute("list", list);
//			PageMaker pageMaker = new PageMaker(criteria, boardService.getTotalCount());
//			model.addAttribute("pageMaker", pageMaker);
//		}
//	}
//
//	@GetMapping("/register")
//	public void registerGET() {
//		logger.info("registerGET() ȣ��");
//	}
//
//	@PostMapping("/register")
//	// �ƴ� register.jsp ���� POST�� �����ϸ� �ڵ����� �Ķ���Ͱ��� vo�� ��ܼ� ������� ���°���? ����
//	public String registerPOST(BoardVO vo, RedirectAttributes reAttr) {
//		// RedirectAttributes
//		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
//		logger.info("registerPOST() ȣ��");
//		logger.info(vo.toString());
//		int result = boardService.create(vo);
//		logger.info(result + "�� ����");
//		if (result == 1) {
//			reAttr.addFlashAttribute("insert_result", "success"); // �ؿ� �̵��ϴ� ��ġ�� ����
//			return "redirect:/board/list"; // get���
//		} else {
//			reAttr.addFlashAttribute("insert_result", "fail");
//			return "redirect:/board/register"; // get��� registerGET()
//		}
//	}
//
//	@GetMapping("/detail")
//	public void detail(Model model, Integer boardId, PageCriteria criteria) {
//		logger.info("detail() ȣ�� : boardId = " + boardId);
//		logger.info(criteria.toString());
//		BoardVO vo = boardService.read(boardId);
//		model.addAttribute("vo", vo);
//		// �Խñ� �ۼ� �� �۸������ ���ư� �������� view�� ����
//		model.addAttribute("criteria", criteria);
//	}
//
//	@GetMapping("/update")
//	public void updateGET(Model model, Integer boardId, PageCriteria criteria) {
//		logger.info("updateGET() ȣ�� : boardId = " + boardId);
//		// ��ϵǾ��ִ� �Խñ� ������ �о�ͼ� view�� ����
//		BoardVO vo = boardService.read(boardId);
//		model.addAttribute("vo", vo);
//		// �Խñ� �ۼ� �� �۸������ ���ư� �������� �ӽ÷� view�� ����
//		model.addAttribute("criteria", criteria);
//	}
//
//	@PostMapping("/update")
//	public String updatePOST(BoardVO vo, PageCriteria criteria, RedirectAttributes reAttr) {
//		logger.info("updatePOST() ȣ��");
//		logger.info(criteria.toString());
//		int result = boardService.update(vo);
//		logger.info(result + "�� ����");
//		if (result == 1) {
//			reAttr.addFlashAttribute("update_result", "success");
//			// �Խñ� �ۼ� �� �۸������ ���ư� �������� get������� view�� ����
//			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
//		} else {
//			reAttr.addFlashAttribute("update_result", "fail");
//			return "redirect:/board/update?boardId=" + vo.getBoardId(); // updateGET()
//		}
//	}
//	
//	@PostMapping("/delete")
//	public String delete(Integer boardId, PageCriteria criteria, RedirectAttributes reAttr) {
//		logger.info("updateGET() ȣ�� : boardId = " + boardId);
//		int result = boardService.delete(boardId);
//		logger.info(result + "�� ����");
//		if (result == 1) {
//			reAttr.addFlashAttribute("delete_result", "success");
//			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
//		}else {
//			reAttr.addFlashAttribute("delete_result", "fail");
//			return "redirect:/board/list?page=" + criteria.getPage() + "&numsPerPage=" + criteria.getNumsPerPage();
//		}
//	}
}
