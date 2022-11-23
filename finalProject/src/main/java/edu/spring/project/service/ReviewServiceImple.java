package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.ReviewVO;
import edu.spring.project.persistence.MovieDAO;
import edu.spring.project.persistence.ReviewDAO;


@Service
public class ReviewServiceImple implements ReviewService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImple.class);
	@Autowired
	private ReviewDAO reviewDao;

	@Autowired
	private MovieDAO movieDao;
	
	@Transactional
	@Override
	public int create(ReviewVO vo) {
		logger.info("create() ȣ��");
		reviewDao.insert(vo);
		movieDao.updateRating(1, vo.getRvRating(), vo.getMvId());
		logger.info("rvCreate&&rating success");
		return 1;
	}

	@Override
	public List<ReviewVO> read(int mvId) {
		logger.info("read() ȣ��");
		return reviewDao.select(mvId);
	}

	@Override
	public List<ReviewVO> read(String mmbId) {
		logger.info("read() ȣ��");
		return reviewDao.select(mmbId);
	}
		
	@Override
	public ReviewVO readOne(int rvId) {
		logger.info("update() ȣ��");
		return reviewDao.selectOne(rvId);
	}

	@Override
	public int update(ReviewVO vo) {
		logger.info("update() ȣ��");
		return reviewDao.update(vo);
	}
	
	@Transactional // ���̺��� ����
	@Override
	public int delete(ReviewVO vo) {
		logger.info("delete() ȣ��");
		reviewDao.delete(vo);
		movieDao.updateRating(-1, -vo.getRvRating(), vo.getMvId());
		logger.info("rvCreate&&rating success");
		return 1;
	}
	
}
