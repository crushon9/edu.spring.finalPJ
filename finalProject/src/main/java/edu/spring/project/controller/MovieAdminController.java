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
@RequestMapping(value = "/movie/admin") // url: /project/movie
public class MovieAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MovieAdminController.class);

	@Autowired
	private MovieService movieService;

	// ���ε�� ������
	@Resource(name = "uploadPath")
	private String uploadPath;

	// �ش� �ּ�jsp ȣ��
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}// end registerGET()

	// ������ ����
	@PostMapping("/register")
	public void registerPOST(Model model, MovieVO vo) {
		// RedirectAttributes ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("registerPOST() ȣ��");
		logger.info(vo.toString());

		int result = movieService.create(vo);
		logger.info("result = " + result);
		model.addAttribute("vo", vo);
	} // end registerPOST()

	@GetMapping("/list") // ���� ����Ʈ ���ϱ�
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

	@GetMapping("/update")
	public void updateGET(Model model, int mvId) {
		logger.info("updateGET() call : mvId = " + mvId);
		MovieVO vo = movieService.read(mvId);
		logger.info(vo.toString());
		// page�� �����Ѵ�
		model.addAttribute("vo", vo);
	}// end updateGET()

	@PostMapping("/update") // void ���� String���� �ٲ�
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
	}// end updatePOST()

	@GetMapping("/delete")
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
