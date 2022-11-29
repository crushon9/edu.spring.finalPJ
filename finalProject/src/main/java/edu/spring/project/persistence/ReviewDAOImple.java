package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.spring.project.domain.ReviewVO;

@Repository
public class ReviewDAOImple implements ReviewDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.reviewMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ReviewVO vo) {
		logger.info("insert() call");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReviewVO> select(int mvId) {
		logger.info("select() call mvId = " + mvId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_id", mvId);
	}

	@Override
	public List<ReviewVO> select(String mmbId) {
		logger.info("select() call mmbId = " + mmbId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mmb_id", mmbId);
	}

	@Override
	public ReviewVO selectOne(int rvId) {
		logger.info("selectOne() call");
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_rv_id", rvId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("update() call");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int rvId) {
		logger.info("delete() call");
		return sqlSession.delete(NAMESPACE + ".delete", rvId);
	}

	@Override
	public Integer registerCheck(String mmbId, int mvId) {
		logger.info("registerCheck() call : mmbId=" + mmbId + ", mvId=" + mvId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mmbId", mmbId);
		args.put("mvId", mvId);
		return sqlSession.selectOne(NAMESPACE + ".registered_check", args);
	}

}
