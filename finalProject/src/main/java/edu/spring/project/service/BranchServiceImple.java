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
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public List<BranchVO> read() {
		logger.info("read() ȣ��");
		return dao.select();
	}

	@Override
	public BranchVO readOne(int brcId) {
		logger.info("readOne() ȣ�� : brcId = " + brcId);
		return dao.selectOne(brcId);
	}
	
	// ������ȣ�� �˻�
	@Override
	public List<BranchVO> read(int brcArea) {
		logger.info("read() ȣ�� : brcArea = " + brcArea);
		return dao.select(brcArea);
	}
	
	// ���������� �˻�
	@Override
	public List<BranchVO> read(String brcName) {
		logger.info("read() ȣ�� : brcName = " + brcName);
		return dao.select(brcName);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(int brcId) {
		logger.info("delete() ȣ�� : brcId = " + brcId);
		return dao.delete(brcId);
	}

}
