package edu.spring.project.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.spring.project.domain.ReviewVO;

@Repository
public class ReviewDAOImple implements ReviewDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.reviewMapper"; // movie-mapper.xml�� ����

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ReviewVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReviewVO> select(int mvId) {
		logger.info("select() ȣ�� mvId = " + mvId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mv_id", mvId);
	}

	@Override
	public List<ReviewVO> select(String mmbId) {
		logger.info("select() ȣ�� mmbId = " + mmbId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mmb_id", mmbId);
	}

	@Override
	public ReviewVO selectOne(int rvId) {
		logger.info("selectOne() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_rv_id", rvId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int rvId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", rvId);
	}

}
