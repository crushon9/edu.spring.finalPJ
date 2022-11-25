package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.BranchVO;

public interface BranchDAO {

	int insert(BranchVO vo);

	List<BranchVO> select();

	BranchVO selectOne(int brcId);

	// ������ȣ�� �˻�
	List<BranchVO> select(int brcArea);
	
	// ���������� �˻�
	List<BranchVO> select(String brcName);
	
	int update(BranchVO vo);

	int delete(int brcId);
	
}
