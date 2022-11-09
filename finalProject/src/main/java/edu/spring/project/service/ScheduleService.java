package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ScheduleVO;

// CRUD (Create, Read, Update, Delete)
// ScheduleÀº Update ¾øÀ½
public interface ScheduleService {

	int create(ScheduleVO vo);

	ScheduleVO read(int scdId);

	List<ScheduleVO> readAll();

	List<ScheduleVO> readM(int mvId);

	List<ScheduleVO> readB(int brcId);

	List<ScheduleVO> readD(String scdDate);

	List<ScheduleVO> readMB(int mvId, int brcId);

	List<ScheduleVO> readMD(int mvId, String scdDate);

	List<ScheduleVO> readBD(int brcId, String scdDate);

	List<ScheduleVO> readMBD(int mvId, int brcId, String scdDate);

	int delete(int scdId);

}
