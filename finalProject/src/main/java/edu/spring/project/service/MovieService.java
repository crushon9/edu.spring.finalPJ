package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);
	
	// ������ ���� ����
	List<MovieVO> readTs();
	
	// �������� ����
	
	List<MovieVO> readRa();
	MovieVO read(int mvId);

	int update(MovieVO vo);

	int delete(int mvId);
	
	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	List<MovieVO> select(String inputDate);

}
