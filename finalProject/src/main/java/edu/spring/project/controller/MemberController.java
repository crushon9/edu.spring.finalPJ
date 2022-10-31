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
		logger.info("registerGET() 호출");
	}

	// 아니 register.jsp 에서 POST로 전송하면 자동으로 파라미터값이 vo에 담겨서 여기까지 오는거임? ㅇㅇ
	@PostMapping("/register")
	public String registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "행 삽입");
		
		return "야호";
	}
	
	// 멤버 정보 보여주자.
	@GetMapping("/detail")
	public void detail(String mmbId) {
		logger.info("detail call : memberId = " + mmbId);
		MemberVO vo = memberService.read(mmbId);
	}
	
	// get만 해오자
	@GetMapping("/update")
	public void updateGet(String mmbId) {
		logger.info("updateGet() call");
		MemberVO vo = memberService.read(mmbId);
		
	}
	
	// 진짜 기능 ㄱ
	@PostMapping("update")
	public void updatePost(MemberVO vo) {
		logger.info("updatePost call : vo = " + vo.toString()); 
		int result = memberService.update(vo);		
		
	}
	
	// 기능만 구현, 탈퇴하면 손모가지.
	@PostMapping("/delete")
	public void delete(String mmbId) {
		logger.info("delete call : memberId = " + mmbId);
		int result = memberService.delete(mmbId);
		
	}
		
	
}
