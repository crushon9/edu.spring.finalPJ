package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

//CRUD (Create, Read, Update, Delete)
public interface ReviewService {

	int create(ReviewVO vo);

	// read list by mvId
	List<ReviewVO> read(int mvId);

	// read list by mmbId
	List<ReviewVO> read(String mmbId);

	ReviewVO readOne(int mvId);

	int update(ReviewVO vo);

	int delete(ReviewVO vo);
	
	// 티켓구매했는지, 리뷰 등록했는지 체크
	Integer check(String mmbId, int mvId);

}
