package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.ScheduleVO;

@Repository
public class ScheduleDAOImple implements ScheduleDAO {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.scheduleMapper"; // schedule-mapper.xml

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ScheduleVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public ScheduleVO select(int scdId) {
		logger.info("select() 호출 : scdId = " + scdId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_scd_id", scdId);
	}

	@Override
	public List<ScheduleVO> select(int mvId, int brcId, String scdDate) {
		logger.info("select() 호출 : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mvId", mvId);
		args.put("brcId", brcId);
		args.put("scdDate", scdDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_if", args);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() 호출 : scdId = " + scdId);
		return sqlSession.delete(NAMESPACE + ".delete_by_scd_id", scdId);
	}

	@Override
	public int updateScdSeatBookedCnt(int amount, int scdId) {
		logger.info("updateScdSeatBookedCnt() 호출 : scdId = " + scdId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("scdId", scdId);
		return sqlSession.update(NAMESPACE + ".update_scdSeatBookedCnt", args);
	}

}
