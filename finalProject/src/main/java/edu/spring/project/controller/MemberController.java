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
		logger.info("registerGET() ȣ��");
	}//end registerGet

	// �ƴ� register.jsp ���� POST�� �����ϸ� �ڵ����� �Ķ���Ͱ��� vo�� ��ܼ� ������� ���°���? ����
	@PostMapping("/register")
	public String registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "�� ����");		
		return "��ȣ";
	}//end registerPost()
	
	// ��� ���� ��������.
	@GetMapping("/detail")
	public void detail(String mmbId) {
		logger.info("detail call : memberId = " + mmbId);
		MemberVO vo = memberService.read(mmbId);
	}//end detail
	
	// get�� �ؿ���
	@GetMapping("/update")
	public void updateGet(String mmbId) {
		logger.info("updateGet() call");
		MemberVO vo = memberService.read(mmbId);		
	}//end updateGet()
	
	// ��¥ ��� ��
	@PostMapping("update")
	public void updatePost(MemberVO vo) {
		logger.info("updatePost call : vo = " + vo.toString()); 
		int result = memberService.update(vo);		
	}//end updatePost()
	
	// ��ɸ� ����, Ż���ϸ� �ո���.
	@PostMapping("/delete")
	public void delete(String mmbId) {
		logger.info("delete call : memberId = " + mmbId);
		int result = memberService.delete(mmbId);		
	}//end delete()
		
	@PostMapping("/login")
	public void login(String mmbId, String mmbPassword) {
		logger.info("login call : mmbId = " + mmbId + ", mmbPassword = " + mmbPassword);
		MemberVO vo = memberService.login(mmbId, mmbPassword);
	}//end login()
	
}
