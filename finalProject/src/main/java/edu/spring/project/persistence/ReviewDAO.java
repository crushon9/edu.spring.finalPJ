package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.ReviewVO;

public interface ReviewDAO {

	int insert(ReviewVO vo);
	
	// list mvId ���� ����
	List<ReviewVO> select(int mvId);
	
	// list mmbId ���� ����
	List<ReviewVO> select(String mmbId);
	
	ReviewVO selectOne(int rvId);

	int update(ReviewVO vo);

	int delete(int rvId);
	
}
