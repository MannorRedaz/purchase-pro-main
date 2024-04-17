<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>实验室管理员</span>
      </div>
      <div class="date">
        <span id="budge">预算：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px;" v-model="budge" />
        <span id="count">已使用：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px" v-model="count" />
        <span id="balance">余额：</span>
        <input type="text" disabled="disabled" style="width: 70px; height: 15px" v-model="balance" />
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
          backgroundColor: "#2D9DFF",
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
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    this.userName = data.data[0].contract_name;
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
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "president/getBudget?sid=" + data.data[0].cid
      );
      this.budge = res.date[0].budget;
    },
    async getUsedMoney() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "president/getUsedMoney?cid=" + data.data[0].cid
      );
      this.count = res.status;
    },
    async setting() { },
    handleCommand(command) {
      if (command === 'setting') {
        this.$router.push("/supplier/basicInfo")
      } else if (command === 'logout') {
        window.sessionStorage.clear();
        this.$router.push("/")
      }
    }
  },
};
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
