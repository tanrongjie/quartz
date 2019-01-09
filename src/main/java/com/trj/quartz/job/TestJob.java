package com.trj.quartz.job;

import com.trj.quartz.utils.TypeConversion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.Serializable;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.job
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-04 10:42
 * @Description:
 */
public class TestJob implements Job, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("任务执行成功,时间:" + TypeConversion.getNowTimeStr());
    }
}
