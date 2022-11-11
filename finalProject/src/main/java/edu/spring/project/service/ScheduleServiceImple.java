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
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public ScheduleVO read(int scdId) {
		logger.info("read() ȣ�� : scdId = " + scdId);
		return dao.select(scdId);
	}

	@Override
	public List<ScheduleVO> read(int mvId, int brcId, String scdDate) {
		logger.info("read() ȣ�� : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		return dao.select(mvId, brcId, scdDate);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() ȣ�� : scdId = " + scdId);
		return dao.delete(scdId);
	}
}
