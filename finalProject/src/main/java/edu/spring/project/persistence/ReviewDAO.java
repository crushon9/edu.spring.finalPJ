package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

public interface ReviewDAO {

	int insert(ReviewVO vo);


	// 평점기준 정렬
	List<ReviewVO> selectRa();
	
	ReviewVO select(int mvId);

	int update(ReviewVO vo);

	int delete(int mvId);
	/*
	 * List<MovieVO> select(String inputDateStarted, String inputDateEnded);
	 * 
	 * List<MovieVO> select(String inputDate);
	 */
}
