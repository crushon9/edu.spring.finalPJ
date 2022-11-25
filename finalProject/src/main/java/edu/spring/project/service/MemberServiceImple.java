package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.project.domain.MemberVO;
import edu.spring.project.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);
	@Autowired
	private MemberDAO dao;

	@Override
	public int create(MemberVO vo) {
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() ȣ��");
		return dao.select();
	}

	@Override
	public MemberVO readOne(String mmbId) {
		logger.info("read() ȣ�� : mmbId = " + mmbId);
		return dao.selectOne(mmbId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(String mmbId) {
		logger.info("delete() ȣ�� : mmbId = " + mmbId);
		return dao.delete(mmbId);
	}

	@Override
	public MemberVO login(String mmbId, String mmbPassword) {
		logger.info("login call : mmbId = " + mmbId + ", mmbPassword = " + mmbPassword);
		return dao.login(mmbId, mmbPassword);
	}
	
	// mmbId�� �˻�
	@Override
	public List<MemberVO> read(String mmbId) {
		logger.info("read() ȣ��");
		return dao.select(mmbId);
	}

}
