package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.service.ScheduleService;

@Controller
@RequestMapping(value = "/schedule/admin") // url: /project/schedule/admin
public class ScheduleAdminController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleAdminController.class);

	@Autowired
	private ScheduleService scheduleService;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() call");
	}// end registerGET()

	@PostMapping("/register")
	public ResponseEntity<Integer> registerREST(@RequestBody ScheduleVO vo) {
		logger.info("registerREST() call : vo = " + vo.toString());
		int result = scheduleService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end registerREST()

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() call");
	}// end listGET()

	@DeleteMapping("/delete/{scdId}")
	public ResponseEntity<Integer> deleteREST(@PathVariable("scdId") int scdId) {
		logger.info("deleteREST() call : scdId = " + scdId);
		int result = scheduleService.delete(scdId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end deleteREST()
}
