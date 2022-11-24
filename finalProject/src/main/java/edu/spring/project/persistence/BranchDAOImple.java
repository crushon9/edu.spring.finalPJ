package edu.spring.project.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.BranchVO;

@Repository
public class BranchDAOImple implements BranchDAO {
	private static final Logger logger = LoggerFactory.getLogger(BranchDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.branchMapper"; // branch-mapper.xml

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(BranchVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE가 동일한 mapper를 찾아가서 id="insert"인 insert 태그에 vo 데이터를 전송
	}

	@Override
	public List<BranchVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	@Override
	public BranchVO select(int brcId) {
		logger.info("select() 호출 : boardId = " + brcId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_brc_id", brcId);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete", brcId);
	}

	// 지역번호로 검색
	@Override
	public List<BranchVO> areaList(int brcArea) {
		logger.info("areaList() 호출 : brcArea = " + brcArea);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_brc_area", brcArea);
	}

	// 지역명으로 검색
	@Override
	public List<BranchVO> areaList(String brcName) {
		logger.info("areaList() 호출 : brcName = " + brcName);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_brc_name", brcName);
	}
}
