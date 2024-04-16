<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <!--输入物品名称-->
      <div class="product-name-box">
        <div class="product-name">学院名称:</div>
        <el-input v-model="academyName" placeholder="请输入学院名称进行查询">
        </el-input>
      </div>
      <!--搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="searchByAcademyName">搜索</el-button>
        <el-button type="primary" @click="dialogFormVisible = true"
          >添加学院管理员</el-button
        >
      </div>
      <div class="btnAdd">
        <!-- Form  button 的type属性primary和text  -->
        <!-- <el-button type="primary" @click="dialogFormVisible = true"
          >添加学院管理员</el-button
        > -->
        <!-- @click="addSchAdmin" -->
        <el-dialog title="添加学院管理员" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="学院名" :label-width="formLabelWidth">
              <el-input
                v-model="form.academy_name"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="管理员姓名" :label-width="formLabelWidth">
              <el-input
                v-model="form.contract_name"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="联系电话" :label-width="formLabelWidth">
              <el-input v-model="form.tel" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="管理员账户" :label-width="formLabelWidth">
              <el-input v-model="form.count" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学院预算" :label-width="formLabelWidth">
              <el-input v-model="form.budget" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item
              label="学院最大单笔金额"
              :label-width="formLabelWidth"
            >
              <el-input
                v-model="form.maximum_order"
                autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialog(false)">取 消</el-button>
            <el-button type="primary" @click="dialog(true)">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table
        :data="tableList"
        :key="tableList.id"
        style="width: 100%; font-size: 15px"
        stripe
      >
        <el-table-column type="index"></el-table-column>
        <el-table-column label="学院名称" prop="academy_name">
        </el-table-column>
        <el-table-column label="账号" prop="name"> </el-table-column>
        <el-table-column label="联系人姓名" prop="contract_name">
        </el-table-column>
        <el-table-column label="联系人电话" prop="tel"> </el-table-column>
        <el-table-column label="重置密码">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="reset(scope.row.id)"
              >重置密码</el-button
            >
          </template>
        </el-table-column>
        <el-table-column label="删除">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="deleteAdmin(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pagesize"
        :page-sizes="[50]"
        layout="total,sizes, prev, pager, next"
        :total="tableList.length"
        >``
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagesize:5,
      academyName: "",
      currentPage: 0,
      date: "",
      tableList: [],
      presidentAdminList: [],
      academyList: [],
      //时间选择器的时间
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      //设置默认时间
      value1: [new Date(), new Date()],
      value2: "",
      //设置弹框
      dialogFormVisible: false,
      form: {
        academy_name: "学院名",
        contract_name: "shini",
        tel: "1584235035",
        count: "242364326435754725",
        name: "nihao",
        budget: "",
        maximum_order: "",
        sid: "",
      },
      formLabelWidth: "120px",
    };
  },
  created() {},
  mounted() {
    this.iniSearch();
    this.selectAcademy();
  },
  computed: {},
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    async dialog(val) {
      if (val) {
        if (this.form.name == null || this.form.name == "") {
          this.$message.warning("请输入用户名");
          return;
        }
        if (this.form.academy_name == null || this.form.academy_name == "") {
          this.$message.warning("请输入学院名称");
          return;
        }
        if (this.form.tel.length != 11) {
          this.$message.warning("电话格式不对");
          return;
        }
        this.dialogFormVisible = false;
        const data = JSON.parse(window.sessionStorage.getItem("data"));
        this.form.sid = data.sid;
        // console.log('shdi'+data.sid);
        const { data: res } = await this.$http.post(
          "schoolAdmin/addPresidentAdmin",
          this.form
        );
        if (res.success) {
          this.$message.success(res.msg);
          this.handleList(res.date);
          this.iniSearch();
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.dialogFormVisible = false;
        // console.log("fasle");
      }
    },
    async searchByAcademyName() {
      // console.log("searchByAcademyName");
      const { data: res } = await this.$http.get(
        `schoolAdmin/SearchByAcademyName?name=` + this.academyName
      );
      this.presidentAdminList = res.date;
      this.selectAcademy;
      this.handleList(res.date);
      this.$message.success(
        "查询到" + this.presidentAdminList.length + "条记录"
      );
    },

    async reset(id) {
      // console.log("reset");
      const { data: res } = await this.$http.get(
        `schoolAdmin/resetPresidentPwd?id=` + id
      );
      // console.log("reset");
      if (res.success) {
        // console.log("reset");
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
    },
    async deleteAdmin(id) {
      // console.log("delete");
      const { data: res } = await this.$http.get(
        `schoolAdmin/deletePresident?id=` + id
      );
      if (res.success) {
        this.$message.success(res.msg);
        iniSearch();
      } else {
        this.$message.error(res.msg);
      }
    },
    async iniSearch() {
      //查询数据
      const { data: res } = await this.$http.get(
        `schoolAdmin/SearchByAcademyName?name=`
      );
      if (res.success) {
        this.presidentAdminList = res.date;
        this.handleList(res.date);
      } else {
        this.$message.error(res.msg);
      }
    },
    async selectAcademy() {
      //查询数据
      const { data: res } = await this.$http.post(`academys`);
      if (res.success) {
        this.$message.success(res.msg);
        this.academyList = res.date;
        // console.log(this.academyList);
      } else {
        this.$message.error(res.msg);
      }
    },

    async handleList(val) {
      let m = [];
      this.tableList = m;
      // console.log(val.length);console.log(val.length);console.log(val.length);console.log(val.length);
      for (let i = 0; i < val.length; i++) {
        let row = val[i];
        for (let j = 0; j < this.academyList.length; j++) {
          if (this.academyList[j].cid == val[i].cid) {
            row.academy_name = this.academyList[j].academy_name;
            row.budget = this.academyList[j].budget;
            row.maximum_order = this.academyList[j].maximum_order;
            this.tableList.push(row);
            break;
          }
        }
      }
      this.$message.success("查询到" + val.length + "条记录");
    },
  },
};
</script>

<style scoped lang='less'>
.main-content {
  margin-top: 3%;
}

.function-part,
.product-name-box,
.product-type-box,
.transaction-type-box,
.grade-box {
  display: flex;
  align-items: center;
  margin-right: 5px;
}

.product-name,
.product-type,
.transaction-type,
.grade {
  font-size: 14px;
  width: 100px;
}

.dialog > .el-dialog {
  overflow-y: auto !important;
  height: 500px !important;
}

.tit {
  width: 60%;
  margin: 0 auto;
  text-align: center;
  font-size: 24px;
}

.myclose {
  top: 18px;
  right: 36.6px;
  font-size: 16px;
  position: fixed;
  cursor: pointer;
}

.close:hover {
  color: blue;
}

.info {
  width: 50%;
  height: 40px;
  margin: 0 auto;
  text-align: center;
  line-height: 40px;
  border: solid 1px #dadada;
  color: #666;
  background-color: #f6f5f5;
}

.content p {
  width: 70%;
  margin: 10px auto;
  text-indent: 2em;
  line-height: 37px;
}

.btnAdd {
  position: relative;
  left: 800px;
  top: 0px;
}
</style>