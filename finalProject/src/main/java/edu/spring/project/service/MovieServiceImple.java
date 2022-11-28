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
		logger.info("create() call");
		return dao.insert(vo);
	}

	@Override
	public MovieVO read(int mvId) {
		logger.info("read() call : mvId = " + mvId);
		return dao.select(mvId);
	}

	// 예매율기준 정렬
	@Override
	public List<MovieVO> readTs() {
		logger.info("readTs() call");
		return dao.selectTs();
	}

	// 평점기준 정렬
	@Override
	public List<MovieVO> readRa() {
		logger.info("readRa() call");
		return dao.selectRa();
	}

	// search by period
	@Override
	public List<MovieVO> readPeriod(String inputDateStarted, String inputDateEnded) {
		logger.info("select() call : inputDateStarted = " + inputDateStarted + ", inputDateEnded = " + inputDateEnded);
		return dao.selectPeriod(inputDateStarted, inputDateEnded);
	}

	// search by date
	@Override
	public List<MovieVO> readDate(String inputDate) {
		logger.info("select() call : inputDate = " + inputDate);
		return dao.selectDate(inputDate);
	}

	// search by String
	@Override
	public List<MovieVO> readSearch(String search) {
		logger.info("readSearch() call : search = " + search);
		return dao.selectSearch(search);
	}

	@Override
	public int update(MovieVO vo) {
		logger.info("update() call");
		return dao.update(vo);
	}

	@Override
	public int delete(int mvId) {
		logger.info("delete() call : mvId = " + mvId);
		return dao.delete(mvId);
	}

	@Override
	public float readRatingAvg(int mvId) {
		logger.info("readRatingAvg() call : mvId = " + mvId);
		return dao.selectRatingAvg(mvId);
	}

}
