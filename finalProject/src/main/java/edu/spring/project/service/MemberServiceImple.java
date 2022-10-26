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
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() 호출");
		return dao.select();
	}

	@Override
	public MemberVO read(String memberId) {
		logger.info("read() 호출 : memberId = " + memberId);
		return dao.select(memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete() 호출 : memberId = " + memberId);
		return dao.delete(memberId);
	}

}
