package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.MemberVO;
import edu.spring.project.service.MemberService;

@Controller
@RequestMapping(value = "/member") // url: /project/member
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}//end registerGet()

	// 아니 register.jsp 에서 POST로 전송하면 자동으로 파라미터값이 vo에 담겨서 여기까지 오는거임? ㅇㅇ
	@PostMapping("/register")
	public void registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "행 삽입");		
	}//end registerPost()
	
	// 멤버 정보 보여주자.
	@GetMapping("/detail")
	public void detailGET(String mmbId) {
		logger.info("detailGET() call : memberId = " + mmbId);
		MemberVO vo = memberService.read(mmbId);
	}//end detailGet()
	
	// get만 해오자
	@GetMapping("/update")
	public void updateGET(String mmbId) {
		logger.info("updateGET() call");
		MemberVO vo = memberService.read(mmbId);		
	}//end updateGet()
	
	// 진짜 기능 ㄱ
	@PostMapping("update")
	public void updatePOST(MemberVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString()); 
		int result = memberService.update(vo);		
	}//end updatePost()
	
	// 기능만 구현, 탈퇴하면 손모가지.
	@PostMapping("/delete")
	public void deletePOST(String mmbId) {
		logger.info("deletePOST() call : mmbId = " + mmbId);
		int result = memberService.delete(mmbId);		
	}//end deletePost()
		
	@PostMapping("/login")
	public void loginPOST(String mmbId, String mmbPassword) {
		logger.info("loginPOST() call : mmbId = " + mmbId + ", mmbPassword = " + mmbPassword);
		MemberVO vo = memberService.login(mmbId, mmbPassword);
	}//end loginPost()
	
}
