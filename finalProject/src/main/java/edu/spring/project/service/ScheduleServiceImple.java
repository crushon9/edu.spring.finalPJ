package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.persistence.ScheduleDAO;

@Service
public class ScheduleServiceImple implements ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImple.class);
	@Autowired
	private ScheduleDAO dao;

	@Override
	public int create(ScheduleVO vo) {
		logger.info("create() call");
		return dao.insert(vo);
	}

	@Override
	public ScheduleVO read(int scdId) {
		logger.info("read() call : scdId = " + scdId);
		return dao.select(scdId);
	}

	@Override
	public List<ScheduleVO> read(int mvId, int brcId, String scdDate) {
		logger.info("read() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		return dao.select(mvId, brcId, scdDate);
	}

	@Override
	public int delete(ScheduleVO vo) {
		logger.info("delete() call : scdId = " + vo.getScdId());
		if (vo.getScdSeatBookedCnt() != 0) {
			// 예매된 좌석이 있으면 -2 반환
			return -2;
		}
		return dao.delete(vo.getScdId());
	}
}
