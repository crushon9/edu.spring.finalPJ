package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {
	int insert(MovieVO vo);

	MovieVO select(int mvId);

	// order by ticketSales
	List<MovieVO> selectOrderTicket();

	// order by ReviewAvg
	List<MovieVO> selectOrderReview();

	// search by period
	List<MovieVO> selectPeriod(String inputDateStarted, String inputDateEnded);

	// search by date
	List<MovieVO> selectDate(String inputDate);

	// search by String
	List<MovieVO> selectSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	// update rvRating
	int updateRating(int amount, int rvRating, int mvId);

	// rvRatingAvg(a/b)
	float selectRatingAvg(int mvId);
	
	// update TicketSales(int)
	int updateTicketSales(int amount, int mvId);

}
