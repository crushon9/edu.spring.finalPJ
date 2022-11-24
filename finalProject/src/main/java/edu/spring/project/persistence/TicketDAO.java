package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.TicketVO;

public interface TicketDAO {

	int insert(TicketVO vo);

	List<TicketVO> select(int scdId);
	
	List<TicketVO> select(String scdId);
	
	int delete(int tkId);
}
