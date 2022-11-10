package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);
	// MovieVO vo 넣고 콜 가능?
	List<MovieVO> read(MovieVO vo);

	MovieVO read(int mvId);

	int update(MovieVO vo);

	int delete(int mvId);
	
	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	List<MovieVO> select(String inputDate);

}
