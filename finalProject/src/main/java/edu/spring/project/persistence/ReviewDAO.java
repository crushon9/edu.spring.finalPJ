package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

public interface ReviewDAO {

	int insert(ReviewVO vo);

	// read list by mvId
	List<ReviewVO> select(int mvId);

	// read list by mvId
	List<ReviewVO> select(String mmbId);

	ReviewVO selectOne(int rvId);

	int update(ReviewVO vo);

	int delete(int rvId);

	// 한 계정에 한 영화 한 리뷰 체크
	Integer registerCheck(String mmbId, int mvId);
}
