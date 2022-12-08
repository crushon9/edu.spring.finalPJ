package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {
	int insert(MovieVO vo);

	MovieVO select(int mvId);
	
	// 관리자 기본검색
	List<MovieVO> selectAll();

	// 관리자 search by period
	List<MovieVO> selectPeriod(String inputDateStarted, String inputDateEnded);
	
	// 관리자 search by String(keyword)
	List<MovieVO> selectSearch(String search);
	
	// 유저 order by ticketSales
	List<MovieVO> selectOrderTicketToday();

	// 유저 order by ReviewAvg
	List<MovieVO> selectOrderReviewToday();
	
	// 유저 search by String(keyword)
	List<MovieVO> selectSearchToday(String search);

	// search by date
	List<MovieVO> selectDate(String inputDate);

	int update(MovieVO vo);

	int delete(int mvId);

	// update rvRating
	int updateRating(int amount, int rvRating, int mvId);

	// rvRatingAvg(ratingTotal / ratingCnt)
	float selectRatingAvg(int mvId);

	// update TicketSales(int)
	int updateTicketSales(int amount, int mvId);

	// VO 멤버변수에는 없지만, 각 영화마다 예매율을 구하기 위함
	int selectTicketSalesTotal();

}
