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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		logger.info("buyGET() call");
		logger.info(vo.toString());
		model.addAttribute("vo", vo);
	}//end buyGET()

	@PostMapping("/buy")
	public String buyPOST(TicketVO vo, RedirectAttributes reAttr) {
		logger.info("buyPOST() call");
		logger.info(vo.toString());
		int result = ticketService.create(vo);
		logger.info(result + "insert");
		if (result == 1) {
			reAttr.addFlashAttribute("alertMessage", "ticketBuySuccess");
			return "redirect:/movie/main";
		} else {
			reAttr.addFlashAttribute("alertMessage", "ticketBuyFail");
			return "redirect:/schedule/list";
		}
	}//end buyPOST()

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() call");
	}//end buyPOST()

	@GetMapping("/listAll")
	public ResponseEntity<List<TicketVO>> listAllREST() {
		logger.info("listAll() call");
		List<TicketVO> list = ticketService.read();
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}//end listAllREST()

	@GetMapping("/listScdId/{scdId}")
	public ResponseEntity<List<TicketVO>> listScdIdREST(@PathVariable("scdId") int scdId) {
		logger.info("listScdIdREST() call : scdId = " + scdId);
		List<TicketVO> list = ticketService.read(scdId);
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}//end listScdIdREST()

	@GetMapping("/listMmbId/{mmbId}")
	public ResponseEntity<List<TicketVO>> listMmbIdREST(@PathVariable("mmbId") String mmbId) {
		logger.info("listMmbIdREST() call : mmbId = " + mmbId);
		List<TicketVO> list = ticketService.read(mmbId);
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}//end listMmbIdREST()
	
	@GetMapping("/list/{searchText}")
	public ResponseEntity<List<TicketVO>> listGET(@PathVariable("searchText") String searchText) {
		logger.info("listGET() call");
		// search by String
		List<TicketVO> list = ticketService.readSearch(searchText);
		return new ResponseEntity<List<TicketVO>>(list, HttpStatus.OK);
	}// end listGET()

	@DeleteMapping
	public ResponseEntity<Integer> deleteREST(@RequestBody TicketVO vo) {
		logger.info("deleteREST() call " + vo.toString());
		int result = ticketService.delete(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}//end deleteREST()

}
