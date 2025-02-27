import http from '@/utils/http';
// 获取聊天响应
export async function aliPay(amt,subject,body) {
  return http.get('/alipay/pay', {  amt,subject, body});
}
