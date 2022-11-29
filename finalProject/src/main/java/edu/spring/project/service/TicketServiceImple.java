package edu.spring.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.TicketVO;
import edu.spring.project.persistence.MovieDAO;
import edu.spring.project.persistence.ScheduleDAO;
import edu.spring.project.persistence.TicketDAO;

@Service
public class TicketServiceImple implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImple.class);

	@Autowired
	private TicketDAO tkDao;
	@Autowired
	private ScheduleDAO scdDao;
	@Autowired
	private MovieDAO mvDao;

	@Override
	@Transactional
	public int create(TicketVO vo) {
		logger.info("create() call");
		// insert -> + 1
		tkDao.insert(vo);
		logger.info("ticket insert success");
		// scdSeatBookedCnt(update)
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scdDao.updateScdSeatBookedCnt(bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt success");
		// mvTicketSales
		mvDao.updateTicketSales(bookedTotal, vo.getMvId());
		logger.info("updateTicketSales success");
		return 1;
	}

	@Override
	public List<TicketVO> read() {
		logger.info("read() call");
		return tkDao.select();
	}

	@Override
	public List<TicketVO> read(int scdId) {
		logger.info("read() call : scdId = " + scdId);
		return tkDao.select(scdId);
	}

	@Override
	public List<TicketVO> read(String mmbId) {
		logger.info("read() call : mmbId = " + mmbId);
		return tkDao.select(mmbId);
	}

	@Transactional
	@Override
	public int delete(TicketVO vo) {
		logger.info("delete() call");
		tkDao.delete(vo.getTkId());
		logger.info("ticket delete success");
		// scdSeatBookedCnt
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scdDao.updateScdSeatBookedCnt(-bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt success");
		// mvTicketSales
		mvDao.updateTicketSales(-bookedTotal, vo.getMvId());
		logger.info("updateTicketSales success");
		return 1;
	}
}
