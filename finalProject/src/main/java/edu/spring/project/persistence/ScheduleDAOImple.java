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
	private static final String NAMESPACE = "edu.spring.project.scheduleMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(ScheduleVO vo) {
		logger.info("insert() call");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public ScheduleVO select(int scdId) {
		logger.info("select() call : scdId = " + scdId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_scd_id", scdId);
	}

	@Override
	public List<ScheduleVO> select(int mvId, int brcId, String scdDate) {
		logger.info("select() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mvId", mvId);
		args.put("brcId", brcId);
		args.put("scdDate", scdDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_if", args);
	}
	
	@Override
	public List<ScheduleVO> select(int mvId, int brcId, String scdDate, int scdTime) {
		logger.info("select 오늘날짜 () call : mvId=" + mvId + ", brcId=" + brcId + ", scdTime=" + scdTime);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mvId", mvId);
		args.put("brcId", brcId);
		args.put("scdDate", scdDate);
		args.put("scdTime", scdTime);
		return sqlSession.selectList(NAMESPACE + ".select_list_if_today", args);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() call : scdId = " + scdId);
		return sqlSession.delete(NAMESPACE + ".delete_by_scd_id", scdId);
	}

	@Override
	public int updateScdSeatBookedCnt(int amount, int scdId) {
		logger.info("updateScdSeatBookedCnt() call : scdId = " + scdId);
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("scdId", scdId);
		return sqlSession.update(NAMESPACE + ".update_scdSeatBookedCnt", args);
	}
}
