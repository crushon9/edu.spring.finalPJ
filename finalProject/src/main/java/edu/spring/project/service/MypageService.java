package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
public interface MypageService {
	// 삭제
//	int create(MemberVO vo);

	List<MemberVO> read();

	MemberVO read(String mmbId);
	// 수정, 비번만바꾸기 가능?
	int update(MemberVO vo);
	// 탈퇴
	int delete(String mmbId);
	// 삭제
//	MemberVO login(String mmbId, String mmbPassword);
}
