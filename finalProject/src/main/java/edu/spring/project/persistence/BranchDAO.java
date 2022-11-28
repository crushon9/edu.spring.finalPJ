package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.BranchVO;

public interface BranchDAO {

	int insert(BranchVO vo);

	List<BranchVO> select();

	// selectOne data
	BranchVO selectOne(int brcId);

	// search by brcArea
	List<BranchVO> selectBrcArea(int brcArea);
	
	// search by brcName
	List<BranchVO> selectBrcName(String brcName);
	
	int update(BranchVO vo);

	int delete(int brcId);
	
}
