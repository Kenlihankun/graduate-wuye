<template>
  <div class="navbar">

    <!-- 修改密码的弹框 -->
    <sys-dialog
      :title="dialog.title"
      :height="dialog.height"
      :width="dialog.width"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template slot="content">
        <el-form
          :model="params"
          ref="changeForm"
          :rules="rules"
          label-width="80px"
          :inline="true"
          size="small"
        >
          <el-form-item prop="oldPassword" label="旧密码">
            <el-input v-model="params.oldPassword"></el-input>
          </el-form-item>
          <el-form-item prop="newPassword" label="新密码">
            <el-input v-model="params.newPassword"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </sys-dialog>
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">

          <el-dropdown-item divided @click.native="resetPassword">
            <span style="display:block;">重置密码</span>
          </el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import SysDialog from "../../components/System/SysDialog";
import {resetPasswordApi} from "../../api/user";
import {getUserId} from "../../utils/auth";
export default {
  components: {
    Breadcrumb,
    Hamburger,
    SysDialog
  },
  data(){
    return {
      rules:{
        oldPassword:[{
          trigger:"change",
          require:true,
          message:"请填写旧密码"
        }],
        newPassword:[{
          trigger:"change",
          require:true,
          message:"请填写旧密码"
        }]
      },
      dialog:{
        title:"修改密码",
        height:150,
        width:630,
        visible:false
      },
      params:{
        userId:getUserId(),
        oldPassword:'',
        newPassword:''
      }
    }
  }
  ,
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    onConfirm() {
      this.$refs.changeForm.validate(async (valid) => {
        if (valid) {
          let res = await resetPasswordApi(this.params);
          if (res && res.code === 200) {
            this.dialog.visible = false;
            await this.$store.dispatch("user/logout");
            this.$router.push(`/login?redirect=${this.$route.fullPath}`);
          }else {
            this.$message.error(res.msg)
          }
        }
      });
    },
    onClose() {
      this.dialog.visible = false;
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      let confirm = await this.$myconfirm("确定退出登录吗?");
      if (confirm) {
        await this.$store.dispatch("user/logout");
        this.$router.push(`/login?redirect=${this.$route.fullPath}`);
      }
    },
    //重置密码
    async resetPassword() {
      //信息提示
        this.dialog.visible = true;
    },
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
