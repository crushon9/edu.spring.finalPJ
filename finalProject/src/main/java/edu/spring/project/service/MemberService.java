package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MemberVO;

//CRUD (Create, Read, Update, Delete)
public interface MemberService {

	int create(MemberVO vo);

	List<MemberVO> read();

	MemberVO read(String mmbId);

	int update(MemberVO vo);

	int delete(String mmbId);

	MemberVO login(String mmbId, String mmbPassword);
}
