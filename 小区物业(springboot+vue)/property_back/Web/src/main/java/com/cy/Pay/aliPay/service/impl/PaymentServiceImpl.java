package com.cy.Pay.aliPay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.Pay.aliPay.entity.PaymentPO;
import com.cy.Pay.aliPay.service.PaymentService;
import com.cy.Pay.aliPay.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, PaymentPO> implements PaymentService {

    @Override
    public void insertData(PaymentPO paymentPO){
        baseMapper.insert(paymentPO);
    }
}
