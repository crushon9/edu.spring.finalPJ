package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);

	MovieVO read(int mvId);

	// ���������� ����
	List<MovieVO> readTs();

	// �������� ����
	List<MovieVO> readRa();

	// �Ⱓ �˻�
	List<MovieVO> read(String inputDateStarted, String inputDateEnded);

	// ��¥ �˻�
	List<MovieVO> read(String inputDate);

	// ���ڿ� �˻�
	List<MovieVO> readSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	float readRatingAvg(int mvId);

}
