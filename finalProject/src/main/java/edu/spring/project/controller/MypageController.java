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

// 예매내역 조회, 작성후기보기, 아이디비번찾기(api관련 추후)
// 멤버 조회, 수정, 삭제
@Controller
@RequestMapping(value = "/mypage") // url: /project/mypage
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	private MypageService mypageService;
	
	@Autowired
	private MemberService memberService;
	
	// member 정보 상세
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

		// update data 보내기
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

	// 예매 조회
	@GetMapping(value = "/ticket")
	public void myplabGET(Model model, HttpServletRequest request) {
		logger.info("myplabGET 호출");
		logger.info("myplab(신청 내역) 호출");
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
