package edu.spring.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.TicketVO;
import edu.spring.project.service.TicketService;

@Controller
@RequestMapping(value = "/ticket/admin") // url: /project/ticket/admin
public class TicketAdminController {

	private static final Logger logger = LoggerFactory.getLogger(TicketAdminController.class);

	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/list")
	public void listGET(Model model, String searchText) {
		logger.info("listGET() call");
		// search by String
		if (searchText != null) {
			List<TicketVO> list = ticketService.readSearch(searchText);
			model.addAttribute("list", list);
		} else {
			List<TicketVO> list = ticketService.read();
			model.addAttribute("list", list);
		}
		
	}//end listGET()
}
