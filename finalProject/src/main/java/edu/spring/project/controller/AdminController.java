package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin") // url: /project/admin
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	// admin main ������ ȣ��
	@GetMapping("/main")
	public void mainGET() {
		logger.info("mainGET() call");
	}// end mainGET()

}
