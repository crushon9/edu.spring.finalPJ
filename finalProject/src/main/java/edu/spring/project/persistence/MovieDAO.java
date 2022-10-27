package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {

	int insert(MovieVO vo);

	List<MovieVO> select();

	MovieVO select(String mvId);

	int update(MovieVO vo);

	int delete(String mvId);

}
