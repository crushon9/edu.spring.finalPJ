package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ScheduleVO;

// CRUD (Create, Read, Update, Delete)
// Schedule : Never Update
public interface ScheduleService {

	int create(ScheduleVO vo);

	ScheduleVO read(int scdId);

	List<ScheduleVO> read(int mvId, int brcId, String scdDate);
	
	List<ScheduleVO> readAdmin(int mvId, int brcId, String scdDate);

	int delete(int scdId);

}
