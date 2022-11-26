package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.service.TicketService;

@Controller
@RequestMapping(value = "/ticket/admin") // url: /project/ticket
public class TicketAdminController {

	private static final Logger logger = LoggerFactory.getLogger(TicketAdminController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping("/list")
	public void listGET(Model model) {
		logger.info("listGET() ȣ��");
	}//end listGET()
}
