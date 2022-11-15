package edu.spring.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.spring.project.domain.TicketVO;
import edu.spring.project.persistence.TicketDAO;

@Service
public class TicketServiceImple implements TicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImple.class);
	
	@Autowired
	private TicketDAO dao;

	@Override
	public int create(TicketVO vo) {
		logger.info("create() »£√‚");
		return dao.insert(vo);
	}
}
