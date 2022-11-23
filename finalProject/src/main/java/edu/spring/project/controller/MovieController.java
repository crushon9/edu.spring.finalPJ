package edu.spring.project.controller;

import java.util.List;
import javax.annotation.Resource;
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
import edu.spring.project.domain.MovieVO;
import edu.spring.project.service.MovieService;

@Controller
@RequestMapping(value = "/movie") // url: /project/movie
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	// ���ε�� ������
	@Resource(name = "uploadPath")
	private String uploadPath;

	// ������������ ����Ʈ ������or���� ���� ���ϱ� (+���ڿ��˻�)
	@GetMapping("/main")
	public void mainGET(Model model, String orderChoice, String searchText) {
		logger.info("mainGET() call");
		if (searchText != null) {
			List<MovieVO> mvList = movieService.readSearch(searchText);
			model.addAttribute("mvList", mvList);
		} else if (orderChoice == "ts") {
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		} else if (orderChoice == "ra") {
			List<MovieVO> mvList = movieService.readRa();
			model.addAttribute("mvList", mvList);
		} else { // �⺻��
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		}
	}// end mainGet()

	// mvId�� ��ü ���� show, -> detail.jsp
	@GetMapping("/detail")
	public void detailGET(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}// end detail()

	// �ٽú���?????
	@GetMapping("/review/register")
	public void reviewRegister(Model model, int mvId) {
		logger.info("detailGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		model.addAttribute("vo", vo);
	}// end reviewRegister

// ----------------------------------- admin ---------------------------------------
	@GetMapping("/admin/list") // ���� ����Ʈ ���ϱ�
	public void listGET(Model model, String searchText, String inputDateStarted, String inputDateEnded) {
		logger.info("listGET() call");
		if (searchText != null) {
			List<MovieVO> mvList = movieService.readSearch(searchText);
			model.addAttribute("mvList", mvList);
		} else if (inputDateStarted != null && inputDateEnded != null) {
			List<MovieVO> mvList = movieService.read(inputDateStarted, inputDateEnded);
			model.addAttribute("mvList", mvList);
		} else { // �⺻��
			List<MovieVO> mvList = movieService.readTs();
			model.addAttribute("mvList", mvList);
		}
	}// end listGET()

	// �ش� �ּ�jsp ȣ��
	@GetMapping("/admin/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}// end registerGet()

	// ������ ����
	@PostMapping("/admin/register")
	public void registerPOST(Model model, MovieVO vo) {
		// RedirectAttributes ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());

		int result = movieService.create(vo);
		logger.info("result = " + result);
		model.addAttribute("vo", vo);
	} // end registerPost()

	@GetMapping("/admin/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		logger.info(vo.toString());
		// page�� �����Ѵ�
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/admin/update") // void ���� String���� �ٲ�
	public String updatePOST(MovieVO vo) {
		logger.info("updatePOST() call : vo = " + vo.toString());
		int result = movieService.update(vo);
		if (result == 1) {
			// "list?page=" + page
			// else �κ� return ������ ���� �Ҹ�����
			return "redirect:/movie/admin/list";
		} else {
			return "redirect:/movie/admin/update?mvId=" + vo.getMvId();
		}
	}// end updatePost()

	@GetMapping("/admin/delete")
	public String deleteGET(int mvId) {
		logger.info("deleteGET() call : boardId = " + mvId);
		int result = movieService.delete(mvId);
		if (result == 1) {
			return "redirect:/movie/admin/list";
		} else {
			return "redirect:/movie/admin/update?mvId=" + mvId;
		}
	}// end deleteGET()

}
