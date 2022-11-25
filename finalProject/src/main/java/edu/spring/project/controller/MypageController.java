package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.spring.project.service.MemberService;

// ���ų��� ��ȸ, �ۼ��ı⺸��, ���̵���ã��(api���� ����)
// ��� ��ȸ, ����, ����
@Controller
@RequestMapping(value = "/member/mypage") // url: /project/member/mypage
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	private MemberService memberService;

	@GetMapping("/main") // http://localhost:8080/project/member/mypage/main
	public void mainGET() {
		logger.info("mainGET() call");
	}

}
