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
		// 리뷰 내역은 삽입/등록되고, 평점은 업데이트된다
		reviewDao.insert(vo);
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
		// 영화의 평균평점 변경 반영 updateRating(변경갯수, 변경점수, 영화아이디)
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
	public String check(String mmbId, int mvId) {
		logger.info("check() call");
		// 동일한 영화와 계정으로 리뷰 등록되어 있으면
		if (reviewDao.registerCheck(mmbId, mvId) != null) {
			return "impossible_existed";
		}
		// 구입 내역 확인 리스트의 첫번째 값에 상영날짜시간 가장 빠른 값 순서로 정렬되어있음
		List<TicketVO> buyTicketList = ticketDao.buyCheck(mmbId, mvId);
		// 구입 안했을때 buyTicketListrk 비어있으면
		if (buyTicketList.isEmpty()) {
			return "impossible_noTicket";
		} else { // 구입했을때
			// buyTicketList에 상영날짜시간이 빠른순서대로 정렬되어 있음 (index 0 가장 빠른값)
			String earliestDate = buyTicketList.get(0).getScdDate();
			int earliestTime = buyTicketList.get(0).getScdTime();
			// 티켓시간이 현재시간보다 before일때 리뷰등록가능
			if (TimeCompareUtil.compareToNow(earliestDate, earliestTime).equals("before")) {
				return "possible";
			} else { // 현재시간과 같거나 아직 상영전 영화라면
				return "impossible_time";
			}
		}
	}

}
