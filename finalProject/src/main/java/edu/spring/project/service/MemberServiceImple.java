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
		logger.info("create() call");
		return dao.insert(vo);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() call");
		return dao.select();
	}

	// updateGET && idCheck && detailGET
	@Override
	public MemberVO readOne(String mmbId) {
		logger.info("read() call : mmbId = " + mmbId);
		return dao.selectOne(mmbId);
	}

	// member admin listGET
	@Override
	public List<MemberVO> read(String mmbId) {
		logger.info("read() call");
		return dao.select(mmbId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() call");
		return dao.update(vo);
	}

	@Override
	public String idCheck(String mmbId) {
		logger.info("idCheck() call : mmbId = " + mmbId);
		return dao.idCheck(mmbId);
	}
	
	@Override
	public MemberVO login(String mmbId, String mmbPassword) {
		logger.info("login call : mmbId = " + mmbId + ", mmbPassword = " + mmbPassword);
		return dao.login(mmbId, mmbPassword);
	}

	@Override
	public int resign(String mmbId) {
		logger.info("delete() call : mmbId = " + mmbId);
		return dao.resign(mmbId);
	}


}
