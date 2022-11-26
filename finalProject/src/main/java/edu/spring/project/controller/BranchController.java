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
	
	// user main page
	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("list main page call");
		List<BranchVO> list = branchService.read();
		model.addAttribute("list", list);
	}// end listGET()

	// scd's async
	@GetMapping("/list/{brcArea}")
	public ResponseEntity<List<BranchVO>> listREST(@PathVariable("brcArea") int brcArea) {
		logger.info("listREST() call : brcArea = " + brcArea);
		List<BranchVO> list = branchService.read(brcArea);
		return new ResponseEntity<List<BranchVO>>(list, HttpStatus.OK); // �ڵ����� JSON���� �Ľ̵�
	}// end listREST()
	
	// user branch detail
	@GetMapping("/detail")
	public void detailGET(Model model, int brcId) {
		logger.info("detailGET() call : mvId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		model.addAttribute("vo", vo);
	}// end detailGET()
	
	@GetMapping("/detail/{brcId}")
	public ResponseEntity<BranchVO> detailREST(@PathVariable("brcId") int brcId) {
		logger.info("detailREST() call : brcId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		System.out.println(vo);
		return new ResponseEntity<BranchVO>(vo, HttpStatus.OK);
	}// end detailREST()
}
