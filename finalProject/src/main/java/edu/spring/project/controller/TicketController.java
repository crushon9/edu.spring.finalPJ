package edu.spring.project.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.TicketVO;
import edu.spring.project.service.TicketService;

@Controller
@RequestMapping(value = "/ticket") // url: /project/ticket
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@GetMapping("/buy")
	public void buyGET(Model model, TicketVO vo) {
		logger.info("buyGET() 호출");
		logger.info(vo.toString());
		model.addAttribute("vo", vo);
	}

	@PostMapping("/buy")
	public String buyPOST(TicketVO vo) {
		logger.info("buyPOST() 호출");
		logger.info(vo.toString());
		int result = ticketService.create(vo);
		logger.info(result + "행 삽입");
		if (result == 1) {
			return "redirect:/movie/main";
		} else {
			return "redirect:/schedule/list";
		}
	}

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() 호출");
	}

	@GetMapping("/listScdId/{scdId}")
	public ResponseEntity<List<TicketVO>> listScdIdREST(@PathVariable("scdId") int scdId) {
		logger.info("listScdIdREST() 호출 : scdId = " + scdId);
		List<TicketVO> list = ticketService.read(scdId);
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/listMmbId/{mmbId}")
	public ResponseEntity<List<TicketVO>> listMmbIdREST(@PathVariable("mmbId") String mmbId) {
		logger.info("listMmbIdREST() 호출 : mmbId = " + mmbId);
		List<TicketVO> list = ticketService.read(mmbId);
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Integer> deleteREST(@RequestBody TicketVO vo) {
		logger.info("deleteREST() 호출 " + vo.toString());
		int result = ticketService.delete(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// ------------------------오빠 admin 나눠줘

	@GetMapping("/admin/list")
	public void listGET(Model model) {
		logger.info("listGET() 호출");
	}
}
