package com.cy.summary.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.chat.deepseek.service.impl.OpenAIService;
import com.cy.complaintManagement.userComplaint.entity.UserComplaint;
import com.cy.complaintManagement.userComplaint.mapper.UserComplaintMapper;
import com.cy.feeManagement.FeePark.entity.FeePark;
import com.cy.feeManagement.FeePark.mapper.FeeParkMapper;
import com.cy.feeManagement.FeePower.entity.FeePower;
import com.cy.feeManagement.FeePower.mapper.FeePowerMapper;
import com.cy.feeManagement.FeeWater.entity.FeeWater;
import com.cy.feeManagement.FeeWater.mapper.FeeWaterMapper;
import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.mapper.LiveUserMapper;
import com.cy.repairManagement.userRepair.entity.UserRepair;
import com.cy.repairManagement.userRepair.mapper.UserRepairMapper;
import com.cy.summary.entity.summary;
import com.cy.summary.summaryMapper.mapper.SummaryMapper;
import com.cy.summary.service.SummaryService;
import com.cy.systemManagement.user.entity.User;
import com.cy.systemManagement.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private SummaryMapper summaryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private OpenAIService openAIService;



    @Scheduled(cron = "0 0 0 1 * ?") // 每月的第一天的0点0分0秒执行
    public void executeMonthlyTask() throws IOException {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthDate = currentDate.minusMonths(1);

        // 定义格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        // 定义格式化器
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化上个月的日期
        String lastMonthFormatted = lastMonthDate.format(formatter);

        String newLastMonthFormatted = lastMonthDate.format(newFormatter);

        LambdaQueryWrapper<LiveUser> liveUserQueryWrapper = new LambdaQueryWrapper<>();
        liveUserQueryWrapper.eq(LiveUser::getStatus, "0"); // 使用字符串 "0" 表示_STATUS 状态
        List<LiveUser> liveUserList = liveUserMapper.selectList(liveUserQueryWrapper);
        if (!CollectionUtils.isEmpty(liveUserList)) {
            for (LiveUser liveUser : liveUserList) {
                QueryWrapper<FeePark> feeParkQueryWrapper = new QueryWrapper<>();

                feeParkQueryWrapper.eq("payParkMonth",lastMonthFormatted);
                List<FeePark> feeParks =  feeParkMapper.selectList(feeParkQueryWrapper);

                QueryWrapper<FeeWater> feeWaterQueryWrapper = new QueryWrapper<>();
                feeWaterQueryWrapper.eq("payWaterMonth",lastMonthFormatted);
                List<FeeWater> feeWaters = feeWaterMapper.selectList(feeWaterQueryWrapper);

                QueryWrapper<FeePower> feePowerQueryWrapper = new QueryWrapper<>();
                feePowerQueryWrapper.eq("payPowerMonth",lastMonthFormatted);
                List<FeePower> feePowers = feePowerMapper.selectList(feePowerQueryWrapper);

                if (CollectionUtils.isEmpty(feeParks) && CollectionUtils.isEmpty(feeWaters) && CollectionUtils.isEmpty(feePowers)) {
                    continue;
                }
                String result = openAIService.newGetResponse(liveUser.getLoginName(),CollectionUtils.isEmpty(feePowers) ? "" : feeParks.toString(),
                        CollectionUtils.isEmpty(feeParks) ? "" : feePowers.toString(),
                        CollectionUtils.isEmpty(feeWaters) ? "" : feeWaters.toString());
                summary summary = new summary();
                summary.setSummary(result);
                summary.setTime(lastMonthFormatted);
                summary.setUserId(liveUser.getUserId());
                summaryService.saveSummary(summary);
            }
        }

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getStatus, "0"); // 使用字符串 "0" 表示_STATUS 状态
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                QueryWrapper<UserRepair> repairQueryWrapper = new QueryWrapper<>();
                repairQueryWrapper.eq("commitTime",newLastMonthFormatted);
                List<UserRepair> userRepairs =  userRepairMapper.selectList(repairQueryWrapper);

                QueryWrapper<UserComplaint> userComplaintQueryWrapper = new QueryWrapper<>();
                userComplaintQueryWrapper.eq("createTime",newLastMonthFormatted);
                List<UserComplaint> userComplaints =  userComplaintMapper.selectList(userComplaintQueryWrapper);

                if (CollectionUtils.isEmpty(userComplaints) && CollectionUtils.isEmpty(userRepairs)) {
                    continue;
                }
                String result = openAIService.newGetResponse(user.getLoginName(),CollectionUtils.isEmpty(userRepairs) ? "" : userRepairs.toString(),
                        CollectionUtils.isEmpty(userComplaints) ? "" : userComplaints.toString(),null);
                summary summary = new summary();
                summary.setSummary(result);
                summary.setTime(lastMonthFormatted);
                summary.setUserId(user.getUserId());
                summaryService.saveSummary(summary);
            }
        }

        // 这里添加您的任务逻辑
        System.out.println("每月初任务已执行: " + Calendar.getInstance().getTime());
    }
}