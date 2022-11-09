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
	public List<ScheduleVO> readAll() {
		logger.info("readAll() ȣ��");
		return dao.selectAll();
	}

	@Override
	public List<ScheduleVO> readM(int mvId) {
		logger.info("readM() ȣ�� : mvId = " + mvId);
		return dao.selectM(mvId);
	}

	@Override
	public List<ScheduleVO> readB(int brcId) {
		logger.info("readB() ȣ�� : brcId = " + brcId);
		return dao.selectB(brcId);
	}

	@Override
	public List<ScheduleVO> readD(String scdDate) {
		logger.info("readD() ȣ�� : scdDate = " + scdDate);
		return dao.selectD(scdDate);
	}

	@Override
	public List<ScheduleVO> readMB(int mvId, int brcId) {
		logger.info("readMB() ȣ�� : mvId = " + mvId + ", brcId = " + brcId);
		return dao.selectMB(mvId, brcId);
	}

	@Override
	public List<ScheduleVO> readMD(int mvId, String scdDate) {
		logger.info("readMD() ȣ�� : mvId = " + mvId + ", scdDate = " + scdDate);
		return dao.selectMD(mvId, scdDate);
	}

	@Override
	public List<ScheduleVO> readBD(int brcId, String scdDate) {
		logger.info("readBD() ȣ�� : brcId = " + brcId + ", scdDate = " + scdDate);
		return dao.selectBD(brcId, scdDate);
	}

	@Override
	public List<ScheduleVO> readMBD(int mvId, int brcId, String scdDate) {
		logger.info("readMBD() ȣ�� : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		return dao.selectMBD(mvId, brcId, scdDate);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() ȣ�� : scdId = " + scdId);
		return dao.delete(scdId);
	}
}
