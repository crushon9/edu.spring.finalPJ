package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {

	int insert(MovieVO vo);

	MovieVO select(int mvId);

	// 예매율 기준 정렬
	List<MovieVO> selectTs();

	// 평점기준 정렬
	List<MovieVO> selectRa();

	// 기간검색
	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	// 날짜검색
	List<MovieVO> select(String inputDate);

	// 문자열검색
	List<MovieVO> selectSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	// 리뷰 결합 _ 영화 평점 변경 
	int updateRating(int amount, int rvRating, int mvId);

	// 리뷰로 평점이 변경되면, 평점만 가져오기
	float selectRatingAvg(int mvId);
	
	// 티켓 결합 _ 영화 예매수 변경
	int updateTicketSales(int amount, int mvId);

}
