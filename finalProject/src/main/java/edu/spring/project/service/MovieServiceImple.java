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
		logger.info("create() 호출");
		return dao.insert(vo);
	}

	@Override
	public MovieVO read(int mvId) {
		logger.info("read() 호출 : mvId = " + mvId);
		return dao.select(mvId);
	}

	// 예매율 기준 정렬
	@Override
	public List<MovieVO> readTs() {
		logger.info("readTs() 호출");
		return dao.selectTs();
	}

	// 평점기준 정렬
	@Override
	public List<MovieVO> readRa() {
		logger.info("readRa() 호출");
		return dao.selectRa();
	}

	@Override
	public List<MovieVO> read(String inputDateStarted, String inputDateEnded) {
		logger.info("select() 호출 : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		return dao.select(inputDateStarted, inputDateEnded);
	}

	@Override
	public List<MovieVO> read(String inputDate) {
		logger.info("select() 호출 : inputDate = " + inputDate);
		return dao.select(inputDate);
	}

	@Override
	public List<MovieVO> readSearch(String search) {
		logger.info("readSearch() 호출 : search = " + search);
		return dao.selectSearch(search);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() 호출");
		return dao.update(vo);
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() 호출 : mvId = " + mvId);
		return dao.delete(mvId);
	}

	@Override
	public float readRatingAvg(int mvId) {
		logger.info("readRatingAvg() 호출 : mvId = " + mvId);
		return dao.selectRatingAvg(mvId);
	}

}
