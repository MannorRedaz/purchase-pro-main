<template>
  <div>
    <el-row :gutter="20" style="margin-top: 10px">
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>个人中心</span>
            </div>
            <div class="name-role">
              <span class="sender"
                >超级管理员 - {{ dataForm.contract_name }}</span
              >
            </div>

            <el-divider></el-divider>
            <div class="personal-relation">
              <div class="relation-item">
                手机号:
                <div style="float: right; padding-right: 20px">
                  {{ dataForm.tel }}
                </div>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>基本资料</span>
            </div>
            <div>
              <el-form
                label-width="80px"
                v-model="dataForm"
                size="small"
                label-position="right"
              >
                <el-form-item label="用户昵称" prop="name">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.name"
                  ></el-input>
                </el-form-item>
                <el-form-item label="联系人姓名" prop="contract_name">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.contract_name"
                  ></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="tel">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.tel"
                  ></el-input>
                </el-form-item>

                <el-form-item label="旧密码">
                  <el-input maxlength="18" v-model="loginForm.pwd"></el-input>
                </el-form-item>

                <el-form-item label="新密码">
                  <el-input maxlength="18" v-model="newPwd"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="primary" @click="setBasicInfo()"
                  >提交修改</el-button
                >
                <el-button size="mini" type="warning">取消</el-button>
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        pwd: "",
        name: "",
      },
      newPwd: "",
      dataForm: {
        // id: "",
        // contract_name: "超级管理员",
        // tel: "173567777777",
        // company_name: "http://www.baidu.com",
        // sid: "",
        // cid: "",
        // name: "",f
        // pwd: "",
        // account: "",
        // company_place: "",
        // access: "",
      },
    };
  },
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem("data"));
    this.dataForm = data;
    // this.dataForm.id = data.id;
    // this.dataForm.contract_name = data.contract_name;
    // this.dataForm.tel = data.tel;
    // console.log(this.dataForm);
    // this.dataForm.company_name = data.company_name;
    //  this.dataForm.sid=data.sid,
    //     this.dataForm.cid=,
    //     this.dataForm.name: "",
    //     this.dataForm.pwd: "",
    //     this.dataForm.account: "",
    //     this.dataForm.company_place: "",
    //     this.dataForm.access: "",
  },
  methods: {
    async setBasicInfo() {
      if (this.loginForm.pwd != null && this.loginForm.pwd != "") {
        const data = JSON.parse(window.sessionStorage.getItem("data"));
        this.loginForm.name = data.name;
        const { data: res1 } = await this.$http.post(
          "administerLogin",
          this.loginForm
        );
        // console.log(userinfo)
        if (res1.success) {
          this.dataForm.pwd = this.newPwd;
          const { data: res } = await this.$http.post(
            `supper/infoUpdate`,
            this.dataForm
          );
          if (!res.success) {
            this.$message.error("更新用户状态失败！");
          } else {
            this.$message.success("更新用户状态成功！");
            this.$router.push("/");
            window.sessionStorage.setItem("data", null);
          }
        } else {
          return this.$message.error("更新用户状态失败，输入密码有误");
        }
      } else {

        this.$message.warning("密码不能为空");
      }
    },
  },
};
</script>

<style lang="less" scoped>
//卡片样式
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
}
//文本样式区
.name-role {
  font-size: 16px;
  padding: 5px;
  text-align: center;
}
.sender {
  text-align: center;
}
.registe-info {
  text-align: center;
  padding-top: 10px;
}
.personal-relation {
  font-size: 16px;
  padding: 0px 5px 15px;
  margin-right: 1px;
  width: 100%;
}

.relation-item {
  padding: 12px;
}
.dialog-footer {
  padding-top: 10px;
  padding-left: 10%;
}
//布局样式区
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
