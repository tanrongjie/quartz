package com.trj.quartz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.entity
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-03 17:15
 * @Description:  定时任务实体类
 */
@Entity
@Table(name = "scheduler")
public class SchedulerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_class")
    private String jobClass;

    @Column(name = "job_status")
    private Integer jobStatus;

    @Column(name = "cron_expr")
    private String cronExpr;

    @Column(name = "describe")
    private String describe;

    @Column(name = "create_datetime")
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpr() {
        return cronExpr;
    }

    public void setCronExpr(String cronExpr) {
        this.cronExpr = cronExpr;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SchedulerEntity{" +
                "id=" + id +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobClass='" + jobClass + '\'' +
                ", jobStatus=" + jobStatus +
                ", cronExpr='" + cronExpr + '\'' +
                ", describe='" + describe + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
