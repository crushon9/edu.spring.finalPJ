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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/branch") // url: /project/branch
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;

	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("listGET() call");
		List<BranchVO> list = branchService.read();
		model.addAttribute("list", list);
	}// end list

	@GetMapping("/areaList/{brcArea}")
	public ResponseEntity<List<BranchVO>> areaListREST(@PathVariable("brcArea") int brcArea) {
		logger.info("areaListREST() call : brcArea = " + brcArea);
		List<BranchVO> list = branchService.areaList(brcArea);
		return new ResponseEntity<List<BranchVO>>(list, HttpStatus.OK); // 자동으로 JSON으로 파싱됨
	}

	@GetMapping("/admin/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGet()
		// brcTheaterSeats는 배열 S 붙여라 알긋나?

	@PostMapping("/admin/register") // redirectAttributes ㄱㄱ
	public void registerPOST(BranchVO vo) {
		// RedirectAttributes
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = branchService.create(vo);
		logger.info(result + "행 삽입");
//		if(result == 1) {
//			// sending key value 
//			reAttr.addFlashAttribute("insert_result", "success");
//			return "redirect:/board/list"; // 
//		} else {
//			reAttr.addFlashAttribute("insert_result", "fail");
//			return "redirect:/board/register"; // 
//		}		
	}// end registerPost()

	@GetMapping("/detail") // branchSer read
	public void detailGET(Model model, int brcId) {
		logger.info("detailGET() call : mvId = " + brcId);
		BranchVO vo = branchService.read(brcId);
		model.addAttribute("vo", vo);
	}// end detailGet

	@GetMapping("/detail/{brcId}")
	public ResponseEntity<BranchVO> detailREST(@PathVariable("brcId") int brcId) {
		logger.info("detailREST() call : brcId = " + brcId);
		BranchVO vo = branchService.read(brcId);
		System.out.println(vo);
		return new ResponseEntity<BranchVO>(vo, HttpStatus.OK);
	}

	@GetMapping("/update") // branchSer read
	public void updateGET(Model model, int brcId) {
		logger.info("updateGET() call : brcId = " + brcId);
		BranchVO vo = branchService.read(brcId);
		// page로 전송한다
		model.addAttribute("vo", vo);
	}// end updateGET

	@PostMapping("/update") // branchSer update
	public void updatePOST(BranchVO vo, int brcId) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = branchService.update(vo);
//		if(result == 1) {
//			// list + ?page=
//			return "redirect:/board/list?page=" + page;
//			// else 부분 return 빠지면 오류 쫘르르를
//		} else {
//			return "redirect:/board/update?boardOd=" + vo.getBoardId();		
//		}				
	}// end updatePOST

	@PostMapping("/delete") // branchSer delete
	public void deletePOST(int brcId) {
		logger.info("deletePOST() call : brcId = " + brcId);
		int result = branchService.delete(brcId);
//		if(result == 1) {			
//			return "redirect:/board/list";
//		} else {
//			return "redirect:/board/list";
//		}		
	}// end delete

}
