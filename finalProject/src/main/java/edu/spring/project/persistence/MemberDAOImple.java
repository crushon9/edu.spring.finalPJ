package edu.spring.project.persistence;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImple.class);
	private static final String NAMESPACE = "edu.spring.project.memberMapper"; // member-mapper.xml
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MemberVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE�� ������ mapper�� ã�ư��� id="insert"�� insert �±׿� vo �����͸� ����
	}

	@Override
	public List<MemberVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all_member_id");
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select() ȣ�� : boardId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}

}
