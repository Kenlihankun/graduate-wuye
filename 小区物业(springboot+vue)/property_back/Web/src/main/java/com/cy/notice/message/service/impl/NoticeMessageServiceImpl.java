package com.cy.notice.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.notice.message.entity.NoticeMessagePO;
import com.cy.notice.message.mapper.NoticeMessageMapper;
import com.cy.notice.message.service.NoticeMessageService;
import org.springframework.stereotype.Service;

@Service
public class NoticeMessageServiceImpl extends ServiceImpl<NoticeMessageMapper, NoticeMessagePO> implements NoticeMessageService {
}
