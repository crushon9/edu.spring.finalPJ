package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.project.domain.MovieVO;
import edu.spring.project.persistence.MovieDAO;

@Service
public class MovieServiceImple implements MovieService {
	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImple.class);
	@Autowired
	private MovieDAO dao;

	@Override
	public int create(MovieVO vo) {
		logger.info("create() ȣ��");
		return dao.insert(vo);
	}

	@Override
	public List<MovieVO> read() {
		logger.info("read() ȣ��");
		return dao.select();
	}

	@Override
	public MovieVO read(String mvId) {
		logger.info("read() ȣ�� : mvId = " + mvId);
		return dao.select(mvId);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() ȣ��");
		return dao.update(vo);
	}

	@Override
	public int delete(String mvId) {
		logger.info("delete() ȣ�� : mvId = " + mvId);
		return dao.delete(mvId);
	}

}
