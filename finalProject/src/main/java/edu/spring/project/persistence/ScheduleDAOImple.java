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
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE�� ������ mapper�� ã�ư��� id="insert"�� insert �±׿� vo �����͸� ����
	}

	@Override
	public List<ScheduleVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all_scd_id");
	}

	@Override
	public ScheduleVO select(int scdId) {
		logger.info("select() ȣ�� : scdId = " + scdId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_scd_id", scdId);
	}

	@Override
	public int update(ScheduleVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", scdId);
	}

}
