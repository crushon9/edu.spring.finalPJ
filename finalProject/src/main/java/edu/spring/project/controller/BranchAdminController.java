package edu.spring.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/branch/admin") // url: /project/branch/admin
public class BranchAdminController {
	private static final Logger logger = LoggerFactory.getLogger(BranchAdminController.class);

	@Autowired
	private BranchService branchService;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGET()

	@PostMapping("/register")
	public String registerPOST(BranchVO vo, RedirectAttributes reAttr) {
		logger.info("registerPOST() call");
		logger.info(vo.toString());
		int result = branchService.create(vo);
		if (result == 1) {
			// success -> list.jsp
			logger.info(result + " line registered");
			return "redirect:/branch/admin/list";
		} else {
			reAttr.addFlashAttribute("branchRegisterResult", "fail");
			return "redirect:/branch/admin/register";
		}
	}// end registerPOST()

	// main page
	@GetMapping("/list")
	public void listGET(Model model, Integer brcArea, String searchBrcName) {
		logger.info("list main page call");
		List<BranchVO> list;
		if (brcArea != null) {
			list = branchService.readBrcArea(brcArea);
		} else if (searchBrcName != null) {
			list = branchService.readBrcName(searchBrcName);
		} else {
			list = branchService.read();
		}
		model.addAttribute("list", list);
	}// end listGET()

	@GetMapping("/update")
	public void updateGET(Model model, int brcId) {
		logger.info("updateGET() call : brcId = " + brcId);
		BranchVO vo = branchService.readOne(brcId);
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update")
	public String updatePOST(BranchVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = branchService.update(vo);
		if (result == 1) {
			return "redirect:/branch/admin/list";
		} else {
			return "redirect:/branch/admin/update?brcId=" + vo.getBrcId();
		}
	}// end updatePOST()

	@PostMapping("/delete")
	public String deletePOST(int brcId) {
		logger.info("deletePOST() call : brcId = " + brcId);
		int result = branchService.delete(brcId);
		if (result == 1) {
			return "redirect:/branch/admin/list";
		} else {
			return "redirect:/branch/admin/update?brcId=" + brcId;
		}
	}// end deletePOST()

}
