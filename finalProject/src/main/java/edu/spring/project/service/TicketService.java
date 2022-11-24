package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.TicketVO;

public interface TicketService {
	
	int create(TicketVO vo);
	
	List<TicketVO> read(int scdId);
	
	List<TicketVO> read(String mmbId);

	int delete(TicketVO vo);
}
