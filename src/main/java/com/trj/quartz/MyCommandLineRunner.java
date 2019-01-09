package com.trj.quartz;

import com.trj.quartz.entity.SchedulerEntity;
import com.trj.quartz.service.SchedulerService;
import com.trj.quartz.utils.SchedulerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-04 11:40
 * @Description: 启动类,项目启动时,查询定时任务,并将启用中的定时任务启动
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final SchedulerService service;
    private final SchedulerUtil schedulerUtil;

    private final static Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

    public MyCommandLineRunner(SchedulerService service, SchedulerUtil schedulerUtil) {
        this.service = service;
        this.schedulerUtil = schedulerUtil;
    }

    @Override
    public void run(String... var1) {
        List<SchedulerEntity> list = service.queryList();
        for (SchedulerEntity entity : list) {
            if (null != entity.getJobStatus() && entity.getJobStatus().equals(1)) {
                try {
                    schedulerUtil.createJob(entity.getJobGroup(), entity.getJobName(), entity.getJobClass(), entity.getCronExpr());
                } catch (Exception e) {
                    logger.error("启动定时任务异常");
                }
            }
        }
    }
}
