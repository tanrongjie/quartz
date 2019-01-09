package com.trj.quartz.service.impl;

import com.trj.quartz.dao.SchedulerDao;
import com.trj.quartz.entity.SchedulerEntity;
import com.trj.quartz.service.SchedulerService;
import com.trj.quartz.utils.SchedulerUtil;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.service.impl
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-03 21:49
 * @Description:
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerDao schedulerDao;
    private final SchedulerUtil schedulerUtil;

    private final static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    public SchedulerServiceImpl(SchedulerDao schedulerDao, SchedulerUtil schedulerUtil) {
        this.schedulerDao = schedulerDao;
        this.schedulerUtil = schedulerUtil;
    }

    public List<SchedulerEntity> queryList() {
        return schedulerDao.queryAll();
    }

    public Integer updateScheduler(Integer id, Integer jobStatus) {
        if (null == id) {
            return 0;
        }

        if (null == jobStatus) {
            return 0;
        }

        Integer result = schedulerDao.updateScheduler(id, jobStatus);
        if (null != result && 0 < result) {
            SchedulerEntity entity = schedulerDao.queryById(id);
            if (jobStatus.equals(1)) {
                try {
                    schedulerUtil.createJob(entity.getJobGroup(), entity.getJobName(), entity.getJobClass(), entity.getCronExpr());
                } catch (Exception e) {
                    logger.error("任务启动异常:" + e.getMessage());
                }
            } else if (jobStatus.equals(0)) {
                    try {
                        schedulerUtil.deleteJob(entity.getJobName(), entity.getJobGroup());
                    } catch (SchedulerException e) {
                        logger.error("任务关闭异常:" + e.getMessage());
                    }
            }
        }

        return result;
    }
}
