package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO vo);

	List<MemberVO> select();

	MemberVO selectOne(String mmbId);

	int update(MemberVO vo);

	int delete(String mmbId);

	MemberVO login(String mmbId, String mmbPassword);
	
	// mmbId·Î °Ë»ö
	List<MemberVO> select(String mmbId);
}
