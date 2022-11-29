package edu.spring.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/branch") // url: /project/branch
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;

	// main page
	@GetMapping("/list")
	public void listGET(Model model, Integer brcArea) {
		logger.info("list main page call");
		List<BranchVO> list;
		if (brcArea != null) {
			list = branchService.readBrcArea(brcArea);
		} else {
			list = branchService.read();
		}
		model.addAttribute("list", list);
	}// end listGET()

	// 셀렉트 전체 선택시 리스트 불러오기
	@GetMapping("/listREST")
	public ResponseEntity<List<BranchVO>> listREST() {
		logger.info("listREST() call");
		List<BranchVO> list = branchService.read();
		return new ResponseEntity<List<BranchVO>>(list, HttpStatus.OK);
	}// end listREST()

	// reason : scd's async
	@GetMapping("/list/{brcArea}")
	public ResponseEntity<List<BranchVO>> listBrcAreaREST(@PathVariable("brcArea") int brcArea) {
		logger.info("listBrcAreaREST() call : brcArea = " + brcArea);
		List<BranchVO> list = branchService.readBrcArea(brcArea);
		return new ResponseEntity<List<BranchVO>>(list, HttpStatus.OK);
	}// end listBrcAreaREST()

	// branch detail
	@GetMapping("/detail")
	public void detailGET(Model model, int brcId) {
		logger.info("detailGET() call : mvId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		model.addAttribute("vo", vo);
	}// end detailGET()

	// reason : scd's async
	@GetMapping("/detail/{brcId}")
	public ResponseEntity<BranchVO> detailREST(@PathVariable("brcId") int brcId) {
		logger.info("detailREST() call : brcId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		System.out.println(vo);
		return new ResponseEntity<BranchVO>(vo, HttpStatus.OK);
	}// end detailREST()
}
