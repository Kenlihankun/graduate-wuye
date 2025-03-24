package com.cy.summary.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cy.chat.deepseek.service.impl.OpenAIService;
import com.cy.complaintManagement.userComplaint.mapper.UserComplaintMapper;
import com.cy.feeManagement.FeePark.mapper.FeeParkMapper;
import com.cy.feeManagement.FeePower.mapper.FeePowerMapper;
import com.cy.feeManagement.FeeWater.mapper.FeeWaterMapper;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.mapper.LiveUserMapper;
import com.cy.repairManagement.userRepair.mapper.UserRepairMapper;
import com.cy.systemManagement.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;

@Component
public class MonthlyTask {

    @Autowired
    private LiveUserMapper liveUserMapper;

    @Autowired
    private FeeParkMapper feeParkMapper;

    @Autowired
    private FeePowerMapper feePowerMapper;

    @Autowired
    private FeeWaterMapper feeWaterMapper;

    @Autowired
    private UserRepairMapper userRepairMapper;

    @Autowired
    private UserComplaintMapper userComplaintMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OpenAIService openAIService;



    @Scheduled(cron = "0 0 0 1 * ?") // 每月的第一天的0点0分0秒执行
    public void executeMonthlyTask() {
        LambdaQueryWrapper<LiveUser> liveUserQueryWrapper = new LambdaQueryWrapper<>();
        liveUserQueryWrapper.eq(LiveUser::getStatus, "0"); // 使用字符串 "0" 表示_STATUS 状态
        List<LiveUser> liveUserList = liveUserMapper.selectList(liveUserQueryWrapper);
        if (!CollectionUtils.isEmpty(liveUserList)) {
            for (LiveUser liveUser : liveUserList) {

            }
        }

        // 这里添加您的任务逻辑
        System.out.println("每月初任务已执行: " + Calendar.getInstance().getTime());
    }
}