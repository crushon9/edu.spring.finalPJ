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
		logger.info("create() 호출");
		// 티켓이 예매되면(insert)
		tkDao.insert(vo);
		logger.info("ticket insert 성공");
		// 스케줄의 scdSeatBookedCnt가 예약인원만큼 증가(update)
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scdDao.updateScdSeatBookedCnt(bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt 성공");
		// 무비의 mvTicketSales도 증가
		mvDao.updateTicketSales(bookedTotal, vo.getMvId());
		logger.info("updateTicketSales 성공");
		return 1;
	}
	
	@Override
	public List<TicketVO> read() {
		logger.info("read() 호출");
		return tkDao.select();
	}

	@Override
	public List<TicketVO> read(int scdId) {
		logger.info("read() 호출 : scdId = " + scdId);
		return tkDao.select(scdId);
	}

	@Override
	public List<TicketVO> read(String mmbId) {
		logger.info("read() 호출 : mmbId = " + mmbId);
		return tkDao.select(mmbId);
	}

	@Transactional
	@Override
	public int delete(TicketVO vo) {
		logger.info("delete() 호출");
		tkDao.delete(vo.getTkId());
		logger.info("ticket delete 성공");
		// 스케줄의 scdSeatBookedCnt가 예약인원만큼 감소
		int adult = Integer.parseInt(vo.getTkPeopleList().split("&")[0].split("=")[1]);
		int adolescent = Integer.parseInt(vo.getTkPeopleList().split("&")[1].split("=")[1]);
		int bookedTotal = adult + adolescent;
		scdDao.updateScdSeatBookedCnt(-bookedTotal, vo.getScdId());
		logger.info("updateScdSeatBookedCnt 성공");
		// 무비의 mvTicketSales도 감소
		mvDao.updateTicketSales(-bookedTotal, vo.getMvId());
		logger.info("updateTicketSales 성공");
		return 1;
	}
}
