package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);

	MovieVO read(int mvId);

	// 예매율기준 정렬
	List<MovieVO> readTs();

	// 평점기준 정렬
	List<MovieVO> readRa();

	// 기간 검색
	List<MovieVO> read(String inputDateStarted, String inputDateEnded);

	// 날짜 검색
	List<MovieVO> read(String inputDate);

	// 문자열 검색
	List<MovieVO> readSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	float readRatingAvg(int mvId);

}
