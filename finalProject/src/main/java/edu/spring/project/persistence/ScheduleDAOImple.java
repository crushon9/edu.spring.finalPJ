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
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public ScheduleVO select(int scdId) {
		logger.info("select() ȣ�� : scdId = " + scdId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_scd_id", scdId);
	}

	@Override
	public List<ScheduleVO> selectAll() {
		logger.info("selectAll() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	@Override
	public List<ScheduleVO> selectM(int mvId) {
		logger.info("selectM() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_m", mvId);
	}

	@Override
	public List<ScheduleVO> selectB(int brcId) {
		logger.info("selectB() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_b", brcId);
	}

	@Override
	public List<ScheduleVO> selectD(String scdDate) {
		logger.info("selectD() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_d", scdDate);
	}

	@Override
	public List<ScheduleVO> selectMB(int mvId, int brcId) {
		logger.info("selectMB() ȣ��");
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("mvId", mvId);
		args.put("brcId", brcId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mb", args);
	}

	@Override
	public List<ScheduleVO> selectMD(int mvId, String scdDate) {
		logger.info("selectMD() ȣ��");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mvId", mvId);
		args.put("scdDate", scdDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_md", args);
	}

	@Override
	public List<ScheduleVO> selectBD(int brcId, String scdDate) {
		logger.info("selectBD() ȣ��");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("brcId", brcId);
		args.put("scdDate", scdDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_bd", args);
	}

	@Override
	public List<ScheduleVO> selectMBD(int mvId, int brcId, String scdDate) {
		logger.info("selectMBD() ȣ��");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mvId", mvId);
		args.put("brcId", brcId);
		args.put("scdDate", scdDate);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mbd", args);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete_by_scd_id", scdId);
	}

}
