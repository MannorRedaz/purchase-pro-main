<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>供应商</span>
      </div>
      <div class="tools">
        <el-menu class="user" background-color="skyblue" active-text-color="#ffd04b" router>
          <el-submenu index="2">
            <template slot="title">{{ userName }}</template>
            <el-menu-item index="/">设置</el-menu-item>
            <el-menu-item index="/">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </div>
  </base-div>
</template>
<script>
import baseDiv from '../../components/Utils/Home'
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
          backgroundColor: "skyblue"
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
    //console.log(data);
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
    }
  }
}
</script>
<style lang="less" scoped>
.logo {
  line-height: 60px;
  float: left;
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
