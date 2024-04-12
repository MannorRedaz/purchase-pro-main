<template>
  <div>
    <div>
      <div class="headers">
        <el-tag width="50px" id="tagn">供应商入围审核</el-tag>
        <div class="headers">是否通过:</div>
        <div class="headers">
          <template>
            <el-select placeholder="请选择" v-model="access_value">

              <el-option v-for="item in access" :key="item.id" :value="item.value">
              </el-option>
            </el-select>
          </template>
        </div>
        <div class="headers">申请时间:</div>
        <div class="headers">
          <template>
             <div>
                            <el-date-picker                 v-model="time_value"                 type="datetimerange"
                                range-separator="至"                 start-placeholder="开始日期"
                                end-placeholder="结束日期"               >
                              </el-date-picker>
                        </div>
          </template>
        </div>

      </div>

    </div>
    <el-table :data="tableData" :key="tableData.id" style="width: 100%;font-size: 15px;" stripe>
      <el-table-column type="index" label="序号"> </el-table-column>

      <el-table-column prop="company_name" label="公司名称"> </el-table-column>
      <el-table-column prop="company_place" label="公司位置"> </el-table-column>
      <el-table-column prop="category" label="主要供应产品"> </el-table-column>
      <el-table-column prop="apply_time" label="申请时间"> </el-table-column>

      <el-table-column align="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">详情</el-button>
          <el-button size="mini" type="danger" :disabled="scope.row.school_administrator_access === 1"
            @click="handleApprove(scope.row)">{{ fun1(scope.row) }}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page.sync="currentPage" :page-size="pagesize" :page-sizes="[50]" layout="total,sizes, prev, pager, next"
      :total="tableData.length">
    </el-pagination>

    <el-dialog title="供应商详情" :visible.sync="dialogFormVisible">
      <el-form :model="clickTarget">
        <el-form-item label="公司名称" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.company_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="公司地址" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.company_place" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系人姓名" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.contract_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.tel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品提供类型" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.category" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="公司简介" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.brief_introduction"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="公司法人" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.corporation"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="法人电话" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.corporation_tel"
            autocomplete="off"
          ></el-input>
        </el-form-item> -->
        <el-form-item label="附件下载" :label-width="formLabelWidth">
          <el-button @click="downLoad()">【附件下载】</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog(false)">取 消</el-button>
        <el-button type="primary" @click="dialog(true)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      access_list: [],
      access_value: "",
      time_value: [new Date(), new Date()],
      access: [
        { id: "1", value: "通过" },
        {
          id: "2",
          value: "未通过",
        }, {
          id: "3",
          value: "无",
        }
      ],
      currenId: "",
      formLabelWidth: "120px",
      tmp: [],
      form: {
        academy_name: "",
        contract_name: "shini",
        tel: "1584235035",
        count: "242364326435754725",
        name: "nihao",
        budget: "",
        maximum_order: "",
      },
      dialogFormVisible: false,
      applyList: [],
      supplierList: [],
      categorieList: [],
      tableData: [],
      clickTarget: {},
    };
  },
  created() {
    this.initial();
  },
  watch: {
    time_value: function () {
      if (this.time_value[0] != this.time_value[1]) {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          // let res = this.time_value[0].compareTo(this.tmp[i].date);
          // let res2 = this.time_value[1].compareTo(this.tmp[i].date);
          if (this.time_value[0] <= this.tmp[i].date && this.time_value[1] >= this.tmp[i].date) {
            this.tableData.push(this.tmp[i]);
          }
        }
      }
    },
    access_value: function () {
      if (this.access_value == "通过") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].academy_access == 1) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else if (this.access_value == "未通过") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].academy_access == 0) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else {
        this.tableData = this.tmp;
      }
    },
  },
  methods: {
    fun1(val) {
      if (val.school_administrator_access === 0) {
        return "同意";
      }
      else
        return "已通过";
    },
    //获得商品类别列表
    async getCategoryList() {
      // console.log(2);
      const { data: res } = await this.$http.post("categories");

      const arry = res.date;
      // console.log(arry);

      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.categorieList[res.date[i].pid] = res.date[i];
          // console.log(i);
        }
        // console.log(this.categorieList);
      } else {
        // console.log("categories请求失败！");
      }
    },
    //获取供应商列表
    async getSupplierList() {
      const { data: res } = await this.$http.get("schoolAdmin/getSupplierList");

      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.supplierList[res.date[i].id] = res.date[i];
        }
        // console.log(this.supplierList);
      } else {
        // console.log("getSupplierList请求失败！");
      }
    },
    async dialog(val) {
      if (val) {
        // console.log("dialog");
        this.dialogFormVisible = false;
        const { data: res } = await this.$http.post(
          "schoolAdmin/addPurchaserAdmin",
          // "supper/testAddSchAdmin",
          this.form
        );
        if (res.success) {
          // console.log("dialog2");
          this.$message.success(res.msg);
          this.iniSearch();
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.dialogFormVisible = false;
        // console.log("fasle");
      }
    },
    async initial() {
      await this.getCategoryList();
      await this.getSupplierList();
      //查询数据
      const { data: res } = await this.$http.get(
        `schoolAdmin/getSupApplyList?name=`
      );
      if (res.success) {
        this.applyList = res.date;
        this.$message.success("查询到" + res.date.length + "条记录");
        this.handleList();
      } else {
        this.$message.error(res.msg);
      }
    },
    handleList() {
      for (let i = 0; i < this.applyList.length; i++) {
        this.tableData[i] = this.applyList[i];
        this.tableData[i].company_name = this.supplierList[
          this.applyList[i].sid
        ].company_name;
        this.tableData[i].company_place = this.supplierList[
          this.applyList[i].sid
        ].company_place;
        this.tableData[i].category = this.categorieList[
          this.supplierList[this.applyList[i].sid].cid
        ].product_name;
        this.tableData[i].apply_time = this.$moment(
          this.applyList[i].date
        ).format("YYYY-MM-DD");
        this.tableData[i].corporation = this.supplierList[
          this.applyList[i].sid
        ].corporation;
        this.tableData[i].corporation_tel = this.supplierList[
          this.applyList[i].sid
        ].corporation_tel;
        this.tableData[i].brief_introduction = this.supplierList[
          this.applyList[i].sid
        ].brief_introduction;
        this.tableData[i].contract_name = this.supplierList[
          this.applyList[i].sid
        ].contract_name;
        this.tableData[i].tel = this.supplierList[this.applyList[i].sid].tel;

      }
      //触发更新问题
      this.tableData.push(null);
      this.tableData.pop(null);
      this.tmp = this.tableData;
    },

    handleEdit(row) {
      this.dialogFormVisible = true;
      this.currenId = row.id;
      this.clickTarget = row;
    },
    async handleApprove(row) {
      if (row.school_administrator_access === 0) {
        const { data: res } = await this.$http.get(
          `schoolAdmin/supplyApprove?id=` + row.id
        );
        if (res.success) {
          this.$message.success(res.msg);
        } else {
          this.$message.error(res.msg);
        }
      }
      //this.clickTarget =row;
    },
    async downLoad() {
      window.open(
        "http://localhost:8080/ssm_war_exploded/downLoadSupplierFile/?id=" +
        this.clickTarget.sid
      );
    },
  },
};
</script>
<style lang="less" scoped>
.el-tag {
  margin-right: 800px;
  width: 150px;
  font-size: 20px;
}

.headers {
  margin-right: 5px;
  display: flex;
  align-items: center;
}

#tagn {
  margin-right: 70px;
}
</style>