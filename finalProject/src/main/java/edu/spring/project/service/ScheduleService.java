package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ScheduleVO;

// CRUD (Create, Read, Update, Delete)
// ScheduleÀº Update ¾øÀ½
public interface ScheduleService {

	int create(ScheduleVO vo);

	ScheduleVO read(int scdId);

	List<ScheduleVO> read(int mvId, int brcId, String scdDate);

	int delete(int scdId);

}
