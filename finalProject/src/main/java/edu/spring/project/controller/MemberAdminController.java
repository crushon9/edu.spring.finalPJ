package edu.spring.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.MemberVO;
import edu.spring.project.service.MemberService;

@Controller
@RequestMapping(value = "/member/admin")
public class MemberAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MemberAdminController.class);

	@Autowired
	private MemberService memberService;

	// member list
	@GetMapping("/list")
	public void listGET(Model model, String searchMmbId) {
		logger.info("listGet call");
		if (searchMmbId != null) {
			List<MemberVO> list = memberService.read(searchMmbId);
			model.addAttribute("list", list);
		} else {
			List<MemberVO> list = memberService.read();
			model.addAttribute("list", list);
		}
	}// end listGET()

	// detail call
	@GetMapping("/detail")
	public void detailGET(Model model, String mmbId) {
		logger.info("detailGET() call");
		MemberVO vo = memberService.readOne(mmbId);
		model.addAttribute("vo", vo);
	}// end detailGET()

}
