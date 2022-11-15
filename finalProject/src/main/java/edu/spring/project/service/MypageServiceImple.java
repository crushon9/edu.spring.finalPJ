package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.project.domain.MemberVO;
import edu.spring.project.persistence.MemberDAO;
// ���ų��� ��ȸ, �ۼ��ı⺸��, ���̵���ã��(api���� ����)
// ��� ��ȸ, ����, ����
@Service
public class MypageServiceImple implements MypageService {
	private static final Logger logger = LoggerFactory.getLogger(MypageServiceImple.class);
	@Autowired
	private MemberDAO memberDao;
	
//	@Autowired
//	private TicketDAO ticketDao;
	
//	@Autowired
//	private ReviewDAO reviewDao;
	
//	@Override
//	public int create(MemberVO vo) {
//		logger.info("create() ȣ��");
//		return memberDao.insert(vo);
//	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() ȣ��");
		return memberDao.select();
	}

	@Override
	public MemberVO read(String mmbId) {
		logger.info("read() ȣ�� : memberId = " + mmbId);
		return memberDao.select(mmbId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ��");
		return memberDao.update(vo);
	}

	@Override
	public int delete(String mmbId) {
		logger.info("delete() ȣ�� : memberId = " + mmbId);
		return memberDao.delete(mmbId);
	}

//	@Override
//	public MemberVO login(String mmbId, String mmbPassword) {
//		logger.info("login call : mmbId = " + mmbId + ", mmbPassword = " + mmbPassword);
//		return memberDao.login(mmbId, mmbPassword);
//	}

}
