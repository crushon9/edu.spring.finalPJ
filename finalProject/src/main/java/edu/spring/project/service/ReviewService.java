package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

//CRUD (Create, Read, Update, Delete)
public interface ReviewService {

	int create(ReviewVO vo);
	
	// list all mmbId ���� ����
	List<ReviewVO> read(int mvId);

	// list all mvId ���� ����
	List<ReviewVO> read(String mmbId);

	ReviewVO readOne(int mvId);

	int update(ReviewVO vo);

	int delete(ReviewVO vo);

}
