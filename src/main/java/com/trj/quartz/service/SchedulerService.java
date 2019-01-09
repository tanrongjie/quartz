package com.trj.quartz.service;

import com.trj.quartz.entity.SchedulerEntity;

import java.util.List;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.service
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-03 20:58
 * @Description:
 */
public interface SchedulerService {

    /**
     * 获取定时任务
     * @return
     */
    List<SchedulerEntity> queryList();

    /**
     * 更新状态
     * @param id
     * @param jobStatus
     * @return
     */
    Integer updateScheduler(Integer id, Integer jobStatus);
}
