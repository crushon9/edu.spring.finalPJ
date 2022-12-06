package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.spring.project.domain.BranchVO;
import edu.spring.project.persistence.BranchDAO;
import edu.spring.project.persistence.MemberDAO;

@Service
public class BranchServiceImple implements BranchService {
	private static final Logger logger = LoggerFactory.getLogger(BranchServiceImple.class);
	@Autowired
	private BranchDAO branchDao;
	@Autowired
	private MemberDAO memberDao;

	@Override
	public int create(BranchVO vo) {
		logger.info("create() call");
		return branchDao.insert(vo);
	}

	@Override
	public List<BranchVO> read() {
		logger.info("read() call");
		return branchDao.select();
	}

	@Override
	public BranchVO readOne(int brcId) {
		logger.info("readOne() call : brcId = " + brcId);
		return branchDao.selectOne(brcId);
	}

	// search by brcArea
	@Override
	public List<BranchVO> readBrcArea(int brcArea) {
		logger.info("read() call : brcArea = " + brcArea);
		return branchDao.selectBrcArea(brcArea);
	}

	// search by brcName
	@Override
	public List<BranchVO> readBrcName(String searchBrcName) {
		logger.info("read() call : searchBrcName = " + searchBrcName);
		return branchDao.selectBrcName(searchBrcName);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() call");
		try {
			return branchDao.update(vo);
		} catch (DataIntegrityViolationException sqle) {
			// 변경불가 상태일때 -2반환
			logger.debug(sqle.getMessage());
			return -2;
		} catch (Exception e) {
			return branchDao.update(vo);
		}
	}

	@Transactional
	@Override
	public int delete(int brcId) {
		logger.info("delete() call : brcId = " + brcId);
		try {
			memberDao.replaceBrcIdDeleted(brcId, branchDao.selectMinBrcId());
			logger.info("replaceBrcIdDeleted 완료");
			return branchDao.delete(brcId);
		} catch (DataIntegrityViolationException sqle) {
			// 변경불가 상태일때 -2반환
			logger.debug(sqle.getMessage());
			return -2;
		} catch (Exception e) {
			return branchDao.delete(brcId);
		}
	}

}
