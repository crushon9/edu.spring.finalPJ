package edu.spring.project.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.MovieVO;


@Repository
public class MovieDAOImple implements MovieDAO{
	private static final Logger logger = LoggerFactory.getLogger(MovieDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.movieMapper"; // movie-mapper.xml
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MovieVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<MovieVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_mv_id");
	}

	@Override
	public MovieVO select(String mvId) {
		logger.info("select() 호출 : mvId = " + mvId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_mv_id", mvId);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(String mvId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", mvId);
	}

}
