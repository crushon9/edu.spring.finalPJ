package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ScheduleVO;

//CRUD (Create, Read, Update, Delete)
public interface ScheduleService {

	int create(ScheduleVO vo);

	List<ScheduleVO> read();

	ScheduleVO read(String scdId);

	int update(ScheduleVO vo);

	int delete(String scdId);

}
