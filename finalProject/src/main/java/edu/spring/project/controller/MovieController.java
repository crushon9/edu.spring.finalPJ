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
	}// end list()

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}// end registerGet()

	@PostMapping("/register")
	public void registerPOST(MovieVO vo) {
		// RedirectAttributes
		// - ���� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());
		int result = movieService.create(vo);
		logger.info(result + "�� ����");
	}// end registerPost()

	@GetMapping("/detail") // String mvId�� ���� EU
	public void detail(Model model, int mvId) {
		logger.info("detail call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}// end detail

	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		// page�� �����Ѵ�
		model.addAttribute("vo", vo);
	}// end updateGET

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
	}// end updatePOST

	@PostMapping("/delete")
	public void delete(int mvId) {
		logger.info("delete call : boardId = " + mvId);
		int result = movieService.delete(mvId);
//		if(result == 1) {			
//			return "redirect:/board/list";
//		} else {
//			return "redirect:/board/list";
//		}

	}// end delete

	@GetMapping("/list/{inputDateStarted}/{inputDateEnded}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDateStarted") String inputDateStarted,
			@PathVariable("inputDateEnded") String inputDateEnded) {
		logger.info("listREST() ȣ�� : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		List<MovieVO> list = movieService.select(inputDateStarted, inputDateEnded);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list/{inputDate}")
	public ResponseEntity<List<MovieVO>> listREST(@PathVariable("inputDate") String inputDate) {
		logger.info("listREST() ȣ�� : inputDate = " + inputDate);
		List<MovieVO> list = movieService.select(inputDate);
		return new ResponseEntity<List<MovieVO>>(list, HttpStatus.OK);
	}

}
