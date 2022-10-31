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

import edu.spring.project.domain.BranchVO;
import edu.spring.project.service.BranchService;

@Controller
@RequestMapping(value = "/admin/branch") // url: /project/admin/branch
public class BranchController {
	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;
			
	@GetMapping("/list") 
	public void list(Model model) {
		logger.info("list() call");
		
		List<BranchVO> list = branchService.read();
		model.addAttribute("list", list);
		
		for(BranchVO vo : list) {
			logger.info(vo.toString());
		}		
	}//end list 
	
	@GetMapping("/areaList") 
	public void areaList(Model model) {
		logger.info("areaList()");
		
		List<BranchVO> list = branchService.read();
		model.addAttribute("list", list);
		
		for(BranchVO vo : list) {
			logger.info(vo.toString());
		}	
		
	}
	
	
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() 호출");
	}//end registerGet()
	// brcTheaterSeats는 배열 S 붙여라 알긋나?
	
	@PostMapping("/register") // redirectAttributes ㄱㄱ
	public void registerPost(BranchVO vo) {
		// RedirectAttributes		
		logger.info("registerPost() call");
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
	}//end registerPost()
	
	@GetMapping("/detail") //branchSer read
	public void detail(Model model, int brcId) {
		logger.info("detail call : mvId = " + brcId);
		BranchVO vo = branchService.read(brcId);
		model.addAttribute("vo", vo);				
	}//end detailGet
	
	@GetMapping("/update") // branchSer read
	public void updateGET(Model model, int brcId) {
		logger.info("updateGET() call : brcId = " + brcId);
		BranchVO vo = branchService.read(brcId);
		// page로 전송한다
		model.addAttribute("vo", vo);				
	}//end updateGET
	
	@PostMapping("/update") // branchSer update
	public void updatePOST(BranchVO vo, int brcId) {
		logger.info("updatePOST call : vo = " + vo.toString());
		int result = branchService.update(vo);
//		if(result == 1) {
//			// list + ?page=
//			return "redirect:/board/list?page=" + page;
//			// else 부분 return 빠지면 오류 쫘르르를
//		} else {
//			return "redirect:/board/update?boardOd=" + vo.getBoardId();		
//		}				
	}//end updatePOST
	
	@PostMapping("/delete") // branchSer delete
	public void delete(int brcId) {
		logger.info("delete call : brcId = " + brcId);
		int result = branchService.delete(brcId);
//		if(result == 1) {			
//			return "redirect:/board/list";
//		} else {
//			return "redirect:/board/list";
//		}		
	}//end delete
			
}
