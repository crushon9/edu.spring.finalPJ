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

	// �ƴ� register.jsp ���� POST�� �����ϸ� �ڵ����� �Ķ���Ͱ��� vo�� ��ܼ� ������� ���°���? ����
	@PostMapping("/register")
	public void registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "�� ����");		
	}//end registerPost()
	
	// ��� ���� ��������.
	@GetMapping("/detail")
	public void detailGET(String mmbId) {
		logger.info("detailGET() call : memberId = " + mmbId);
		MemberVO vo = memberService.read(mmbId);
	}//end detailGet()
	
	// get�� �ؿ���
	@GetMapping("/update")
	public void updateGET(String mmbId) {
		logger.info("updateGET() call");
		MemberVO vo = memberService.read(mmbId);		
	}//end updateGet()
	
	// ��¥ ��� ��
	@PostMapping("update")
	public void updatePOST(MemberVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString()); 
		int result = memberService.update(vo);		
	}//end updatePost()
	
	// ��ɸ� ����, Ż���ϸ� �ո���.
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
