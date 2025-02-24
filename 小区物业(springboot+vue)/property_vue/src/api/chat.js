import http from '@/utils/http'
// 获取聊天响应
export async function getChat(param) {
  return http.get('/api/chat/get', {  param }); // 通过 params 传递参数
}
