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
import edu.spring.project.util.TimeCompareUtil;

@Service
public class TicketServiceImple implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImple.class);

	@Autowired
	private TicketDAO ticketDao;
	@Autowired
	private ScheduleDAO scheduleDao;
	@Autowired
	private MovieDAO movieDao;

	@Override
	@Transactional
	public int create(TicketVO vo) {
		logger.info("create() call");
		// insert -> + 1
		ticketDao.insert(vo);
		logger.info("ticket insert success");
		// scdSeatBookedCnt(update)
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scheduleDao.updateScdSeatBookedCnt(bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt success");
		// mvTicketSales
		movieDao.updateTicketSales(bookedTotal, vo.getMvId());
		logger.info("updateTicketSales success");
		return 1;
	}

	@Override
	public List<TicketVO> read() {
		logger.info("read() call");
		return ticketDao.select();
	}

	@Override
	public List<TicketVO> read(int scdId) {
		logger.info("read() call : scdId = " + scdId);
		return ticketDao.select(scdId);
	}

	@Override
	public List<TicketVO> read(String mmbId) {
		logger.info("read() call : mmbId = " + mmbId);
		return ticketDao.select(mmbId);
	}

	@Transactional
	@Override
	public int delete(TicketVO vo) {
		logger.info("delete() call");
		// 현재시간보다 영화 상영 시간이 after 일때 삭제
		if (TimeCompareUtil.compareToNow(vo.getScdDate(), vo.getScdTime()).equals("after")) {
			logger.info(TimeCompareUtil.compareToNow(vo.getScdDate(), vo.getScdTime()));
			ticketDao.delete(vo.getTkId());
			logger.info("ticket delete 성공");
		} else {
			return -2;
		}
		// scdSeatBookedCnt
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scheduleDao.updateScdSeatBookedCnt(-bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt success");
		// mvTicketSales
		movieDao.updateTicketSales(-bookedTotal, vo.getMvId());
		logger.info("updateTicketSales success");
		return 1;
	}

	// search by String
	@Override
	public List<TicketVO> readSearch(String search) {
		logger.info("readSearch() call : search = " + search);
		return ticketDao.selectSearch(search);
	}
}
