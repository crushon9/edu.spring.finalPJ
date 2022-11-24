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
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public List<BranchVO> read() {
		logger.info("read() 호출");
		return dao.select();
	}

	@Override
	public BranchVO read(int brcId) {
		logger.info("read() 호출 : brcId = " + brcId);
		return dao.select(brcId);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() 호출 : brcId = " + brcId);
		return dao.delete(brcId);
	}

	// 지역번호로 검색
	@Override
	public List<BranchVO> areaList(int brcArea) {
		logger.info("areaList() 호출 : brcArea = " + brcArea);
		return dao.areaList(brcArea);
	}

	// 지역명으로 검색
	@Override
	public List<BranchVO> areaList(String brcName) {
		logger.info("areaList() 호출 : brcName = " + brcName);
		return dao.areaList(brcName);
	}
}
