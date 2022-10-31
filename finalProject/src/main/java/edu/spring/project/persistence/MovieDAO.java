package edu.spring.project.persistence;

import java.util.Date;
import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {

	int insert(MovieVO vo);

	List<MovieVO> select();

	MovieVO select(int mvId);

	int update(MovieVO vo);

	int delete(int mvId);

	// select_list_by_period
	MovieVO select(Date inputDateStarted, Date inputDateEnded);
	
	// select_list_by_date
	
}
