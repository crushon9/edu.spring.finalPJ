package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.ScheduleVO;

public interface ScheduleDAO {

	int insert(ScheduleVO vo);

	ScheduleVO select(int scdId);

	List<ScheduleVO> selectAdmin(int mvId, int brcId, String scdDate);

	List<ScheduleVO> selectUser(int mvId, int brcId, String scdDate, String isToday, String today, int now);

	int delete(int scdId);

	int updateScdSeatBookedCnt(int amount, int scdId);

}
