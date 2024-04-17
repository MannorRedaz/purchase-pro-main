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
              <span class="sender">学院管理员 - {{ dataForm.contract_name }}</span>
            </div>
            <div class="name-role">
              <span class="sender">{{ academy1 }}管理员</span>
            </div>
            <el-divider></el-divider>
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
              <el-form label-width="80px" v-model="dataForm" size="small" label-position="right">
                <el-form-item label="用户昵称">
                  <el-input auto-complete="off" v-model="dataForm.contract_name"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                  <el-input auto-complete="off" v-model="dataForm.tel"></el-input>
                </el-form-item>

                <el-form-item label="银行账户">
                  <el-input maxlength="18" v-model="dataForm.company_name"></el-input>
                </el-form-item>
                <el-form-item label="旧密码">
                  <el-input maxlength="18" v-model="dataForm.password"></el-input>
                </el-form-item>
                <el-form-item label="新密码">
                  <el-input maxlength="18"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="primary" @click="setBasicInfo">提交</el-button>
                <el-button size="mini" type="warning">关闭</el-button>
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
      dataForm: {
        id: "1",
        contract_name: "张院长",
        tel: "173567777777",
        company_name: "http://www.baidu.com",
        sid: "1",
        cid: "1",
        name: "nihao",
        pwd: "123",
        account: "4234325",
        company_place: "42135",
        access: "1",
      },
      academyList: [],
      academy1: "",
    };
  },
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    this.dataForm = data;
    this.inital();
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
    async getAcademyList() {
      // console.log(1);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      // console.log(res);
      if (res.success) {
        this.academyList = arry;
      } else {
        // console.log("academys请求失败！");
      }
    },
    async inital() {
      await this.getAcademyList();
      for (let i = 0; i < this.academyList.length; i++) {
        if (this.dataForm.cid == this.academyList[i].cid) {
          this.academy1 = this.academyList[i].academy_name;
        }
      }
    },
    async setBasicInfo() {
      // console.log(userinfo)
      console.log(this.dataForm);
      const { data: res } = await this.$http.post(
        `president/infoUpdate`,
        this.dataForm
      );
      // console.log(res);
      if (!res.success) {
        return this.$message.error("更新用户状态失败！");
      }
      window.sessionStorage.setItem("data", JSON.stringify(this.dataForm));
      this.$message.success("更新用户状态成功！");
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
