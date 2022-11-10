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
		logger.info("registerGET() 호출");
	}

	@PostMapping("/admin/register")
	public ResponseEntity<Integer> registerREST(@RequestBody ScheduleVO vo) {
		// @RequestBody : json 데이터를 자바객체로 변환
		logger.info("registerREST() call : vo = " + vo.toString());
		// ResponseEntity<T> : REST 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터와 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
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
		logger.info("deleteREST() 호출 : scdId = " + scdId);
		int result = 0;
		try {
			result = scheduleService.delete(scdId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() 호출");
	}

	@GetMapping("/list?all")
	public ResponseEntity<List<ScheduleVO>> listAllREST() {
		logger.info("listAllREST() 호출");
		List<ScheduleVO> list = scheduleService.readAll();
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?mvId={mvId}")
	public ResponseEntity<List<ScheduleVO>> listMREST(@PathVariable("mvId") int mvId) {
		logger.info("listMREST() 호출 : mvId = " + mvId);
		List<ScheduleVO> list = scheduleService.readM(mvId);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?brcId={brcId}")
	public ResponseEntity<List<ScheduleVO>> listBREST(@PathVariable("brcId") int brcId) {
		logger.info("listBREST() 호출 : brcId = " + brcId);
		List<ScheduleVO> list = scheduleService.readB(brcId);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?scdDate={scdDate}")
	public ResponseEntity<List<ScheduleVO>> listDREST(@PathVariable("scdDate") String scdDate) {
		logger.info("listDREST() 호출 : scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readD(scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?mvId={mvId}&brcId={brcId}")
	public ResponseEntity<List<ScheduleVO>> listMBREST(@PathVariable("mvId") int mvId,
			@PathVariable("brcId") int brcId) {
		logger.info("listMBREST() 호출 : mvId = " + mvId + ", brcId = " + brcId);
		List<ScheduleVO> list = scheduleService.readMB(mvId, brcId);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?mvId={mvId}&scdDate={scdDate}")
	public ResponseEntity<List<ScheduleVO>> listMDREST(@PathVariable("mvId") int mvId,
			@PathVariable("scdDate") String scdDate) {
		logger.info("listMDREST() 호출 : mvId = " + mvId + ", scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readMD(mvId, scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?brcId={brcId}&scdDate={scdDate}")
	public ResponseEntity<List<ScheduleVO>> listBDREST(@PathVariable("brcId") int brcId,
			@PathVariable("scdDate") String scdDate) {
		logger.info("listBDREST() 호출 : brcId = " + brcId + ", scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readBD(brcId, scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/list?mvId={mvId}&brcId={brcId}&scdDate={scdDate}")
	public ResponseEntity<List<ScheduleVO>> listMBDREST(@PathVariable("mvId") int mvId,
			@PathVariable("brcId") int brcId, @PathVariable("scdDate") String scdDate) {
		logger.info("listMBDREST() 호출 : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		List<ScheduleVO> list = scheduleService.readMBD(mvId, brcId, scdDate);
		return new ResponseEntity<List<ScheduleVO>>(list, HttpStatus.OK);
	}
}
