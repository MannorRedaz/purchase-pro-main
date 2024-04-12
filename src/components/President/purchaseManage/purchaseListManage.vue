<template>
  <div>
    <div>
      <div class="headers">
        <div class="headers">年度选择:</div>
        <!--搜索-->
        <div class="headers">
          <template>
            <div>
              <div class="block">
                <el-date-picker
                  v-model="tmpTime"
                  type="year"
                  placeholder="选择年"
                >
                </el-date-picker>
              </div>
            </div>
          </template>
        </div>

        <div class="headers">采购金额：</div>
        <div class="headers">
          <el-input
            :disabled="true"
            v-model="count"
            autocomplete="off"
          ></el-input>
        </div>
      </div>
    </div>
    <div>
      <el-table
        :data="tableData"
        :key="tableData.id"
        style="width: 100%; font-size: 15px"
        stripe
      >
        <el-table-column type="index" label="序号"> </el-table-column>
        <el-table-column prop="academy" label="采购院系"> </el-table-column>
        <el-table-column prop="product_name" label="采购产品">
        </el-table-column>
        <el-table-column prop="category" label="所属类别"> </el-table-column>
        <el-table-column prop="tag" label="采购数量"> </el-table-column>
        <el-table-column prop="highest_price" label="预估金额">
        </el-table-column>
        <el-table-column prop="company_name" label="公司名称">
        </el-table-column>
        <el-table-column prop="reality_price" label="成交价格">
        </el-table-column>
        <el-table-column align="right" label="详情">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)"
              >详情</el-button
            >
          </template>
        </el-table-column>
        <el-table-column align="right" label="是否通过">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              :disabled="scope.row.acceess == 11"
              @click="handleApprove(scope.row)"
              >{{ fun1(scope.row) }}</el-button
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
        :total="tableData.length"
      >
      </el-pagination>
    </div>
    <el-dialog title="采购详情" :visible.sync="dialogFormVisible">
      <el-form :model="clickTarget">
        <el-form-item label="申请目的" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.reason" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品名称" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.product_name"
            autocomplete="off"
          ></el-input>
        </el-form-item>
         <el-form-item label="型号或类型" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.purpose" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品类别" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.category"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        
        <el-form-item label="发布学院" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.academy" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="预估价格" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.highest_price"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="需求数量" :label-width="formLabelWidth">
          <el-input v-model="clickTarget.tag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="申请时间" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.publish_time1"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="中标公司" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.company_name"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="成交价格" :label-width="formLabelWidth">
          <el-input
            v-model="clickTarget.reality_price"
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
</template>

