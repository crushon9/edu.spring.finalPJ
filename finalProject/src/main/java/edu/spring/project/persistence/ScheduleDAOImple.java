package edu.spring.project.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.ScheduleVO;

@Repository
public class ScheduleDAOImple implements ScheduleDAO{
	private static final Logger logger = LoggerFactory.getLogger(ScheduleDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.scheduleMapper"; // scd-mapper.xml
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ScheduleVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE가 동일한 mapper를 찾아가서 id="insert"인 insert 태그에 vo 데이터를 전송
	}

	@Override
	public List<ScheduleVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all_scd_id");
	}

	@Override
	public ScheduleVO select(int scdId) {
		logger.info("select() 호출 : scdId = " + scdId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_scd_id", scdId);
	}

	@Override
	public int update(ScheduleVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", scdId);
	}

}
