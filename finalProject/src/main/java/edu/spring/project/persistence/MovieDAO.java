package edu.spring.project.persistence;

import java.util.List;
import edu.spring.project.domain.MovieVO;

public interface MovieDAO {

	int insert(MovieVO vo);

	MovieVO select(int mvId);

	// ������ ���� ����
	List<MovieVO> selectTs();

	// �������� ����
	List<MovieVO> selectRa();

	// �Ⱓ�˻�
	List<MovieVO> select(String inputDateStarted, String inputDateEnded);

	// ��¥�˻�
	List<MovieVO> select(String inputDate);

	// ���ڿ��˻�
	List<MovieVO> selectSearch(String search);

	int update(MovieVO vo);

	int delete(int mvId);

	// ���� ���� _ ��ȭ ���� ���� 
	int updateRating(int amount, int rvRating, int mvId);

	// ����� ������ ����Ǹ�, ������ ��������
	float selectRatingAvg(int mvId);
	
	// Ƽ�� ���� _ ��ȭ ���ż� ����
	int updateTicketSales(int amount, int mvId);

}
