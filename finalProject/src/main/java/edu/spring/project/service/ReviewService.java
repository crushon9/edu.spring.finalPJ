package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

//CRUD (Create, Read, Update, Delete)
public interface ReviewService {

	int create(ReviewVO vo);

	// list mvId ���� ����
	List<ReviewVO> read(int mvId);

	// list mmbId ���� ����
	List<ReviewVO> read(String mmbId);

	ReviewVO readOne(int mvId);

	int update(ReviewVO vo);

	int delete(ReviewVO vo);

}
