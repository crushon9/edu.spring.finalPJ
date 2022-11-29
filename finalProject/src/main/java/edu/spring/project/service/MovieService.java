package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);

	MovieVO read(int mvId);

	// order by ticketSales
	List<MovieVO> readOrderTicket();

	// order by ReviewAvg
	List<MovieVO> readOrderReview();

	// search by period
	List<MovieVO> readPeriod(String inputDateStarted, String inputDateEnded);

	// search by date
	List<MovieVO> readDate(String inputDate);

	// search by String
	List<MovieVO> readSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	float readRatingAvg(int mvId);

	int readMvTicketSalesTotal();

}
