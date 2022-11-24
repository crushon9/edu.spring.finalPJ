package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

public interface ReviewDAO {

	int insert(ReviewVO vo);
	
	// list all mvId 기준 정렬
	List<ReviewVO> select(int mvId);
	
	// list all mmbId 기준 정렬
	List<ReviewVO> select(String mmbId);
	
	ReviewVO selectOne(int rvId);

	int update(ReviewVO vo);

	int delete(int rvId);
	
}
