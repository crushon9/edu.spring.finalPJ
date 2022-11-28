package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.BranchVO;

//CRUD (Create, Read, Update, Delete)
public interface BranchService {

	int create(BranchVO vo);

	// read all
	List<BranchVO> read();

	// selectOne data
	BranchVO readOne(int brcId);

	// search by brcArea
	List<BranchVO> readBrcArea(int brcArea);
	
	// search by brcName
	List<BranchVO> readBrcName(String brcName);
	
	int update(BranchVO vo);

	int delete(int brcId);

}