<script>
export default {
  data() {
    return {
      //某某年的消费钱
      tmpTime: "",
      count: "",
      selects: {
        academy: "",
        categories: "",
        value1: [],
        type: "",
      },
      access_list: [],
      access_value: "",
      time_value: [new Date(), new Date()],
      access: [
        { id: "1", value: "通过" },
        {
          id: "2",
          value: "未通过",
        },
        {
          id: "3",
          value: "无",
        },
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
      purchaseList: [],
      academyList: [],
      academyListLi: [],
      categorieList: [],
      categorieListLi: [],
      tableData: [],
      clickTarget: {},
      resultList: [],
      supplierList: [],
      resultList1: [],
    };
  },
  created() {
    this.initial();
  },
  watch: {
    tmpTime: function () {
      this.selects.value1[0] = this.tmpTime;
      this.search();
    },
  },

  methods: {
    async getAcademyList() {
      console.log(2);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      console.log(arry);
      this.academyListLi = arry;
      console.log("getAcademyList");
      console.log("getAcademyList");
      console.log("getAcademyList");
      console.log("getAcademyList");
      console.log(this.academyListLi);

      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
          console.log(i);
        }
      } else {
        console.log("categories请求失败！");
      }
    },
    async getResultList() {
      console.log(2);
      const { data: res } = await this.$http.post("resultList");
      const arry = res.date;
      console.log(arry);
      this.resultList1 = arry;
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          //商品编号作为id
          this.resultList[res.date[i].pid] = res.date[i];
          console.log(i);
        }
      } else {
        console.log("categories请求失败！");
      }
    },
    async getPurchaseList() {
      await this.getAcademyList();
      await this.getCategoryList();
      await this.getResultList();
      await this.getSupplierList();
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res } = await this.$http.get(
        "president/getPurchaseList?cid=" + data.cid
      );
      if (res.success) {
        this.purchaseList = res.date;
        this.handleList();
      } else {
        console.log("purchasingList请求失败！");
      }
    },
    fun1(val) {
      if (val.acceess == 1) return "同意";
      else return "已通过";
    },

    //获得商品类别列表
    async getCategoryList() {
      console.log(2);
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
      this.categorieListLi = arry;
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.categorieList[res.date[i].pid] = res.date[i];
          console.log(i);
        }
        console.log(this.categorieList);
      } else {
        console.log("categories请求失败！");
      }
    },
    //获取供应商列表
    async getSupplierList() {
      const { data: res } = await this.$http.get("schoolAdmin/getSupplierList");

      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.supplierList[res.date[i].id] = res.date[i];
        }
        console.log(this.supplierList);
      } else {
        console.log("getSupplierList请求失败！");
      }
    },
    async dialog(val) {
      if (val) {
        console.log("dialog");
        this.dialogFormVisible = false;
        const { data: res } = await this.$http.post(
          "schoolAdmin/addPurchaserAdmin",
          // "supper/testAddSchAdmin",
          this.form
        );
        if (res.success) {
          console.log("dialog2");
          this.$message.success(res.msg);
          this.iniSearch();
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.dialogFormVisible = false;
        console.log("fasle");
      }
    },
    async initial() {
      this.getPurchaseList();
    },
    async search() {
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      this.selects.academy = this.academyList[data.cid].academy_name;
      const { data: res } = await this.$http.post(
        "president/selectDetail",
        this.selects
      );

      console.log(this.purchaseList);
      if (res.success) {
        this.purchaseList = res.date;
        this.count = res.status;
        this.handleList();
      } else {
        console.log("purchasingList请求失败！");
      }
    },
    handleList() {
      console.log("purchaseList");
      console.log(this.purchaseList);
      let l = [];
      this.tableData = l;
      console.log(this.purchaseList);
      for (let i = 0; i < this.purchaseList.length; i++) {
        this.tableData[i] = this.purchaseList[i];
        this.tableData[i].publish_time1 = this.$moment(
          this.purchaseList[i].publish_time
        ).format("YYYY-MM-DD");
        this.tableData[i].academy =
          this.academyList[this.purchaseList[i].cid].academy_name;
        this.tableData[i].category =
          this.categorieList[this.purchaseList[i].pid].product_name;
        //通过外键赋值
        console.log(this.purchaseList[i].id);
        //初始化时，有的商品还没有结果
        this.tableData[i].reality_price =
          this.resultList[this.purchaseList[i].id].reality_price;
        //this.tableData[i].reality_price =3000;
        console.log(this.tableData);
        console.log(this.resultList);
        console.log("this.tableData[i].reality_price");
        // console.log(this.tableData[i].reality_price);
        this.tableData[i].company_name =
          this.supplierList[
            this.resultList[this.purchaseList[i].id].sid
          ].company_name;
      }
      //触发更新问题
      this.tableData.push(null);
      this.tableData.pop(null);
      let o = [];
      this.tmp = o;
      this.tmp = this.tableData;
    },

    handleEdit(row) {
      this.dialogFormVisible = true;
      this.currenId = row.id;
      this.clickTarget = row;
    },
    async handleApprove(row) {
      const { data: res } = await this.$http.get(
        `schoolAdmin/purchaseApprove?id=` + row.id
      );
      if (res.success) {
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
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
  display: flex;
  align-items: center;
  margin-left: 30px;
}
</style>