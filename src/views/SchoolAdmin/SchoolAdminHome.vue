<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>学校管理员</span>
      </div>
      <div class="tools">
        <el-menu
          class="user"
          background-color="skyblue"
          active-text-color="#ffd04b"
          router
        >
          <el-submenu index="4">
            <template slot="title">{{ userName }}</template>
            <el-menu-item @click="setting">设置</el-menu-item>
            <el-menu-item index="/">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </div>
  </base-div>
</template>
<script>
import baseDiv from "../../components/Utils/Home";
export default {
  data() {
    return {
      // 左侧菜单数据
      menulist: [
        {
          authName: "学校公告",
          id: 10,
          ico: "el-icon-document",
          path: "/",
          children: [
            {
              authName: "公告栏",
              id: 11,
              path: "schoolAdmin/notice",
              ico: "el-icon-location",
            },
            {
              authName: "采购公告管理",
              id: 12,
              path: "schoolAdmin/noticeManage",
              ico: "el-icon-location",
            },
            {
              authName: "入围公告管理",
              id: 13,
              path: "schoolAdmin/applyBoardManage",
              ico: "el-icon-location",
            },
          ],
        },
        {
          authName: "账号管理",
          id: 13,
          ico: "el-icon-document",
          path: "/",
          children: [
            {
              authName: "学院账号",
              id: 14,
              path: "schoolAdmin/presidentUserManage",
              ico: "el-icon-location",
            },
            {
              authName: "实验室管理员账号",
              id: 15,
              path: "schoolAdmin/purchaserUserManage",
              ico: "el-icon-location",
            },
          ],
        },
        {
          authName: "审核管理",
          id: 16,
          ico: "el-icon-document",
          path: "",
          children: [
            {
              authName: "入围审核",
              id: 17,
              path: "schoolAdmin/applyShortListManage",
              ico: "el-icon-location",
            },
            {
              authName: "采购审核",
              id: 18,
              path: "schoolAdmin/purchaseManage",
              ico: "el-icon-location",
            },
          ],
        },
        {
          authName: "订单管理",
          id: 16,
          ico: "el-icon-document",
          path: "",
          children: [
            {
              authName: "采购详情",
              id: 19,
              ico: "el-icon-document",
              path: "schoolAdmin/purchaseListManage",
            },
            {
              authName: "数据统计",
              id: 125,
              path: "schoolAdmin/staticMain",
              ico: "el-icon-location",
            },
          ],
        },

        {
          authName: "个人信息管理",
          id: 21,
          ico: "el-icon-document",
          path: "schoolAdmin/schoolAdminBasicInfo",
        },
      ],
      userName: "未知账号",
      backgroundColor: [
        {
          border: "1px solid pink",
          height: "100%",
        },
        {
          backgroundColor: "skyblue",
        },
        {
          backgroundColor: "#545c64",
        },
      ],
      // 被激活的连接地址
      activePath: "",
      budge: "100",
      count: "10",
    };
  },
  created() {},
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem("data"));
    this.userName = data.contract_name;
  },
  computed: {},
  components: {
    baseDiv,
  },
  methods: {
    // 保存连接的激活状态
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
  },
};
</script>
<style lang="less" scoped>
.logo {
  line-height: 60px;
  float: left;
}
.date {
  width: 500px;
  height: 100px;
  position: absolute;
  left: 100px;
  top: 20px;
  margin-left: 500px;
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
