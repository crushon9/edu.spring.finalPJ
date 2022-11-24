package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.BranchVO;

//CRUD (Create, Read, Update, Delete)
public interface BranchService {

	int create(BranchVO vo);

	// 전체 보기
	List<BranchVO> read();

	// brcId로 특정 지점만 보기
	BranchVO read(int brcId);

	int update(BranchVO vo);

	int delete(int brcId);

	// 지점 지역번호로 검색
	List<BranchVO> areaList(int brcArea);
	
	// 지점 이름으로 검색/ 무비문자열 검색 동일
	List<BranchVO> areaList(String brcName);

}
