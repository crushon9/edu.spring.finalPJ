package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		// 변경불가 상태일때 -2반환
		int immutable = branchDao.selectImmutableCheck(vo.getBrcId());
		if (immutable != -2) {
			return branchDao.update(vo);
		} else {
			return immutable;
		}
	}

	@Transactional
	@Override
	public int delete(int brcId) {
		logger.info("delete() call : brcId = " + brcId);
		// 변경불가 상태일때 -2반환
		int immutable = branchDao.selectImmutableCheck(brcId);
		if (immutable != -2) {
			memberDao.replaceBrcIdDeleted(brcId, branchDao.selectMinBrcId());
			logger.info("replaceBrcIdDeleted 완료");
			return branchDao.delete(brcId);
		} else {
			return immutable;
		}
	}

}
