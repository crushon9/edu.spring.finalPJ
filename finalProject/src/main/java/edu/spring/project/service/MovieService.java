package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);
	
	// 예매율 기준 정렬
	List<MovieVO> readTs();
	
	// 평점기준 정렬
	
	List<MovieVO> readRa();
	MovieVO read(int mvId);

	int update(MovieVO vo);

	int delete(int mvId);
	
	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	List<MovieVO> select(String inputDate);

}
