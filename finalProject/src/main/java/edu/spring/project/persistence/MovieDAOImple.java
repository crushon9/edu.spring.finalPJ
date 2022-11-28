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
	private static final String NAMESPACE = "edu.spring.project.movieMapper"; // movie-mapper.xml�� ����

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MovieVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public MovieVO select(int mvId) {
		logger.info("select() ȣ�� : mvId = " + mvId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_mv_id", mvId);
	}

	// ������ ���� ����
	@Override
	public List<MovieVO> selectTs() {
		logger.info("selectTs() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_ts");
	}

	// �������� ����
	@Override
	public List<MovieVO> selectRa() {
		logger.info("selectRa() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_ra");
	}

	// select period
	@Override
	public List<MovieVO> select(String inputDateStarted, String inputDateEnded) {
		logger.info("select() ȣ�� : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		Map<String, String> args = new HashMap<String, String>();
		args.put("inputDateStarted", inputDateStarted);
		args.put("inputDateEnded", inputDateEnded);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_period", args);
	}

	// select date spot
	@Override
	public List<MovieVO> select(String inputDate) {
		logger.info("select() ȣ�� : inputDate = " + inputDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_date", inputDate);
	}

	// ���ڿ��˻�
	@Override
	public List<MovieVO> selectSearch(String search) {
		logger.info("selectSearch() ȣ�� : search = " + search);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_mv_title", search);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", mvId);
	}

	@Override // ���� ���� _ ��ȭ ���� ���� 
	public int updateRating(int amount, int rvRating, int mvId) {
		logger.info("updateRating() : mvId = " + mvId);
		int result = 0;
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("rvRating", rvRating);
		args.put("mvId", mvId);
		result = sqlSession.update(NAMESPACE + ".update_ratingTC_by_mv_id", args);
		if (result != 0) { // �տ� ���氪 ������ �Ϸ�� �� ��� ����Ͽ� DB ����
			result = sqlSession.update(NAMESPACE + ".update_ratingAVG_by_mv_id", mvId);
		}
		return result;
	}

	@Override // ����� ������ ����Ǹ�, ������ ��������
	public float selectRatingAvg(int mvId) {
		logger.info("selectRatingAvg() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".select_ratingAVG_by_mv_id", mvId);
	}

	@Override
	public int updateTicketSales(int amount, int mvId) {
		logger.info("updateTicketSales() ȣ�� : mvId = " + mvId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("mvId", mvId);
		return sqlSession.update(NAMESPACE + ".update_ticketsales_by_mv_id", args);
	}

}
