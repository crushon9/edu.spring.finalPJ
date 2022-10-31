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
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE�� ������ mapper�� ã�ư��� id="insert"�� insert �±׿� vo �����͸� ����
	}

	@Override
	public List<BranchVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}

	@Override
	public BranchVO select(int brcId) {
		logger.info("select() ȣ�� : boardId = " + brcId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_brc_id", brcId);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", brcId);
	}

	@Override
	public List<BranchVO> areaList(int brcArea) {
		logger.info("areaList() ȣ�� : brcArea = " + brcArea);
		return sqlSession.selectList(NAMESPACE + ".select_list_by_brc_area");
	}
}
