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
	private static final String NAMESPACE = "edu.spring.project.movieMapper"; // movie-mapper.xml와 연결

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MovieVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public MovieVO select(int mvId) {
		logger.info("select() 호출 : mvId = " + mvId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_mv_id", mvId);
	}

	// 예매율 기준 정렬
	@Override
	public List<MovieVO> selectTs() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_ts");
	}

	// 평점기준 정렬
	@Override
	public List<MovieVO> selectRa() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_ra");
	}

	// select period
	@Override
	public List<MovieVO> select(String inputDateStarted, String inputDateEnded) {
		logger.info("select() 호출 : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		Map<String, String> args = new HashMap<String, String>();
		args.put("inputDateStarted", inputDateStarted);
		args.put("inputDateEnded", inputDateEnded);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_period", args);
	}

	// select date spot
	@Override
	public List<MovieVO> select(String inputDate) {
		logger.info("select() 호출 : inputDate = " + inputDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_date", inputDate);
	}

	// 문자열검색
	@Override
	public List<MovieVO> selectSearch(String search) {
		logger.info("selectSearch() 호출 : search = " + search);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search", search);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", mvId);
	}

	@Override // 리뷰 결합 _ 영화 평점 변경 
	public int updateRating(int amount, int rvRating, int mvId) {
		logger.info("updateRating() : mvId = " + mvId);
		int result = 0;
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("rvRating", rvRating);
		args.put("mvId", mvId);
		result = sqlSession.update(NAMESPACE + ".update_ratingTC_by_mv_id", args);
		if (result != 0) { // 앞에 변경값 적용이 완료된 후 평균 계산하여 DB 저장
			result = sqlSession.update(NAMESPACE + ".update_ratingAVG_by_mv_id", mvId);
		}
		return result;
	}

	@Override // 리뷰로 평점이 변경되면, 평점만 가져오기
	public float selectRatingAvg(int mvId) {
		logger.info("selectRatingAvg() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_ratingAVG_by_mv_id", mvId);
	}

	@Override
	public int updateTicketSales(int amount, int mvId) {
		logger.info("updateTicketSales() 호출 : mvId = " + mvId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("mvId", mvId);
		return sqlSession.update(NAMESPACE + ".update_ticketsales_by_mv_id", args);
	}

}
