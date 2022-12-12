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

	@Override // 유저는 현재시점기준 과거 스케줄을 출력하면 안됨
	public List<ScheduleVO> readUser(int mvId, int brcId, String scdDate) {
		logger.info("read() call : mvId = " + mvId + ", brcId = " + brcId + ", scdDate = " + scdDate);
		// now : 메소드 호출된 현재 시간을 DB에 저장된 상영스케줄 인덱스로 변환한 숫자
		int now = TimeCompareUtil.nowConvertToScdIndex();
		// today : 오늘 날짜 스트링
		String today = TimeCompareUtil.today();
		// 날짜가 선택됐을때
		if (!scdDate.equals("unselected")) {
			// 선택된 날짜가 오늘날짜인지 비교
			if (TimeCompareUtil.compareToToday(scdDate).equals("equals")) {
				logger.info(TimeCompareUtil.compareToToday(scdDate));
				return scheduleDao.selectUser(mvId, brcId, scdDate, today, now);
			} else {
			// 선택된 날짜가 오늘이 아니라면 notToday
				return scheduleDao.selectUser(mvId, brcId, scdDate, "notToday", now);
			}
		// 날짜가 선택되지 않았을때 (오늘현재시간 ~ 미래)
		} else {
			return scheduleDao.selectUser(mvId, brcId, scdDate, today, now);
		}
	}

	@Override // 관리자는 과거 스케줄을 출력해도 괜찮음
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
