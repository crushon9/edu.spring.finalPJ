package edu.spring.project.persistence;

import java.util.List;

import edu.spring.project.domain.TicketVO;

public interface TicketDAO {

	int insert(TicketVO vo);

	List<TicketVO> select();

	List<TicketVO> select(int scdId);

	List<TicketVO> select(String scdId);
	
	// search by String
	List<TicketVO> selectSearch(String search);
	
	int delete(int tkId);

	// 리뷰 작성 위해서 티켓 구매 여부 체크
	List<Integer> buyCheck(String mmbId, int mvId);
}
