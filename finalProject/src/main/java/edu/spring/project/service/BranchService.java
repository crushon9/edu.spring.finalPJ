package edu.spring.project.service;

import java.util.List;

import edu.spring.project.domain.BranchVO;

//CRUD (Create, Read, Update, Delete)
public interface BranchService {

	int create(BranchVO vo);

	List<BranchVO> read();

	BranchVO read(String brcId);

	int update(BranchVO vo);

	int delete(String brcId);

}
