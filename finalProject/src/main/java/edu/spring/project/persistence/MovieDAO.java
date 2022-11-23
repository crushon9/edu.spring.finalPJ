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

}
