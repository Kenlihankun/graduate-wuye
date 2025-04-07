import http from '@/utils/http'
//获取总结列表
export async function getListApi(param){
  return http.get("/api/summary/get",param)
}

