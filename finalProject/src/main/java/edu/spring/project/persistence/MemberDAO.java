package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO vo);

	List<MemberVO> select();

	// detailGET&&updateGET && idCheck&&login
	MemberVO selectOne(String mmbId);

	// member admin listGET
	List<MemberVO> select(String searchText);

	int update(MemberVO vo);

	String idCheck(String mmbId);

	MemberVO login(String mmbId, String mmbPassword);

	int resign(String mmbId);

	// 브랜치 삭제시 해당하는 선호지점을 가져와서 replace
	int replaceBrcIdDeleted(int deleteBrcId, int minBrcId);
}
