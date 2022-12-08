package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.spring.project.domain.MovieVO;
import edu.spring.project.util.TimeCompareUtil;

@Repository
public class MovieDAOImple implements MovieDAO {
	private static final Logger logger = LoggerFactory.getLogger(MovieDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.movieMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MovieVO vo) {
		logger.info("insert() call");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public MovieVO select(int mvId) {
		logger.info("select() call : mvId = " + mvId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_mv_id", mvId);
	}

	// 관리자 기본검색
	@Override
	public List<MovieVO> selectAll() {
		logger.info("selectAll() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	// 관리자 search by period
	@Override
	public List<MovieVO> selectPeriod(String inputDateStarted, String inputDateEnded) {
		logger.info("selectPeriod() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = "
				+ inputDateEnded);
		Map<String, String> args = new HashMap<String, String>();
		args.put("inputDateStarted", inputDateStarted);
		args.put("inputDateEnded", inputDateEnded);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_period", args);
	}

	// 관리자 search by String(keyword)
	@Override
	public List<MovieVO> selectSearch(String search) {
		logger.info("selectSearch() call : search = " + search);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_mv_title", search);
	}

	// 유저 order by ticketSales
	@Override
	public List<MovieVO> selectOrderTicketToday() {
		logger.info("selectOrderTicketToday() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_order_ticket_today", TimeCompareUtil.today());
	}

	// 유저 order by ReviewAvg
	@Override
	public List<MovieVO> selectOrderReviewToday() {
		logger.info("selectOrderReviewToday() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_order_review_today", TimeCompareUtil.today());
	}

	// 유저 search by String(keyword)
	@Override
	public List<MovieVO> selectSearchToday(String search) {
		logger.info("selectSearchToday() call : search = " + search);
		Map<String, String> args = new HashMap<String, String>();
		args.put("searchMvTitle", search);
		args.put("today", TimeCompareUtil.today());
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_mv_title_today", args);
	}

	// search by date
	@Override
	public List<MovieVO> selectDate(String inputDate) {
		logger.info("selectDate() call : inputDate = " + inputDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_date", inputDate);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() call");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() call");
		return sqlSession.delete(NAMESPACE + ".delete", mvId);
	}

	// review 등록 시, 평점 total도 변경
	@Override
	public int updateRating(int amount, int rvRating, int mvId) {
		logger.info("updateRating() : mvId = " + mvId);
		int result = 0;
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("rvRating", rvRating);
		args.put("mvId", mvId);
		// 평점총합변경 후, 평균평점 변경
		result = sqlSession.update(NAMESPACE + ".update_ratingTC_by_mv_id", args);
		if (result != 0) {
			result = sqlSession.update(NAMESPACE + ".update_ratingAVG_by_mv_id", mvId);
		}
		return result;
	}

	// mvId로 평균평점 변경시 평점만 가져온다.
	@Override
	public float selectRatingAvg(int mvId) {
		logger.info("selectRatingAvg() call");
		return sqlSession.selectOne(NAMESPACE + ".select_ratingAVG_by_mv_id", mvId);
	}

	// ticketSales + amount(취소 -1, 결제 +1)
	@Override
	public int updateTicketSales(int amount, int mvId) {
		logger.info("updateTicketSales() call : mvId = " + mvId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("amount", amount);
		args.put("mvId", mvId);
		args.put("today", TimeCompareUtil.today());
		return sqlSession.update(NAMESPACE + ".update_ticketsales_by_mv_id", args);
	}

	// 각 영화마다 예매율을 구하기 위함
	@Override
	public int selectTicketSalesTotal() {
		logger.info("selectTicketSalesTotal() call");
		return sqlSession.selectOne(NAMESPACE + ".select_ticketsales_total", TimeCompareUtil.today());
	}

}
