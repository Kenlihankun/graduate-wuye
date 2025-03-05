package com.cy.aliPay.Enum;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
    success(1, "成功"),
    failed(2, "失败"),
    process(3,"支付中")
    ;


    private final Integer code;

    private final String description;

    PaymentStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
