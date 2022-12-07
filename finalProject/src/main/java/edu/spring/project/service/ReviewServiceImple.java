package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.ReviewVO;
import edu.spring.project.domain.TicketVO;
import edu.spring.project.persistence.MovieDAO;
import edu.spring.project.persistence.ReviewDAO;
import edu.spring.project.persistence.TicketDAO;
import edu.spring.project.util.TimeCompareUtil;

@Service
public class ReviewServiceImple implements ReviewService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private MovieDAO movieDao;

	@Autowired
	private TicketDAO ticketDao;

	@Transactional
	@Override
	public int create(ReviewVO vo) {
		logger.info("create() call");
		reviewDao.insert(vo); // both data sh be changed
		movieDao.updateRating(1, vo.getRvRating(), vo.getMvId());
		logger.info("reivew create success");
		return 1;
	}

	@Override
	public List<ReviewVO> read(int mvId) {
		logger.info("read() call : mvId = " + mvId);
		return reviewDao.select(mvId);
	}

	@Override
	public List<ReviewVO> read(String mmbId) {
		logger.info("read() call : mmbId = " + mmbId);
		return reviewDao.select(mmbId);
	}

	@Override
	public ReviewVO readOne(int rvId) {
		logger.info("readOne() call");
		return reviewDao.selectOne(rvId);
	}

	@Transactional
	@Override
	public int update(ReviewVO vo) {
		logger.info("update() call: rvId=" + vo.getRvId());
		reviewDao.update(vo);
		// same as create
		movieDao.updateRating(0, -vo.getRvRatingBefore() + vo.getRvRating(), vo.getMvId());
		logger.info("movieRating update success");
		return 1;
	}

	@Transactional
	@Override
	public int delete(ReviewVO vo) {
		logger.info("delete() call : rvId=" + vo.getRvId());
		reviewDao.delete(vo.getRvId());
		movieDao.updateRating(-1, -vo.getRvRating(), vo.getMvId());
		logger.info("delete success");
		return 1;
	}

	@Override
	public Integer check(String mmbId, int mvId) {
		logger.info("check() call");
		// 구입 내역 확인 리스트의 첫번째 값에 상영날짜시간 최신값이 정렬되어 들어감
		List<TicketVO> isBuy = ticketDao.buyCheck(mmbId, mvId);
		// 구입 안했을때 -2 반환
		if (isBuy.isEmpty()) {
			return -2;
		} else { // 구입했을때
			// 동일한 영화와 계정으로 리뷰 등록되어 있으면 리뷰 등록 불가 -3 반환
			if (reviewDao.registerCheck(mmbId, mvId) != null) {
				return -3;
			}
			// 티켓시간이 현재시간보다 before일때 리뷰 등록가능 0 반환
			if (TimeCompareUtil.compareToNow(isBuy.get(0).getScdDate(), isBuy.get(0).getScdTime()).equals("before")) {
				return 0;
			} else { // 현재시간과 같거나 아직 상영전 영화라면 리뷰 등록 불가 -4 반환
				return -4;
			}
		}
	}

}
