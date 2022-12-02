package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.ScheduleVO;

public interface ScheduleDAO {

	int insert(ScheduleVO vo);

	ScheduleVO select(int scdId);

	List<ScheduleVO> select(int mvId, int brcId, String scdDate);

	int delete(int scdId);

	int updateScdSeatBookedCnt(int amount, int scdId);

	// 데이터 변경가능여부 체크를 위한 ImmutableCheck
	int selectImmutableCheck(int scdId);

	int updateImmutableCheck(int scdId);

}
