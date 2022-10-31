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

import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

@Controller
@RequestMapping(value = "/admin/movie") // url: /project/admin/movie
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/list")
	public void list(Model model) {
		logger.info("list call");
		List<MovieVO> list = movieService.read();
		model.addAttribute("list", list);
		
		for(MovieVO vo : list) {			
			logger.info(vo.toString());
		}						
	}//end list()
		
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}//end registerGet()

	@PostMapping("/register")
	public String registerPOST(MovieVO vo) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = movieService.create(vo);
		logger.info(result + "�� ����");		
		return "��ȣ2";
	}//end registerPost()
	
	@GetMapping("/detail") // String mvId�� ���� EU
	public void detail(Model model, int mvId) {
		logger.info("detail call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}//end detail
	
	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		// page�� �����Ѵ�
		model.addAttribute("vo", vo);				
	}//end updateGET
		
	@PostMapping("/update") // void ���� String���� �ٲ�
	public void updatePOST(MovieVO vo) {
		logger.info("updatePOST call : vo = " + vo.toString());
		int result = movieService.update(vo);
//		if(result == 1) {
//			// list + ?page=
//			return "redirect:/board/list?page=" + page;
//			// else �κ� return ������ ���� �Ҹ�����
//		} else {
//			return "redirect:/board/update?boardOd=" + vo.getBoardId();		
//		}				
	}//end updatePOST
	
	@PostMapping("/delete")
	public void delete(int mvId) {
		logger.info("delete call : boardId = " + mvId);
		int result = movieService.delete(mvId);
//		if(result == 1) {			
//			return "redirect:/board/list";
//		} else {
//			return "redirect:/board/list";
//		}
		
	}//end delete
	
	
	
}
