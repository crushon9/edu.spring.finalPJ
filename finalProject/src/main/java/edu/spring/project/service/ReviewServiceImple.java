package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.ReviewVO;
import edu.spring.project.persistence.MovieDAO;
import edu.spring.project.persistence.ReviewDAO;
import edu.spring.project.persistence.TicketDAO;

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
		movieDao.updateRating(0, -vo.getRvRatingBefore(), vo.getMvId());
		movieDao.updateRating(0, vo.getRvRating(), vo.getMvId());
		logger.info("update success");
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
		Integer result = 0; // 등록가능
		List<Integer> isBuy = ticketDao.buyCheck(mmbId, mvId);
		if (isBuy.isEmpty()) { // 구입 안했을때
			result = -1;
		} else {
			Integer isRegister = reviewDao.registerCheck(mmbId, mvId);
			if (isRegister != null) { // 등록 되어있을때
				result = -2;
			}
		}
		return result;
	}

}
