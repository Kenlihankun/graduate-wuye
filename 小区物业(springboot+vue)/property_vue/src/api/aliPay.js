import http from '@/utils/http';
// 获取聊天响应
export async function aliPay(param) {
  return http.get('/alipay/pay', param);
}
