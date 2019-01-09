package com.trj.quartz.controller;

import com.trj.quartz.service.SchedulerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @BelongsProject: quartz
 * @BelongsPackage: com.trj.quartz.controller
 * @Author: 谭荣杰
 * @CreateTime: 2018-12-03 21:52
 * @Description:
 */
@RestController
@RequestMapping(value = "scheduler")
public class SchedulerController {

    private final SchedulerService service;

    public SchedulerController(SchedulerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public ModelAndView getScbedulerList(ModelAndView modelAndView) {
        modelAndView.addObject("list", service.queryList());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public String updateScbeduler(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "jobStatus", required = false) Integer jobStatus) {
        if (null == id) {
            return "未获取到id";
        }

        if (null == jobStatus) {
            return "未获取到状态";
        }

        return service.updateScheduler(id, jobStatus) > 0 ? "更新成功" : "更新失败";
    }
}
