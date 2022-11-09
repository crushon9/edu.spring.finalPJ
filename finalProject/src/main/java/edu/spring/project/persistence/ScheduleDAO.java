package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.ScheduleVO;

public interface ScheduleDAO {

	int insert(ScheduleVO vo);

	ScheduleVO select(int scdId);

	List<ScheduleVO> selectAll();

	List<ScheduleVO> selectM(int mvId);

	List<ScheduleVO> selectB(int brcId);

	List<ScheduleVO> selectD(String scdDate);

	List<ScheduleVO> selectMB(int mvId, int brcId);

	List<ScheduleVO> selectMD(int mvId, String scdDate);

	List<ScheduleVO> selectBD(int brcId, String scdDate);

	List<ScheduleVO> selectMBD(int mvId, int brcId, String scdDate);

}
