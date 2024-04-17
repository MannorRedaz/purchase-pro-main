<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>供应商</span>
      </div>
      <div class="tools">
        <el-menu class="user" background-color="#2D9DFF" active-text-color="#2D9DFF" style="margin-top: 15px;" router>
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link" style=" color: #fff;margin-left: 9px;">
              <img src="@/assets/img/user.svg" style="margin-right: 9px; color: #fff;" width="20px">
              {{ userName }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="setting">设置</el-dropdown-item>
              <el-dropdown-item command="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu>

      </div>
    </div>
  </base-div>
</template>
<script>
import baseDiv from '../../components/Utils/Home'
import router from "@/router/index.js"
export default {
  data() {
    return {
      // 左侧菜单数据
      menulist: [
        {
          authName: '通知栏',
          id: 24,
          ico: 'el-icon-document',
          path: '',
          children: [
            {
              authName: '采购公告',
              id: 26,
              path: 'supplier/board',
              ico: 'el-icon-location'
            },
            {
              authName: '申请入围公告',
              id: 25,
              path: 'supplier/applyForlistBoard',
              ico: 'el-icon-location'
            },
          ]
        },
        {
          authName: '申请管理',
          id: 27,
          path: 'supplier/applyManage',
          ico: 'el-icon-menu'
        },
        {
          authName: '个人信息管理',
          id: 28,
          ico: 'el-icon-s-goods',
          path: '/',
          children: [
            {
              authName: '个人信息',
              id: 29,
              path: 'supplier/basicInfo',
              ico: 'el-icon-location'
            },
            {
              authName: '数据统计',
              id: 28,
              path: 'Static',
              ico: 'el-icon-location'
            },
            {
              authName: '入围申请记录',
              id: 30,
              path: 'application',
              ico: 'el-icon-location'
            }
          ]
        }
      ],
      userName: '',
      backgroundColor: [
        {
          border: "1px solid pink",
          height: "100%"
        },
        {
          backgroundColor: "#2D9DFF"
        },
        {
          backgroundColor: "#545c64"
        }
      ],
      // 被激活的连接地址
      activePath: ''
    }
  },
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem('data'));
    this.userName = data.contract_name;
  },
  components: {
    baseDiv
  },
  methods: {
    // 保存连接的激活状态
    saveNavState(activePath) {
      // console.log(this.activePath)
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    handleCommand(command) {
      if (command === 'setting') {
        this.$router.push("/supplier/basicInfo")
      } else if (command === 'logout') {
        window.sessionStorage.clear();
        this.$router.push("/")
      }
    }
  }
}
</script>
<style lang="less" scoped>
.logo {
  line-height: 60px;
  float: left;
  color: #fff;
}

input {
  border: none;
  outline: none;
  background-color: transparent;
  color: #fff;
}

.date {
  width: 500px;
  height: 100px;
  position: absolute;
  left: 100px;
  top: 20px;
  margin-left: 500px;
  color: #fff;

}

.tools {
  width: 650px;
  float: right;
  padding-right: 50px;

  .money {
    float: left;

    .el-form-item {
      float: left;
      width: 180px;
      margin: 10px 10px 0;
    }
  }

  .user {
    width: 200px;
    float: right;
    text-align: center;

    .el-menu-item {
      z-index: 100;
    }
  }
}
</style>
