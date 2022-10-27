package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/admin/branch") // url: /project/admin/branch
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}
	// brcTheaterSeats는 배열 S 붙여라 알긋나?
	@PostMapping("/register")
	public String registerPOST(BranchVO vo) {
		// RedirectAttributes
		// - 재경로 위치에 속성값을 전송하는 객체
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = branchService.create(vo);
		logger.info(result + "행 삽입");
		
		return "야호3";
	}
}
