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
@RequestMapping(value = "/schedule/admin") // url: /project/schedule
public class ScheduleAdminController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleAdminController.class);

	@Autowired
	private ScheduleService scheduleService;

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}//end registerGET()

	@PostMapping("/register")
	public ResponseEntity<Integer> registerREST(@RequestBody ScheduleVO vo) {
		// @RequestBody : json �����͸� �ڹٰ�ü�� ��ȯ
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// - �����Ϳ� HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		int result = 0;
		try {
			result = scheduleService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}//end registerREST()
	
	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() ȣ��");
	}//end listGET()

	@DeleteMapping("/delete/{scdId}")
	public ResponseEntity<Integer> deleteREST(@PathVariable("scdId") int scdId) {
		logger.info("deleteREST() ȣ�� : scdId = " + scdId);
		int result = 0;
		try {
			result = scheduleService.delete(scdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}//end deleteREST()
}
