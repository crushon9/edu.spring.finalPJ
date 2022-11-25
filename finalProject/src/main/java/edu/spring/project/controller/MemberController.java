package edu.spring.project.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private MemberService memberService;

	// 회원가입, home의 역할
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGet()

	@PostMapping("/register")
	public String registerPOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);

		if (result == 1) {
			logger.info(result + "행 삽입");
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			return "redirect:/member/register";
		}
	}// end registerPost()

	// update call
	@GetMapping("/update")
	public void updateGET(Model model, String mmbId) {
		logger.info("updateGET() call");
		MemberVO vo = memberService.read(mmbId);
		model.addAttribute("vo", vo);
	}// end updateGet()

	@PostMapping("/update")
	public String updatePOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = memberService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/member/mypage/main";
		} else {
			reAttr.addFlashAttribute("update_result", "fail");
			return "redirect:/member/mypage/update?mmbId=" + vo.getMmbId();
		}
	}// end updatePost()

	@PostMapping("/delete")
	public String deletePOST(String mmbId, RedirectAttributes reAttr) {
		logger.info("deletePOST() call : mmbId = " + mmbId);
		int result = memberService.delete(mmbId);
		if (result == 1) {
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("delete_result", "fail");
			return "redirect:/member/mypage/update?mmbId=" + mmbId;
		}
	}// end deletePost()

	@PostMapping("/idCheck")
	public ResponseEntity<Integer> idCheckREST(@RequestBody String mmbId) {
		// @RequestBody : json 데이터를 자바객체로 변환
		logger.info("idCheckREST() call : mmbId = " + mmbId);
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터와 HttpStatus를 전송, - <T> : 보내고자 하는 데이터 타입
		MemberVO vo = memberService.read(mmbId);
		int result = 0;
		if (vo == null) {
			result = 1;
		} else {
			result = 0;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end idCheck

	@GetMapping("/login")
	public void loginGET() {
		logger.info("loginGET 호출");
	}// end loginGET

	// 로그인
	@PostMapping("/login")
	public String loginPOST(String mmbId, String mmbPassword, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes reAttr) throws IOException {
		logger.info("loginPOST call");
		MemberVO vo = memberService.login(mmbId, mmbPassword);
		if (vo != null) {
			if (vo.getMmbId().equals("admin")) {
				return "redirect:/admin/main";
			}
			HttpSession session = request.getSession();
			session.setAttribute("mmbIdSession", vo.getMmbId());
			session.setMaxInactiveInterval(300);
			// TODO : targetURL login 확인필요
			String targetURL = (String) session.getAttribute("targetURL");
			logger.info("targetURL : " + targetURL);
			if (targetURL != null) { // targetURL이 있다면
				session.removeAttribute("targetURL");
				return "redirect:" + targetURL;
			} else {// targetURL이 없다면
				return "redirect:/movie/main";
			}
			// 로그인 실패
		} else {
			logger.info("login failed");
			return "redirect:/member/login";
		}
	}// end loginPOST

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout call");
		HttpSession session = request.getSession();
		session.removeAttribute("mmbIdSession");
		return "redirect:/movie/main";
	}// end logout

}
