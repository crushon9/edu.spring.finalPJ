package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.spring.project.domain.TicketVO;

@Repository
public class TicketDAOImple implements TicketDAO {

	private static final Logger logger = LoggerFactory.getLogger(TicketDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.ticketMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(TicketVO vo) {
		logger.info("insert() call");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	@Override
	public List<TicketVO> select() {
		logger.info("select() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	@Override
	public List<TicketVO> select(int scdId) {
		logger.info("select() call : scdId = " + scdId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_scd_id", scdId);
	}

	@Override
	public List<TicketVO> select(String mmbId) {
		logger.info("select() call : mmbId = " + mmbId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_mmb_id", mmbId);
	}

	@Override
	public int delete(int tkId) {
		logger.info("delete() call");
		return sqlSession.insert(NAMESPACE + ".delete", tkId);
	}
	
	@Override
	public List<Integer> buyCheck(String mmbId, int mvId) {
		logger.info("buyCheck() call : mmbId=" + mmbId + ", mvId=" + mvId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mmbId", mmbId);
		args.put("mvId", mvId);
		return sqlSession.selectList(NAMESPACE + ".buy_check", args);
	}

}
