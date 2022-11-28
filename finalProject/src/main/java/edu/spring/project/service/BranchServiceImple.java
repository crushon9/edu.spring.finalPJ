package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.project.domain.BranchVO;
import edu.spring.project.persistence.BranchDAO;

@Service
public class BranchServiceImple implements BranchService {
	private static final Logger logger = LoggerFactory.getLogger(BranchServiceImple.class);
	@Autowired
	private BranchDAO dao;

	@Override
	public int create(BranchVO vo) {
		logger.info("create() call");
		return dao.insert(vo);
	}

	@Override
	public List<BranchVO> read() {
		logger.info("read() call");
		return dao.select();
	}

	@Override
	public BranchVO readOne(int brcId) {
		logger.info("readOne() call : brcId = " + brcId);
		return dao.selectOne(brcId);
	}
	
	// search by brcArea
	@Override
	public List<BranchVO> readBrcArea(int brcArea) {
		logger.info("read() call : brcArea = " + brcArea);
		return dao.selectBrcArea(brcArea);
	}
	
	// search by brcName
	@Override
	public List<BranchVO> readBrcName(String brcName) {
		logger.info("read() call : brcName = " + brcName);
		return dao.selectBrcName(brcName);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() call");
		return dao.update(vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() call : brcId = " + brcId);
		return dao.delete(brcId);
	}

}
