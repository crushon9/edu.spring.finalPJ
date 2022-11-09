package edu.spring.project.controller;

import java.util.List;
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
@RequestMapping(value = "/schedule") // url: /project/schedule
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService scheduleService;

	@GetMapping("/admin/register")
	public void registerGET() {
		logger.info("registerGET() ȣ��");
	}

	@PostMapping("/admin/register")
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
	}

	@DeleteMapping("/admin/delete/{scdId}")
	public ResponseEntity<Integer> deleteREST(@PathVariable("scdId") int scdId) {
		logger.info("deleteREST() ȣ�� : scdId = " + scdId);
		int result = 0;
		try {
			result = scheduleService.delete(scdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/list/{brcId}/{scdDate}")
	public ResponseEntity<List<ScheduleVO>> listREST(@PathVariable("brcId") int brcId,
			@PathVariable("scdDate") String scdDate) {
		logger.info("listREST() ȣ�� : brcId = " + brcId + ", scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readBD(brcId, scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() ȣ��");
	}
}
