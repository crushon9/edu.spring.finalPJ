package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
//등록(create), 로그인, 로그아웃, 아이디 찾기?
public interface MemberService {
	// 등록
	int create(MemberVO vo);

	List<MemberVO> read();

	MemberVO read(String mmbId);
	// 삭제
	int update(MemberVO vo);
	// 삭제
	int delete(String mmbId);
	// 로그인, 로그아웃
	MemberVO login(String mmbId, String mmbPassword);
}
