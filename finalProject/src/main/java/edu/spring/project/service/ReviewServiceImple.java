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

@Service
public class ReviewServiceImple implements ReviewService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private MovieDAO movieDao;

	@Transactional
	@Override
	public int create(ReviewVO vo) {
		logger.info("create() 호출");
		reviewDao.insert(vo);
		movieDao.updateRating(1, vo.getRvRating(), vo.getMvId());
		logger.info("리뷰 등록 및 영화 평점 더하기 success");
		return 1;
	}

	@Override
	public List<ReviewVO> read(int mvId) {
		logger.info("read() 호출 : mvId = " + mvId);
		return reviewDao.select(mvId);
	}

	@Override
	public List<ReviewVO> read(String mmbId) {
		logger.info("read() 호출 : mmbId = " + mmbId);
		return reviewDao.select(mmbId);
	}

	@Override
	public ReviewVO readOne(int rvId) {
		logger.info("readOne() 호출");
		return reviewDao.selectOne(rvId);
	}

	@Transactional
	@Override
	public int update(ReviewVO vo) {
		logger.info("update() 호출: rvId=" + vo.getRvId());
		reviewDao.update(vo);
		// 리뷰 카운트는 0, 점수는 기존점수와 변경점수 둘다 view에서 넘겨줌
		movieDao.updateRating(0, -vo.getRvRatingBefore(), vo.getMvId());
		movieDao.updateRating(0, vo.getRvRating(), vo.getMvId());
		logger.info("리뷰 수정 및 영화 평점 수정  success");
		return 1;
	}

	@Transactional // 같이변동 내역
	@Override
	public int delete(ReviewVO vo) {
		logger.info("delete() 호출 : rvId=" + vo.getRvId());
		reviewDao.delete(vo.getRvId());
		movieDao.updateRating(-1, -vo.getRvRating(), vo.getMvId());
		logger.info("리뷰 삭제 및 영화 평점 빼기  success");
		return 1;
	}

}
