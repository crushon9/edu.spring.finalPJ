package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
public interface MypageService {
	// ����
//	int create(MemberVO vo);

	List<MemberVO> read();

	MemberVO read(String mmbId);
	// ����, ������ٲٱ� ����?
	int update(MemberVO vo);
	// Ż��
	int delete(String mmbId);
	// ����
//	MemberVO login(String mmbId, String mmbPassword);
}
