package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MemberVO;

public interface MypageDAO {

//	int insert(MemberVO vo);

	// ��ü ȸ������ ��ȸ
	List<MemberVO> select();

	MemberVO select(String mmbId);

	int update(MemberVO vo);

	int delete(String mmbId);
	
	// ���ų��� ��ȸ
	
	// �ı⺸��
	
}
