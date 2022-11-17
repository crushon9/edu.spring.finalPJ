package edu.spring.project.persistence;

import java.util.List;
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
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<TicketVO> selectScdId(int scdId) {
		logger.info("selectScdId() 호출 : scdId = " + scdId);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_scd_id", scdId);
	}

}
