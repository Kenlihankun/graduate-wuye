package com.cy.Pay.aliPay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.Pay.aliPay.entity.PaymentPO;

public interface PaymentService  extends IService<PaymentPO> {
    void insertData(PaymentPO paymentPO);
}
