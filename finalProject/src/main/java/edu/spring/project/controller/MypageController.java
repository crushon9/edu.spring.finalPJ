package edu.spring.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.MemberVO;
import edu.spring.project.service.MemberService;
import edu.spring.project.service.MypageService;

// ���ų��� ��ȸ, �ۼ��ı⺸��, ���̵���ã��(api���� ����)
// ��� ��ȸ, ����, ����
@Controller
@RequestMapping(value = "/mypage") // url: /project/mypage
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private MemberService memberService;
	
	// member ���� ��
		@GetMapping("/detail")
		public void detailGET(String mmbId) {
			logger.info("detailGET() call : memberId = " + mmbId);
			MemberVO vo = memberService.read(mmbId);
		}// end detailGet()

		// update call
		@GetMapping("/update")
		public void updateGET(String mmbId) {
			logger.info("updateGET() call");
			MemberVO vo = memberService.read(mmbId);
		}// end updateGet()

		// update data ������
		@PostMapping("update")
		public void updatePOST(MemberVO vo) {
			logger.info("updatePOST() call : vo = " + vo.toString());
			int result = memberService.update(vo);
		}// end updatePost()

		// delete 
		@PostMapping("/delete")
		public void deletePOST(String mmbId) {
			logger.info("deletePOST() call : mmbId = " + mmbId);
			int result = memberService.delete(mmbId);
		}// end deletePost()
	
	@PostMapping("/update")
	public void updatePOST() {

	}

	// ���� ��ȸ
	@GetMapping(value = "/ticket")
	public void myplabGET(Model model, HttpServletRequest request) {
		logger.info("myplabGET ȣ��");
		logger.info("myplab(��û ����) ȣ��");
		HttpSession session = request.getSession();
		String mmbId = ((MemberVO) session.getAttribute("MemberVO")).getMmbId();

//		List<TicketVO> ticketList = mypageService.readTicketList(mmbId);
//		List<MemberVO> ticketVOList = new ArrayList<MemberVO>();
//		for (TicketVO vo : ticketList) {
//			int TicketId = vo.getTicketId();
//			TicketVO ticketVO = (TicketVO) ticketService.read(ticketId);
//			ticketVOList.add(ticketVO);
//		}
//		model.addAttribute("ticketList", ticketList);
	}

}
