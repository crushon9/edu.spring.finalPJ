package edu.spring.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.project.domain.MemberVO;
import edu.spring.project.service.MemberService;

@Controller
@RequestMapping(value = "/member") // url: /project/member
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// ���(create), �α���, �α׾ƿ�, ���̵� ã��?
	@Autowired
	private MemberService memberService;

	// ȸ������, home�� ����
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGet()

	// �ƴ� register.jsp ���� POST�� �����ϸ� �ڵ����� �Ķ���Ͱ��� vo�� ��ܼ� ������� ���°���? ����
	@PostMapping("/register")
	public String registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "�� ����");

		if (result == 1) {
			return "redirect:/member/login";
		} else {
			return "redirect:/member/register";
		}
	}// end registerPost()

	@PostMapping("/idCheck")
	public ResponseEntity<Integer> idCheckREST(@RequestBody String mmbId) {
		// @RequestBody : json �����͸� �ڹٰ�ü�� ��ȯ
		logger.info("idCheckREST() call : mmbId = " + mmbId);
		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - �����Ϳ� HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		MemberVO vo = memberService.read(mmbId);
		int result = 0;
		if (vo == null) {
			result = 1;
		} else {
			result = 0;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}//end idCheck

	@GetMapping(value = "/login")
	public void loginGET() {
		logger.info("loginGET ȣ��");
	}//end loginGET

	// �α���
	@PostMapping("/login")
	public String loginPOST(String mmbId, String mmbPassword, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes reAttr) throws IOException {
		logger.info("loginPOST call");

		MemberVO vo = memberService.login(mmbId, mmbPassword);

		if (vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mmbId", vo.getMmbId());
			return "redirect:/movie/main";

//			// ���ǿ��� targetURL ��������, ���߿� Ÿ�����˿� �ʿ��� ��
//			String targetURL = (String) session.getAttribute("targetURL");
//			logger.info("targetURL : " + targetURL);
//
//			if (targetURL != null) {
//				session.removeAttribute("targetURL");
//				return "redirect:" + targetURL;
//			} else {
//				return "redirect:/project/movie/main";
//			}
			
			// �α��� ����
		} else {
			logger.info("login failed");
			return "redirect:/member/login";
		}
	}// end loginPOST

	// �α׾ƿ�
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout call");
		HttpSession session = request.getSession();

		if (session.getAttribute("MemberVO") != null) {
			session.removeAttribute("memberId");
		}
		return "redirect:/project/movie/main";
	}// end logout

	// mmbId confirm ���̵�Ȯ��
	@PostMapping(value = "/confirmMmbId")
	public ResponseEntity<Integer> confirmMmbId(@RequestBody MemberVO vo) {
		logger.info("confirmMmbId ȣ�� : mmbId = " + vo.getMmbId());

		ArrayList<String> mmbIdList = new ArrayList<String>();
		int result = 0;
		String mmbId = vo.getMmbId();
		List<MemberVO> memberVOList = memberService.read();
		for (MemberVO memberVO : memberVOList) {
			mmbIdList.add(memberVO.getMmbId());
		}

		if (mmbIdList.contains(mmbId)) {
			result = 1;
		} else {
			result = 0;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end confirmMmbId

}
