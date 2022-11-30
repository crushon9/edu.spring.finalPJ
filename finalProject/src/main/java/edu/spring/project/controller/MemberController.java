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
		// if (brcId isUnselected)
		if (vo.getBrcId() == 0) {
			vo.setBrcId(1);
		}

		int result = memberService.create(vo);
		if (result == 1) {
			logger.info(result + " data added");
			reAttr.addFlashAttribute("alertMassage", "memberRegisterSuccess");
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("alertMassage", "memberRegisterFail");
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
		// if (brcId isUnselected)
		if (vo.getBrcId() == 0) {
			vo.setBrcId(1);
		}
		int result = memberService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("alertMassage", "memberUpdateSuccess");
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("alertMassage", "memberUpdateFail");
			return "redirect:/member/mypage/update?mmbId=" + vo.getMmbId();
		}
	}// end updatePOST()

	// delete page call
	@GetMapping("/delete_confirm")
	public void deleteConfirmGET() {
		logger.info("deleteConfirmGET() call");
	}// end deleteConfirmGET()

	@PostMapping("/delete_confirm")
	public String deleteConfirmPOST(String mmbId, String mmbPassword, RedirectAttributes reAttr) {
		logger.info("deleteConfirmPOST call");
		MemberVO vo = memberService.login(mmbId, mmbPassword);
		if (vo != null) {
			return "redirect:/member/delete";
		} else {
			return "redirect:/member/delete_confirm";
		}
	}// end loginPOST()

	// delete page call
	@GetMapping("/delete")
	public void deleteGET() {
		logger.info("deleteGET() call");
	}// end deleteGET()

	@PostMapping("/delete")
	public String deletePOST(String mmbId, RedirectAttributes reAttr, HttpServletRequest request) {
		logger.info("deletePOST() call : mmbId = " + mmbId);
		int result = memberService.delete(mmbId);
		if (result == 1) {
			HttpSession session = request.getSession();
			session.removeAttribute("mmbIdSession");
			reAttr.addFlashAttribute("alertMassage", "memberDeleteSuccess");
			// movie/main -> withdrawal
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("alertMassage", "memberDeleteFail");
			return "redirect:/member/delete_confirm";
		}
	}// end deletePOST()

	@PostMapping("/idCheck")
	public ResponseEntity<Integer> idCheckREST(@RequestBody String mmbId) {
		logger.info("idCheckREST() call : mmbId = " + mmbId);
		MemberVO vo = memberService.readOne(mmbId);
		int result = 0;
		if (vo == null) {
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end idCheckREST()

	@GetMapping("/login")
	public void loginGET(Model model, String alertMessage) {
		logger.info("loginGET call");
		model.addAttribute("alertMessage", alertMessage);
	}// end loginGET()

	@PostMapping("/login")
	public String loginPOST(String mmbId, String mmbPassword, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes reAttr) throws IOException {
		logger.info("loginPOST call");
		MemberVO vo = memberService.login(mmbId, mmbPassword);
		HttpSession session = request.getSession();

		if (vo != null) {
			// admin login only
			if (vo.getMmbAdminCheck() == 1) {
				logger.info("admin login success");
				session.setAttribute("adminSession", vo.getMmbId());
			}
			// user login
			session.setAttribute("mmbIdSession", vo.getMmbId());
			logger.info(mmbId + " user login");
			session.setMaxInactiveInterval(600);
			// TODO : targetURL login
			String targetURL = (String) session.getAttribute("targetURL");
			logger.info("targetURL : " + targetURL);
			if (targetURL != null) { // targetURL yes
				session.removeAttribute("targetURL");
				return "redirect:" + targetURL;
				// targetURL X -> main.jsp
			} else { // targetURL no
				// 일반 유저가 정상 로그인시 이까지 나옴...
				logger.info("targetURL NO?");
				return "redirect:/movie/main";
			}
			// login failed
		} else {
			logger.info("login failed");
			reAttr.addFlashAttribute("alertMassage", "memberLoginFail");
			return "redirect:/member/login";
		}
	}// end loginPOST()

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout call");
		HttpSession session = request.getSession();
		session.removeAttribute("mmbIdSession");
		session.removeAttribute("adminSession");
		return "redirect:/movie/main";
	}// end logout()

}
