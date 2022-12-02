package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
public interface MemberService {
	int create(MemberVO vo);

	List<MemberVO> read();

	// updateGET && idCheck && detailGET
	MemberVO readOne(String mmbId);

	// member admin listGET
	List<MemberVO> read(String mmbId);

	int update(MemberVO vo);
	
	String idCheck(String mmbId);

	MemberVO login(String mmbId, String mmbPassword);

	int resign(String mmbId);
}
