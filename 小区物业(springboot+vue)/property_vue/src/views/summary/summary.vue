<template>
  <el-main>
    <!-- 搜索 -->
    <el-form
      :model="params"
      ref="searchParm"
      label-width="80px"
      :inline="true"
      size="small"
    >
    </el-form>
    <!-- 表格 -->
    <el-table :height="tableHeight" :data="tableList" border stripe>
      <el-table-column align="center" label="用户ID" prop="userId"></el-table-column>
      <el-table-column align="center" label="月度总结" prop="summary"></el-table-column>
      <el-table-column align="center" label="总结月份" prop="time"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="params.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="params.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="params.total"
      background
    >
    </el-pagination>
  </el-main>
</template>

<script>
import { getListApi} from "@/api/summary";
import {getUserId} from "@/utils/auth";
export default {
  data() {
    return {
      //表格高度
      tableHeight: 0,
      //表格数据
      tableList: [],
      params: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        userId: "",
      },
    };
  },
  created() {
    this.getMyList();
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 210;
    });
  },
  methods: {
    //页数改变时触发
    currentChange(val) {
      this.params.currentPage = val;
      this.getMyList();
    },
    //页容量改变时触发
    sizeChange(val) {
      this.params.pageSize = val;
      this.getMyList();
    },

    //获取列表
    async getMyList() {
      this.params.userId = getUserId();
      let res = await getListApi(this.params);
      console.log("---------------",res)
      if (res && res.code === 200) {
        this.tableList = res.data.records;
      }else {
        this.$message.error(res.msg);
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
