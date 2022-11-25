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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/branch") // url: /project/branch
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;

	@GetMapping("/admin/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGet()
		// brcTheaterSeats는 배열 S 붙여라 알긋나?

	@PostMapping("/admin/register") // redirectAttributes ㄱㄱ
	public void registerPOST(BranchVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = 0;
		result = branchService.create(vo);
		if (result == 1) {
			// sending key value
			logger.info(result + "행 삽입");
			reAttr.addFlashAttribute("insert_result", "success");
			// return "redirect:/board/list";
		} else {
			reAttr.addFlashAttribute("insert_result", "fail");
			// return "redirect:/board/register";
		}
	}// end registerPost()

	// main page
	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("list main page call");
		List<BranchVO> list = branchService.read();
		model.addAttribute("list", list);
	}// end list

	// --admin--
	@GetMapping("/admin/list")
	public void listGET(Model model, String searchText, Integer brcArea) {
		logger.info("listGET() call");

		if (searchText != null) {
			List<BranchVO> list = branchService.read(searchText);
			model.addAttribute("list", list);
		} else if (brcArea != null) {
			List<BranchVO> list = branchService.read(brcArea);
			model.addAttribute("list", list);
		} else {
			List<BranchVO> list = branchService.read();
			model.addAttribute("list", list);
		}

	}// end list

	@GetMapping("/list/{brcArea}")
	public ResponseEntity<List<BranchVO>> listREST(@PathVariable("brcArea") int brcArea) {
		logger.info("listREST() call : brcArea = " + brcArea);
		List<BranchVO> list = branchService.read(brcArea);
		return new ResponseEntity<List<BranchVO>>(list, HttpStatus.OK); // 자동으로 JSON으로 파싱됨
	}// end areaListREST

	@GetMapping("/detail") // branchSer read
	public void detailGET(Model model, int brcId) {
		logger.info("detailGET() call : mvId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		model.addAttribute("vo", vo);
	}// end detailGet

	@GetMapping("/detail/{brcId}")
	public ResponseEntity<BranchVO> detailREST(@PathVariable("brcId") int brcId) {
		logger.info("detailREST() call : brcId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		System.out.println(vo);
		return new ResponseEntity<BranchVO>(vo, HttpStatus.OK);
	}

	@GetMapping("/admin/update") // branchSer read
	public void updateGET(Model model, int brcId) {
		logger.info("updateGET() call : brcId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		// page로 전송한다
		model.addAttribute("vo", vo);
	}// end updateGET

	@PostMapping("/admin/update") // branchSer update
	public String updatePOST(BranchVO vo, int brcId) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = branchService.update(vo);
		if (result == 1) {
			return "redirect:/branch/admin/list";
			// else 부분 return 빠지면 오류 쫘르르를
		} else {
			return "redirect:/branch/admin/update?brcId=" + vo.getBrcId();
		}
	}// end updatePOST

	@PostMapping("/admin/delete") // branchSer delete
	public String deletePOST(int brcId) {
		logger.info("deletePOST() call : brcId = " + brcId);
		int result = branchService.delete(brcId);
		if (result == 1) {
			return "redirect:/branch/admin/list";
		} else {
			return "redirect:/branch/admin/update?brcId=" + brcId;
		}
	}// end delete

}
