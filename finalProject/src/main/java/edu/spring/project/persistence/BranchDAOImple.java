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
		logger.info("insert() call");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<BranchVO> select() {
		logger.info("select() call");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	@Override
	public BranchVO selectOne(int brcId) {
		logger.info("selectOne() call : brcId = " + brcId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_brc_id", brcId);
	}

	// search by brcArea
	@Override
	public List<BranchVO> selectBrcArea(int brcArea) {
		logger.info("selectBrcArea() call : brcArea = " + brcArea);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_brc_area", brcArea);
	}

	// search by brcName
	@Override
	public List<BranchVO> selectBrcName(String searchBrcName) {
		logger.info("selectBrcName() call : searchBrcName = " + searchBrcName);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_brc_name", searchBrcName);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() call");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() call");
		return sqlSession.delete(NAMESPACE + ".delete", brcId);
	}

	// 브랜치 삭제시 멤버 선호지점을 가장작은 번호로 바꾸기 위해
	@Override
	public int selectMinBrcId() {
		logger.info("selectMinBrcId() call");
		return sqlSession.selectOne(NAMESPACE + ".select_min_brc_id");
	}

	// 데이터 변경가능여부 체크 immutableCheck
	@Override
	public int selectImmutableCheck(int brcId) {
		logger.info("selectImmutableCheck() call");
		return sqlSession.selectOne(NAMESPACE + ".select_brcImmutableCheck_by_brc_id", brcId);
	}

	@Override
	public int updateImmutableCheck(int brcId) {
		logger.info("updateImmutableCheck() call");
		return sqlSession.update(NAMESPACE + ".update_brcImmutableCheck_by_brc_id", brcId);
	}

}
