package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.BranchVO;

//CRUD (Create, Read, Update, Delete)
public interface BranchService {

	int create(BranchVO vo);

	// ��ü ����
	List<BranchVO> read();

	// brcId�� Ư�� ������ ����
	BranchVO read(int brcId);

	int update(BranchVO vo);

	int delete(int brcId);

	// ���� ������ȣ�� �˻�
	List<BranchVO> areaList(int brcArea);
	
	// ���� �̸����� �˻�/ �����ڿ� �˻� ����
	List<BranchVO> areaList(String brcName);

}
