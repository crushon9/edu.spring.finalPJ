package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import edu.spring.project.domain.MovieVO;
import edu.spring.project.persistence.MovieDAO;

@Service
public class MovieServiceImple implements MovieService {
	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImple.class);
	@Autowired
	private MovieDAO dao;

	@Override
	public int create(MovieVO vo) {
		logger.info("create() call");
		return dao.insert(vo);
	}

	@Override
	public MovieVO read(int mvId) {
		logger.info("read() call : mvId = " + mvId);
		return dao.select(mvId);
	}

	// 관리자 기본검색
	@Override
	public List<MovieVO> readAdmin() {
		logger.info("readAdmin() call");
		return dao.selectAll();
	}

	// 관리자 search by String(keyword)
	@Override
	public List<MovieVO> readAdminSearch(String search) {
		logger.info("readAdminSearch() call : search = " + search);
		return dao.selectSearch(search);
	}

	// 관리자 search by period
	@Override
	public List<MovieVO> readAdminPeriod(String inputDateStarted, String inputDateEnded) {
		logger.info("readAdminPeriod() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = "
				+ inputDateEnded);
		return dao.selectPeriod(inputDateStarted, inputDateEnded);
	}

	// 유저 OrderTicket
	@Override
	public List<MovieVO> readUserOrderTicket() {
		logger.info("readUserOrderTicket() call");
		return dao.selectOrderTicketToday();
	}

	// 유저 OrderReview
	@Override
	public List<MovieVO> readUserOrderReview() {
		logger.info("readUserOrderReview() call");
		return dao.selectOrderReviewToday();
	}

	// 유저 search by String(keyword)
	@Override
	public List<MovieVO> readUserSearch(String search) {
		logger.info("readUserSearch() call : search = " + search);
		return dao.selectSearchToday(search);
	}

	// search by date
	@Override
	public List<MovieVO> readDate(String inputDate) {
		logger.info("readDate() call : inputDate = " + inputDate);
		return dao.selectDate(inputDate);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() call");
		try {
			return dao.update(vo);
		} catch (DataIntegrityViolationException sqle) {
			// 변경불가 상태일때 -2로 리턴
			logger.debug(sqle.getMessage());
			return -2;
		} catch (Exception e) {
			return dao.update(vo);
		}
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() call : mvId = " + mvId);
		try {
			return dao.delete(mvId);
		} catch (DataIntegrityViolationException sqle) {
			// 변경불가 상태일때 -2반환
			logger.debug(sqle.getMessage());
			return -2;
		} catch (Exception e) {
			return dao.delete(mvId);
		}
	}

	// 영화 평균 평점 불러오는 메소드
	@Override
	public float readRatingAvg(int mvId) {
		logger.info("readRatingAvg() call : mvId = " + mvId);
		return dao.selectRatingAvg(mvId);
	}

	// 각 영화마다 예매율을 구하기 위함
	@Override
	public int readMvTicketSalesTotal() {
		logger.info("readMvTicketSalesTotal() call");
		return dao.selectTicketSalesTotal();
	}

}
