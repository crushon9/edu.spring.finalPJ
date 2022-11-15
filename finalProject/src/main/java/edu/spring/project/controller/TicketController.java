package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.project.domain.MovieVO;
import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.domain.TicketVO;
import edu.spring.project.service.TicketService;

@Controller
@RequestMapping(value = "/ticket") // url: /project/ticket
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping("/pay")
	public void payGET(Model model, TicketVO vo) {
		logger.info("payGET() 호출");
		logger.info(vo.toString());
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/pay")
	public void payPOST(Model model, TicketVO vo) {
		logger.info("payPOST() 호출");
	}
}
