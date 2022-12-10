package edu.spring.project.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import edu.spring.project.domain.ScheduleVO;
import edu.spring.project.persistence.ScheduleDAO;
import edu.spring.project.util.TimeCompareUtil;

@Service
public class ScheduleServiceImple implements ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImple.class);
	@Autowired
	private ScheduleDAO scheduleDao;

	@Override
	public int create(ScheduleVO vo) {
		logger.info("create() call");
		return scheduleDao.insert(vo);
	}

	@Override
	public ScheduleVO read(int scdId) {
		logger.info("read() call : scdId = " + scdId);
		return scheduleDao.select(scdId);
	}

	@Override
	public List<ScheduleVO> readUser(int mvId, int brcId, String scdDate) {
		logger.info("read() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		int now = TimeCompareUtil.nowConvertToScdIndex();
		String today = TimeCompareUtil.today();
		if (!scdDate.equals("unselected")) {
			// 오늘 날짜면 시간비교 쿼리로 데이터를 가져오고 아니라면 시간비교 없이 가져옴
			if (TimeCompareUtil.compareToToday(scdDate).equals("equals")) {
				logger.info(TimeCompareUtil.compareToToday(scdDate));
				return scheduleDao.selectUser(mvId, brcId, scdDate, "yes", today, now);
			} else {
				return scheduleDao.selectUser(mvId, brcId, scdDate, "no", today, now);
			}
		} else {
			return scheduleDao.selectUser(mvId, brcId, scdDate, "no", today, now);
		}
	}

	@Override
	public List<ScheduleVO> readAdmin(int mvId, int brcId, String scdDate) {
		logger.info("read() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		return scheduleDao.selectAdmin(mvId, brcId, scdDate);
	}

	@Override
	public int delete(int scdId) {
		logger.info("delete() call : scdId = " + scdId);
		try {
			return scheduleDao.delete(scdId);
		} catch (DataIntegrityViolationException sqle) {
			// 변경불가 상태일때 -2반환
			logger.debug(sqle.getMessage());
			return -2;
		} catch (Exception e) {
			return scheduleDao.delete(scdId);
		}
	}
}
