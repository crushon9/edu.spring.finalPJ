package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ticket/admin") // url: /project/ticket/admin
public class TicketAdminController {

	private static final Logger logger = LoggerFactory.getLogger(TicketAdminController.class);

	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("listGET() call");
	}//end listGET()
}
