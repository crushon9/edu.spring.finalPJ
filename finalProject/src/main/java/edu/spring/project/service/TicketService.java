package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.TicketVO;

public interface TicketService {
	
	int create(TicketVO vo);
	
	List<TicketVO> readScdId(int scdId);
}
