package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MemberVO;

public interface MypageDAO {

//	int insert(MemberVO vo);

	// 전체 회원정보 조회
	List<MemberVO> select();

	MemberVO select(String mmbId);

	int update(MemberVO vo);

	int delete(String mmbId);
	
	// 예매내역 조회
	
	// 후기보기
	
}
