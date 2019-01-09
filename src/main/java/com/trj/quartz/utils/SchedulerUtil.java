package com.trj.quartz.utils;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.utils
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-04 11:05
 * @Description:
 */
@Component
public class SchedulerUtil {


    private final Scheduler scheduler;

    public SchedulerUtil(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 创建并启动定时任务
     * @param jobGroup  组名
     * @param jobName   任务名
     * @param jobClass  任务执行类
     * @param cron      时间表达式
     * @throws Exception
     */
    public void createJob(String jobGroup, String jobName, String jobClass, String cron) throws Exception {
        if (null == jobGroup || "".equals(jobGroup) || null == jobName || "".equals(jobName) || null == jobClass || "".equals(jobClass) || null == cron || "".equals(cron)) {
            return;
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        //任务触发
        Trigger checkExist = scheduler.getTrigger(triggerKey);
        if (checkExist == null) {
            JobDetail jobDetail;
            jobDetail = JobBuilder.newJob(newInstance(jobClass))
                    .requestRecovery(true)//当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
                    .withIdentity(jobName, jobGroup)
                    .build();
            jobDetail.getJobDataMap().put("jobName", jobName);
            jobDetail.getJobDataMap().put("jobGroup", jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName, jobGroup)
                    .withSchedule(cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires())
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    /**
     * 删除当前任务
     * @param jobName   任务名
     * @param jobGroup  组名
     */
    public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.deleteJob(key);
    }


    private Class<? extends Job> newInstance(String className) throws ClassNotFoundException {
        return (Class<? extends Job>) Class.forName(className);
    }
}
