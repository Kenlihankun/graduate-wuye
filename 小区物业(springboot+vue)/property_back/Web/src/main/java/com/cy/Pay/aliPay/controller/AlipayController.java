package com.cy.Pay.aliPay.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.cy.Pay.aliPay.Enum.PaymentStatusEnum;
import com.cy.Pay.aliPay.entity.PaymentPO;
import com.cy.Pay.aliPay.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/alipay")
@Slf4j
public class AlipayController {

    @Autowired
    private PaymentService paymentService;


    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtWyD99Tx6ZgYYsh9MB7x1dXhdjzXRhcWSxBwKv5nUsKazvRfMs4+Ng2sX5/JyvUgTmM2/ex1aRqaur3huR7jnLloiuTt2/gn7V2g+zd3G5+llR0SJL2wIBmmRI2AwP+6k+8XG6Nncl+0ETHbWS2WT5tIjucZbhBYt8obSmR6GM4hUMtUqY6E27II2CW4Lrzc6Z7iTN2h8JNXAz8+CzHXQ3b+Qo6hYLGM/OS4hv8LhZS5LWg/ZAOdT+z2iqcuZHfygdjCRvKNustIysGr2MzW6f7pltBbFPdKWqXs+Zt8CYGLhL4VmhkOWXy2seX5C2iotGadJ8NpJ2AWVl+961DwEwIDAQAB";

    private static final String appId = "9021000145613567";

    private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1bIP31PHpmBhiyH0wHvHV1eF2PNdGFxZLEHAq/mdSwprO9F8yzj42Daxfn8nK9SBOYzb97HVpGpq6veG5HuOcuWiK5O3b+CftXaD7N3cbn6WVHRIkvbAgGaZEjYDA/7qT7xcbo2dyX7QRMdtZLZZPm0iO5xluEFi3yhtKZHoYziFQy1SpjoTbsgjYJbguvNzpnuJM3aHwk1cDPz4LMddDdv5CjqFgsYz85LiG/wuFlLktaD9kA51P7PaKpy5kd/KB2MJG8o26y0jKwavYzNbp/umW0FsU90papez5m3wJgYuEvhWaGQ5ZfLax5fkLaKi0Zp0nw2knYBZWX73rUPATAgMBAAECggEBAKvroUMBMB1EXBeAFeG78T/J5lW9YeKxjhakBSc43YC3mSv0tyHV/24JlwcmzHrfvzWJH1uQ79Ihu+6qcXSTqO2jZUX5ms2QY4Wz7BEjhmNO7gJYAhF/D5/brovX6+L4cz4sm6E9P5JKzsBclpfzczUM2b1Rke+S3f+MLkBY5rsJP9GnN2rt6PxJnoJ208TnPtKwtkSmDhXEeW30cU2Yk8SHW/p0CEMwa04MjTlhf2U9NjHcAlNNjlJv8jto6GJX/T2QP7zgd4Vk3pkGWcvVBCAue7bKqKM/PuwCoAeGCTM12yioHDfsqWZRQDL7DGB7Cvvp4Vs1riDXdBzUVdunmIECgYEA8SIvCDu02mMAOx903twRf8o0bBju8vZ4bH9+2hJen2I0Zci0ZRqZo0ZNU9EJP3sact3dQs59NKGZFfnFfbAqDSLiqpO/jtO81vh4sQP9AZWl2gGIYQJBbNnu/yK6acHQROmMXDQMvuSeqVb3JASnx0PcQEbqt+sLYIgllt2plGsCgYEAwJvu3k9sbh1WcikexLJsCF0vABcgK64VLm/oflhZ/Uq1H1gd9Xm7hIvalkAr+ZZYyfVf96xg6E1AK7ThJaCMr5AKlRcV5FT7w2HQNOUKmTDrftNZuIYIAHpX+MdjU8zTBJevaYdAksh8ad7+ARGr07m6r258hCmLRD+RKuljvPkCgYBeenRPF4W+43ddsu+JulQo68Mqb4KAM83vRO0g6H2mmUXKc58q1gdIj+0QYCDtfIBabU+aM2mfS4m9sIWOv41aZEbHauhtP80HtYckZFmnW0kGZ/9VcxrLWYBo+uRrN4Mw3Qkfv00XJmB8HRTsSd6rFo2fqamhsjhc9Ds2J1egTQKBgEaqr3g3MbQw5V+bQKOwRrzG25Cv62i7RSA1hNqeNd4dw9hNLyi6UhdubHOF9dxr0gW/Fke7og55EVs30QdKIXPkaE2NG8YbfAeSYp6Vjk7oSO7OQnPWESeDu7UWCpZbZuyfbodMTV/NWQBLBWyE/EMBOMJWyi5/Q451uCBDYwmZAoGAF0KRWwr/n5oXSYTorUooHnwt8lDu79j8Ivivm6WYbnrhyHDPFlMwRA/VLC/KS1OiVRLcWGwdi7MTxG7fjvHjnn0GAezP6WUCaQPqN4xBKHlKzAjd/1gUiDz9pIG2Js6MzLF0c1fMNYaYeUq5ke5lUiASmtZHO2pp6MHJV9i7F8Q=";


    @RequestMapping("/pay")
    public String pay(@RequestParam(value = "amt") BigDecimal amt,
                      @RequestParam(value = "subject") String subject,
                      @RequestParam(value = "body") BigDecimal body) {
        log.info("支付宝支付入参：{}", amt + "," + subject + "," + body);
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        String format = "json";
        String charset = "utf-8";
        String signType = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey, format, charset,publicKey, signType);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 设置请求参数
        request.setReturnUrl(""); // 设置 支付宝同步返回支付结果url,可以以此结果做后续业务逻辑处理 可为空
        request.setNotifyUrl(""); // 设置 异步通知地址 可为空
        // 设置网页支付参数
        // outTradeNo 格式为yyyyMMddHHmmsss
        request.setBizContent("{\"out_trade_no\":\""+ new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()) +"\","
                + "\"total_amount\":\""+ amt +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ "5m" +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if(response.isSuccess()){
                log.info("【 Ali pay调用成功 】");
            } else {
                log.info("【 Ali pay调用失败 】");
            }
            log.info("支付宝支付结束，响应为：{}", JSON.toJSON(response));
            // 就是orderString 可以直接给客户端请求，无需再做处理。
            PaymentPO paymentPO = new PaymentPO();
            paymentPO.setAmount(amt);
            paymentPO.setStatus(PaymentStatusEnum.process.getCode());
            paymentService.insertData(paymentPO);
            return response.getBody();
        } catch (AlipayApiException e) {
            log.error("【 Ali pay 异常 】", e);
        }
        return "fail";
    }

}
