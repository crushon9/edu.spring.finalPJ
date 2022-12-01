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

	// order by ticketSales
	@Override
	public List<MovieVO> selectOrderTicket() {
		logger.info("selectTs() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_order_ticket");
	}

	// order by ReviewAvg
	@Override
	public List<MovieVO> selectOrderReview() {
		logger.info("selectRa() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_order_review");
	}

	// search by period
	@Override
	public List<MovieVO> selectPeriod(String inputDateStarted, String inputDateEnded) {
		logger.info("selectPeriod() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = "
				+ inputDateEnded);
		Map<String, String> args = new HashMap<String, String>();
		args.put("inputDateStarted", inputDateStarted);
		args.put("inputDateEnded", inputDateEnded);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_period", args);
	}

	// search by date
	@Override
	public List<MovieVO> selectDate(String inputDate) {
		logger.info("selectDate() call : inputDate = " + inputDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_date", inputDate);
	}

	// search by String(keyword)
	@Override
	public List<MovieVO> selectSearch(String search) {
		logger.info("selectSearch() call : search = " + search);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_mv_title", search);
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

	@Override
	public int updateRating(int amount, int rvRating, int mvId) {
		logger.info("updateRating() : mvId = " + mvId);
		int result = 0;
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("rvRating", rvRating);
		args.put("mvId", mvId);
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
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("mvId", mvId);
		return sqlSession.update(NAMESPACE + ".update_ticketsales_by_mv_id", args);
	}

	// 각 영화마다 예매율을 구하기 위함
	@Override 
	public int selectTicketSalesTotal() {
		logger.info("selectTicketSalesTotal() call");
		return sqlSession.selectOne(NAMESPACE + ".select_ticketsales_total");
	}

}
