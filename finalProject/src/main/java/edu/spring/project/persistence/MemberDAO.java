package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO vo);

	List<MemberVO> select();

	MemberVO select(String memberId);

	int update(MemberVO vo);

	int delete(String memberId);

}
