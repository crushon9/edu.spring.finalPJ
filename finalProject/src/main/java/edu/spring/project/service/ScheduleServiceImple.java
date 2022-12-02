package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.persistence.BranchDAO;
import edu.spring.project.persistence.MovieDAO;
import edu.spring.project.persistence.ScheduleDAO;

@Service
public class ScheduleServiceImple implements ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImple.class);
	@Autowired
	private ScheduleDAO scheduleDao;
	@Autowired
	private BranchDAO branchDao;
	@Autowired
	private MovieDAO movieDao;

	@Transactional
	@Override
	public int create(ScheduleVO vo) {
		logger.info("create() call");
		scheduleDao.insert(vo);
		// 스케줄 등록시 해당 브랜치와 무비 변경불가하게 업데이트
		branchDao.updateImmutableCheck(vo.getBrcId());
		movieDao.updateImmutableCheck(vo.getMvId());
		logger.info("updateImmutableCheck : brcId=" + vo.getBrcId() + ", mvId=" + vo.getMvId());
		return 1;
	}

	@Override
	public ScheduleVO read(int scdId) {
		logger.info("read() call : scdId = " + scdId);
		return scheduleDao.select(scdId);
	}

	@Override
	public List<ScheduleVO> read(int mvId, int brcId, String scdDate) {
		logger.info("read() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		return scheduleDao.select(mvId, brcId, scdDate);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() call : scdId = " + scdId);
		// 변경불가 상태일때 -2반환
		int immutable = scheduleDao.selectImmutableCheck(scdId);
		if (immutable != -2) {
			return scheduleDao.delete(scdId);
		} else {
			return immutable;
		}
	}
}
