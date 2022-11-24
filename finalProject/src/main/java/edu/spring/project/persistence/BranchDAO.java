package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.BranchVO;

public interface BranchDAO {

	int insert(BranchVO vo);

	List<BranchVO> select();

	BranchVO select(int brcId);

	int update(BranchVO vo);

	int delete(int brcId);
	
	// ������ȣ�� �˻�
	List<BranchVO> areaList(int brcArea);
	
	// ���������� �˻�
	List<BranchVO> areaList(String brcName);
}
