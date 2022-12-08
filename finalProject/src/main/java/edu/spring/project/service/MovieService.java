package edu.spring.project.service;

import java.util.List;
import edu.spring.project.domain.MovieVO;

//CRUD (Create, Read, Update, Delete)
public interface MovieService {

	int create(MovieVO vo);

	MovieVO read(int mvId);

	// 관리자 기본검색
	List<MovieVO> readAdmin();

	// 관리자 search by String(keyword)
	List<MovieVO> readAdminSearch(String search);

	// 관리자 search by period
	List<MovieVO> readAdminPeriod(String inputDateStarted, String inputDateEnded);

	// 유저  OrderTicket
	List<MovieVO> readUserOrderTicket();

	// 유저 OrderReview
	List<MovieVO> readUserOrderReview();

	// 유저 search by String(keyword)
	List<MovieVO> readUserSearch(String search);

	// search by date
	List<MovieVO> readDate(String inputDate);

	int update(MovieVO vo);

	int delete(int mvId);

	float readRatingAvg(int mvId);

	// 각 영화마다 예매율을 구하기 위함
	int readMvTicketSalesTotal();

}
