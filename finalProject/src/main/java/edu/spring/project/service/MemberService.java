package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
public interface MemberService {
	// 등록
	int create(MemberVO vo);

	List<MemberVO> read();
	
	// mmbId로 특정 유저만 보기
	MemberVO readOne(String mmbId);
	
	// searchMmbId로 검색기능
	List<MemberVO> read(String mmbId);
	
	// 삭제
	int update(MemberVO vo);
	
	// 삭제
	int delete(String mmbId);
	
	// 로그인, 로그아웃
	MemberVO login(String mmbId, String mmbPassword);
}
