package edu.spring.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.TicketVO;
import edu.spring.project.persistence.ScheduleDAO;
import edu.spring.project.persistence.TicketDAO;

@Service
public class TicketServiceImple implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImple.class);

	@Autowired
	private TicketDAO tkDao;
	@Autowired
	private ScheduleDAO scdDao;

	@Override
	@Transactional
	public int create(TicketVO vo) {
		logger.info("create() ȣ��");
		// Ƽ���� ���ŵǸ�(insert) scdSeatBookedCnt�� �����ο���ŭ ����(update)
		tkDao.insert(vo);
		logger.info("ticket insert ����");
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scdDao.updateScdSeatBookedCnt(bookedTotal, vo.getScdId());
		logger.info("scdSeatBookedCnt update ����");
		return 1;
	}

	@Override
	public List<TicketVO> readScdId(int scdId) {
		logger.info("readScdId() ȣ�� : scdId = " + scdId);
		return tkDao.selectScdId(scdId);
	}
}
