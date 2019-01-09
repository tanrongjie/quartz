package com.trj.quartz.dao;

import com.trj.quartz.entity.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.dao
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-03 17:45
 * @Description:
 */
@Repository
@Transactional
public interface SchedulerDao extends JpaRepository<SchedulerEntity, Integer> {

    @Query(value = " from SchedulerEntity")
    List<SchedulerEntity> queryAll();

    /**
     * 查询单条
     * @param id
     * @return
     */
    @Query(value = " from SchedulerEntity where id =:id")
    SchedulerEntity queryById(@Param("id") Integer id);

    /**
     * 更新任务状态
     * @param id
     * @param jobStatus
     * @return
     */
    @Query(value = "update SchedulerEntity set job_status = :jobStatus where id = :id")
    @Modifying
    Integer updateScheduler(@Param("id") Integer id, @Param("jobStatus") Integer jobStatus);
}
