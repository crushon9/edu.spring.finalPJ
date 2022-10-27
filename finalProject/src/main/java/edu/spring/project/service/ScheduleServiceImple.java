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
	public List<ScheduleVO> read() {
		logger.info("read() ȣ��");
		return dao.select();
	}

	@Override
	public ScheduleVO read(String scdId) {
		logger.info("read() ȣ�� : scdId = " + scdId);
		return dao.select(scdId);
	}

	@Override
	public int update(ScheduleVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(String scdId) {
		logger.info("delete() ȣ�� : scdId = " + scdId);
		return dao.delete(scdId);
	}

}
