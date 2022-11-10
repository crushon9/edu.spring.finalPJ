package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {

	int insert(MovieVO vo);

	List<MovieVO> select();

	MovieVO select(int mvId);

	int update(MovieVO vo);

	int delete(int mvId);

	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	List<MovieVO> select(String inputDate);
}
