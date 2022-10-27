package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.ScheduleVO;

public interface ScheduleDAO {

	int insert(ScheduleVO vo);

	List<ScheduleVO> select();

	ScheduleVO select(String scdId);

	int update(ScheduleVO vo);

	int delete(String scdId);

}
