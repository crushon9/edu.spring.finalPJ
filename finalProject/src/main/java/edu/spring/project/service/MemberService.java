package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
//���(create), �α���, �α׾ƿ�, ���̵� ã��?
public interface MemberService {
	// ���
	int create(MemberVO vo);

	List<MemberVO> read();

	MemberVO read(String mmbId);
	// ����
	int update(MemberVO vo);
	// ����
	int delete(String mmbId);
	// �α���, �α׾ƿ�
	MemberVO login(String mmbId, String mmbPassword);
}
