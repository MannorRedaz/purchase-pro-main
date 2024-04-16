<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <!--输入物品名称-->
      <div class="product-name-box">
        <div class="product-name">学校名称:</div>
        <el-input v-model="schoolName" placeholder="请输入学校名称进行查询">
        </el-input>
      </div>
      <!--搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="seachBySchoolName">搜索</el-button>
        <el-button type="primary" @click="dialogFormVisible = true">添加学校管理员</el-button>
      </div>
      <div class="btnAdd">
        <!-- Form  button 的type属性primary和text  -->
        <!-- <el-button type="primary" @click="dialogFormVisible = true"
          >添加学校管理员</el-button
        > -->
        <!-- @click="addSchAdmin" -->
        <el-dialog title="添加学校管理员" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="学校名" :label-width="formLabelWidth">
              <el-input v-model="form.schoolName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学校管理员姓名" :label-width="formLabelWidth">
              <el-input v-model="form.contract_name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" :label-width="formLabelWidth">
              <el-input v-model="form.tel" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学校账户" :label-width="formLabelWidth">
              <el-input v-model="form.count" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="备注" :label-width="formLabelWidth">
              <el-input v-model="form.tag" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <!-- <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false"
              >确 定</el-button
            > -->
            <el-button @click="dialog(false)">取 消</el-button>
            <el-button type="primary" @click="dialog(true)">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="tableList" :key="sid" style="width: 100%;font-size: 15px;" stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="学校名" prop="school_name"> </el-table-column>
        <el-table-column label="账号" prop="name"> </el-table-column>
        <el-table-column label="联系人姓名" prop="contract_name">
        </el-table-column>
        <el-table-column label="联系人电话" prop="tel"> </el-table-column>
        <el-table-column label="重置密码">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="reset(scope.row.id)">重置密码</el-button>
          </template>
        </el-table-column>
        <el-table-column label="删除">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="deleteAdmin(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="page-part">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage" :page-size="pagesize" :page-sizes="[50]"
        layout="total,sizes, prev, pager, next" :total="tableList.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import VueRouter from 'vue-router';

export default {
  data() {
    return {
      sid:0,
      pagesize:5,
      schoolName: "",
      currentPage: 0,
      date: "",
      tableList: [],
      schoolAdminList: [],
      schoolList: [],
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
        contract_name: "shini",
        tel: "1584235035",
        count: "242364326435754725",
        name: "nihao",
        schoolName: "学校",
        tag: "备注",
      },
      formLabelWidth: "120px",
    };
  },
  created() { },
  mounted() {
    // const data = JSON.parse(window.sessionStorage.getItem('data')).data[0];
    this.iniSearch();
    this.selectSchool();
    this.handleList();
  },
  computed: {},
  methods: {
    handleSizeChange() { },
    handleCurrentChange() { },
    async dialog(val) {
      if (val) {
        // console.log("dialog");
        this.dialogFormVisible = false;
        const { data: res } = await this.$http.post(
          "supper/addSchAdmin",
          // "supper/testAddSchAdmin",
          this.form
        );
        // console.log("dialog1");
        // console.log(res);
        if (res.success) {
          // console.log("dialog2");

          this.$message.success(res.msg);
          this.search();
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.dialogFormVisible = false;
        // console.log("fasle");
      }
    },
    async seachBySchoolName() {

      // console.log("seachBySchoolName");
      const { data: res } = await this.$http.get(
        `supper/selectSchoolByName?name=` + this.schoolName
      );
      this.schoolList = res.date;
      for (let i = 0; i < this.schoolAdminList.length; i++) {
        this.tableList.pop();
      }
      for (let i = 0; i < this.schoolList.length; i++) {
        let row;
        console.log(row);
        for (let j = 0; j < this.schoolAdminList.length; j++) {
          if (this.schoolList[i].sid == this.schoolAdminList[j].sid) {
            row = this.schoolAdminList[j];
            row.school_name = this.schoolList[i].school_name;
            this.tableList.push(row);
            break;
          }
        }
      }
      this.$message.success("查询到" + this.schoolAdminList.length + "条记录");
    },

    async reset(id) {
      // console.log("reset");
      const { data: res } = await this.$http.get(
        `supper/resetAdminPwd?id=` + id
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
      const { data: res } = await this.$http.get(`supper/deleteAdmin?id=` + id);
      
      if (res.success) {
        this.$message.success(res.msg);
        //刷新页面
        this.$router.push("/supper/supperUserMenage");
      } else {
        this.$message.error(res.msg);
      }
    },
    async iniSearch() {
      //查询数据
      const { data: res } = await this.$http.get(
        `supper/selectSchoolAdminByName` 
      );
      // console.log(this.schoolName);
      if (res.success) {
        this.schoolAdminList = res.date;
        // console.log(this.schoolAdminList);
      } else {
        this.$message.error(res.msg);
      }
    },
    search() {
      this.iniSearch();
      this.handleList();
    },
    async selectSchool() {
      //查询数据
      const { data: res } = await this.$http.get(`supper/selectSchool`);
      if (res.success) {
        this.schoolList = res.date;
        // console.log(this.schoolList);
      } else {
        this.$message.error(res.msg);
      }
    },

    async handleList() {
      await this.iniSearch();
      await this.selectSchool();
      for (let i = 0; i < this.schoolAdminList.length; i++) {
        this.tableList.pop();
      }
      for (let i = 0; i < this.schoolAdminList.length; i++) {
        let row = this.schoolAdminList[i];
        // console.log(row);
        for (let j = 0; j < this.schoolList.length; j++) {
          if (this.schoolList[j].sid == this.schoolAdminList[i].sid) {
            row.school_name = this.schoolList[j].school_name;
            this.tableList.push(row);
            break;
          }
        }
      }
      this.$message.success("查询到" + this.schoolAdminList.length + "条记录");
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

.dialog>.el-dialog {
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