package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.ScheduleVO;

public interface ScheduleDAO {

	int insert(ScheduleVO vo);

	ScheduleVO select(int scdId);

	List<ScheduleVO> select(int mvId, int brcId, String scdDate);
	
	List<ScheduleVO> select(int mvId, int brcId, String scdDate, int scdTime);

	int delete(int scdId);

	int updateScdSeatBookedCnt(int amount, int scdId);

}
