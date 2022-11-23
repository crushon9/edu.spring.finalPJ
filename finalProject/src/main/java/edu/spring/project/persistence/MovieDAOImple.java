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
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_ts");
	}

	// �������� ����
	@Override
	public List<MovieVO> selectRa() {
		logger.info("select() ȣ��");
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
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search", search);
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

	@Override // 10.17
	public int updateRating(int amount, int rvRating, int mvId) {
		logger.info("updateRating() : mvId = " + mvId);		
		// sqlSession data ���� �˻� ����
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("rvRating", rvRating);
		args.put("mvId", mvId);		
		return sqlSession.update(NAMESPACE + ".update_mv_ratings_by_mv_id", args);
	}//end updateReplyCnt
	
	
}


