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

	// VO 멤버변수에는 없지만 필요한 데이터라서 쿼리로만 가져옴
	int selectTicketSalesTotal();

}
