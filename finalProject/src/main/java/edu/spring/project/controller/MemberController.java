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

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGET()

	@PostMapping("/register")
	public String registerPOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		if (result == 1) {
			logger.info(result + " data added");
			reAttr.addFlashAttribute("alertMemberRegister", "memberRegisterSuccess");
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("alertMemberRegister", "memberRegisterFail");
			return "redirect:/member/register";
		}
	}// end registerPOST()

	// update page call
	@GetMapping("/update")
	public void updateGET(Model model, String mmbId) {
		logger.info("updateGET() call");
		MemberVO vo = memberService.readOne(mmbId);
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update")
	public String updatePOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = memberService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("alertMemberUpdate", "memberUpdateSuccess");
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("alertMemberUpdate", "memberUpdateFail");
			return "redirect:/member/mypage/update?mmbId=" + vo.getMmbId();
		}
	}// end updatePOST()

	// resign_confirm page call
	@GetMapping("/resign_confirm")
	public void resignConfirmGET() {
		logger.info("resignConfirmGET() call");
	}// end resignConfirmGET()

	@PostMapping("/resign_confirm")
	public String resignConfirmPOST(String mmbId, String mmbPassword, RedirectAttributes reAttr) {
		logger.info("resignConfirmPOST call");
		MemberVO vo = memberService.login(mmbId, mmbPassword);
		// vo != null : 아이디 패스워드가 일치한 경우
		if (vo != null) {
			return "redirect:/member/resign";
		} else {
			return "redirect:/member/resign_confirm";
		}
	}// end loginPOST()

	// resign page call
	@GetMapping("/resign")
	public void resignGET() {
		logger.info("resignGET() call");
	}// end resignGET()

	@PostMapping("/resign")
	public String resignPOST(String mmbId, RedirectAttributes reAttr, HttpServletRequest request) throws IOException {
		logger.info("resignPOST() call : mmbId = " + mmbId);
		int result = memberService.resign(mmbId);
		if (result == 1) {
			HttpSession session = request.getSession();
			session.removeAttribute("mmbIdSession");
			reAttr.addFlashAttribute("alertMemberResign", "memberResignSuccess");
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("alertMemberResign", "memberResignFail");
			return "redirect:/member/resign_confirm";
		}
	}// end resignPOST()

	@PostMapping("/idCheck")
	public ResponseEntity<Integer> idCheckREST(@RequestBody String mmbId) {
		logger.info("idCheckREST() call : mmbId = " + mmbId);
		String idCheck = memberService.idCheck(mmbId);
		// 가입불가 : -1, 기본값으로 설정
		int result = -1;
		// vo에 데이터가 비어있다면, 가입가능 : 1
		if (idCheck == null) {
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end idCheckREST()

	@GetMapping("/login")
	public void loginGET(Model model, String alertSession) {
		logger.info("loginGET call : alertSession=" + alertSession);
		model.addAttribute("alertSession", alertSession);
	}// end loginGET()

	@PostMapping("/login")
	public String loginPOST(String mmbId, String mmbPassword, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes reAttr, Model model) throws IOException {
		logger.info("loginPOST call");
		MemberVO vo = memberService.login(mmbId, mmbPassword);
		HttpSession session = request.getSession();
		// vo != null : 아이디 패스워드에 일치하는 데이터가 있는 경우, 즉 로그인 성공
		if (vo != null) {
			logger.info("login 성공");
			// mbAdminCheck가 "1"인 admin 계정 adminSession 생성
			if (vo.getMmbAdminCheck() == 1) {
				logger.info("admin login success");
				session.setAttribute("adminSession", vo.getMmbId());
			}
			// mbAdminCheck가 "0"인 일반 user계정 mmbIdSession 생성
			session.setAttribute("mmbIdSession", vo.getMmbId());
			logger.info(mmbId + " user login");
			session.setMaxInactiveInterval(600);
			return "redirect:/movie/main";
		} else {
			logger.info("login failed");
			reAttr.addFlashAttribute("alertMemberLogin", "memberLoginFail");
			return "redirect:/member/login";
		}
	}// end loginPOST()

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout call");
		HttpSession session = request.getSession();
		// adminSession or mmbIdSession 제거
		session.removeAttribute("mmbIdSession");
		session.removeAttribute("adminSession");
		return "redirect:/movie/main";
	}// end logout()

}
