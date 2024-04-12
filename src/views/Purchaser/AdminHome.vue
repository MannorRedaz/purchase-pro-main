<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>实验室管理员</span>
      </div>
      <div class="date">
        <span id="budge">预算金额：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px" v-model="budge" />
        <span id="count">已使用金额：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px" v-model="count" />
        <span id="balance">余额：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px" v-model="balance" />
      </div>
      <div class="tools">
        <el-menu class="user" background-color="skyblue" active-text-color="#ffd04b" router>
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
          authName: "公告管理",
          id: 127,
          ico: "el-icon-menu",
          children: [
            {
              authName: "公告栏",
              id: 128,
              path: "purchaser/board",
              ico: "el-icon-location",
            },
            {
              authName: "公告管理",
              id: 124,
              path: "purchaser/boardManage",
              ico: "el-icon-location",
            },
          ],
        },
        {
          authName: "采购信息管理",
          id: 12,
          ico: "el-icon-menu",
          children: [
            {
              authName: "添加采购",
              id: 123,
              path: "purchaser/addPurchase",
              ico: "el-icon-location",
            },
            {
              authName: "历史查看",
              id: 124,
              path: "purchaser/purchaseList",
              ico: "el-icon-location",
            },
            {
              authName: "数据统计",
              id: 125,
              path: "purchaser/staticMain",
              ico: "el-icon-location",
            },
          ],
        },
        // {
        //   authName: '结果栏',
        //   id: 126,
        //   path: 'review1',
        //   ico: 'el-icon-menu'
        // },
        {
          authName: "个人信息管理",
          id: 125,
          path: "purchaser/basicInfo",
          ico: "el-icon-menu",
        },
      ],
      userName: "",
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
  created() {
    this.getBudget();
    this.getUsedMoney();
  },
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem("data"));
    this.userName = data.contract_name;
  },
  computed: {
    balance: function () {
      return this.budge - this.count;
    },
  },
  components: {
    baseDiv,
  },
  methods: {
    // 保存连接的激活状态
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
    async getBudget() {
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res } = await this.$http.get(
        "president/getBudget?sid=" + data.cid
      );
      this.budge = res.date[0].budget;
    },
    async getUsedMoney() {
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res } = await this.$http.get(
        "president/getUsedMoney?cid=" + data.cid
      );
      this.count = res.status;
    },
    async setting() { },
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
