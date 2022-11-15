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

	// 등록, 로그인, 로그아웃, 아이디 찾기?
	@Autowired
	private MemberService memberService;

	// 회원가입
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGet()

	// 아니 register.jsp 에서 POST로 전송하면 자동으로 파라미터값이 vo에 담겨서 여기까지 오는거임? ㅇㅇ
	@PostMapping("/register")
	public String registerPOST(MemberVO vo) {
		// RedirectAttributes
		// - 새로운 경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = memberService.create(vo);
		logger.info(result + "행 삽입");
		
		if (result == 1) {
			return "redirect:/member/login";
		} else {
			return "redirect:/member/register";
		}
	}// end registerPost()
	
	@PostMapping("/login")
	public String loginPOST(String mmbId, String mmbPassword, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes reAttr) throws IOException {
		logger.info("loginPOST call");
		
		List<MemberVO> list = memberService.read();
		List<String> mmbIdList = new ArrayList<String>();
		for (MemberVO vo : list) {
			mmbIdList.add(vo.getMmbId());
		}
		
		if (mmbIdList.contains(mmbId)) {
			MemberVO vo = memberService.read(mmbId);
			// DB에서 MemberVO 형태로 받아온 VO vs 사용자입력 id,pw 비교, DB에 ID&&PW가 있다면 세션
			
			if (vo.getMmbId().equals(mmbId) && vo.getMmbPassword().equals(mmbPassword)) {
				HttpSession session = request.getSession();
				session.setAttribute("MemberVO", vo);
				
				// 세션에서 targetURL 가져오기
				String targetURL = (String) session.getAttribute("targetURL");
				logger.info("targetURL : " + targetURL);
				
				if (targetURL != null) {
					session.removeAttribute("targetURL");
					return "redirect:" + targetURL;
				} else {
					return "redirect:/project/movie/main";
				}
				// 로그인 실패
			} else {
				// reAttr.addFlashAttribute("alert","LoginFailed");
				logger.info("login failed");
				return "redirect:/member/login";
			}
		} else {
			reAttr.addFlashAttribute("alert","LoginFailed");
			return "redirect:/member/login";			
		} // end contains
	}// end loginPOST
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout call");
		HttpSession session = request.getSession();
		
		if (session.getAttribute("MemberVO") != null) {
			session.removeAttribute("memberId");
		}
		return "redirect:/project/movie/main";
	}// end logout

	// mmbId confirm 아이디확인
	@PostMapping(value = "/confirmMmbId")
	public ResponseEntity<Integer> confirmMmbId(@RequestBody MemberVO vo){
		logger.info("confirmMmbId 호출 : mmbId = " + vo.getMmbId());
		
		ArrayList<String> mmbIdList = new ArrayList<String>();
		int result = 0;
		String mmbId = vo.getMmbId();
		List<MemberVO> memberVOList = memberService.read();
		for(MemberVO memberVO : memberVOList) {
			mmbIdList.add(memberVO.getMmbId());
		}
		
		if(mmbIdList.contains(mmbId)) {
			result = 1;
		} else {
			result = 0;
		}		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}//end confirmMmbId		

}
