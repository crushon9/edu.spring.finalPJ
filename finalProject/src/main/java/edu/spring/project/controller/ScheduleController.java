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
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.service.ScheduleService;

@Controller
@RequestMapping(value = "/schedule") // url: /project/schedule
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService scheduleService;

	@GetMapping("/list")
	public void listGET(Model model, int mvId, int brcId, String scdDate) {
		logger.info("listGET() call");
		model.addAttribute("mvId", mvId);
		model.addAttribute("brcId", brcId);
		model.addAttribute("scdDate", scdDate);
	}// end listGET()

	@GetMapping("/list/{mvId}&{brcId}&{scdDate}")
	public ResponseEntity<List<ScheduleVO>> listREST(@PathVariable("mvId") int mvId, @PathVariable("brcId") int brcId,
			@PathVariable("scdDate") String scdDate) {
		logger.info("listREST() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readUser(mvId, brcId, scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}// end listRESRT()

}
