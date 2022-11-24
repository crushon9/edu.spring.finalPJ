package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.BranchVO;

public interface BranchDAO {

	int insert(BranchVO vo);

	List<BranchVO> select();

	BranchVO select(int brcId);

	int update(BranchVO vo);

	int delete(int brcId);
	
	// 지역번호로 검색
	List<BranchVO> areaList(int brcArea);
	
	// 지역명으로 검색
	List<BranchVO> areaList(String brcName);
}
