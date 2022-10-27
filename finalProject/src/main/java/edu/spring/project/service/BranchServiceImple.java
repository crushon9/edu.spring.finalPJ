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
	public BranchVO read(String brcId) {
		logger.info("read() ȣ�� : memberId = " + brcId);
		return dao.select(brcId);
	}

	@Override
	public int update(BranchVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(String brcId) {
		logger.info("delete() ȣ�� : memberId = " + brcId);
		return dao.delete(brcId);
	}

}
