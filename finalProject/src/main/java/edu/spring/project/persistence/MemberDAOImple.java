package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	}//end insert

	@Override
	public List<MemberVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_all");
	}//end select List

	@Override
	public MemberVO selectOne(String mmbId) {
		logger.info("select() ȣ�� : mmbId = " + mmbId);
		return sqlSession.selectOne(NAMESPACE + ".select_one_by_mmb_id", mmbId);
	}//end select

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}//end update

	@Override
	public int delete(String mmbId) {
		logger.info("delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", mmbId);
	}//end delete

	@Override
	public MemberVO login(String mmbId, String mmbPassword) {
		logger.info("login() ȣ��");		
		Map<String, String> args = new HashMap<String, String>();
		args.put("mmbId", mmbId);
		args.put("mmbPassword", mmbPassword);
		return sqlSession.selectOne(NAMESPACE + ".login", args);		
	}//end login()

	// mmbId�� �˻�
	@Override
	public List<MemberVO> select(String mmbId) {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_list_by_search_mmb_id", mmbId);
	}
	
}
