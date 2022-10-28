package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);

	List<MovieVO> read();

	MovieVO read(String mvId);

	int update(MovieVO vo);

	int delete(String mvId);

}