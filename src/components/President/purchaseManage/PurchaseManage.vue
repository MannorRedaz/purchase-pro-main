<template>
  <div>
    <div>
      <div class="headers">
        <el-tag width="100px" id="tagn">采购审核</el-tag>
        <div class="headers">是否通过:</div>
        <div class="headers">
          <template>
            <el-select placeholder="请选择" v-model="access_value">
              <el-option
                v-for="item in access"
                :key="item.id"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </div>
        <div class="headers">申请时间:</div>
        <div class="headers">
          <template>
             
            <div>
                            <el-date-picker
                v-model="selects.value1"
                type="datetimerange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
                              </el-date-picker
              >
                        
            </div>
          </template>
        </div>
        <!-- 搜索-->
        <div id="searchStyle">
          <el-button type="primary" @click="search">搜索</el-button>
        </div>
      </div>
    </div>
    <el-table
      :data="tableData"
      :key="tableData.id"
      style="width: 100%; font-size: 15px"
      stripe
    >
      <el-table-column type="index" label="序号"> </el-table-column>
      <el-table-column prop="academy" label="采购院系"> </el-table-column>
      <el-table-column prop="apply_time1" label="申请时间"> </el-table-column>

      <el-table-column prop="product_name" label="采购产品"> </el-table-column>
      <el-table-column prop="category" label="所属类别"> </el-table-column>
      <el-table-column prop="tag" label="采购数量"> </el-table-column>
      <el-table-column prop="highest_price" label="预估金额"> </el-table-column>

      <el-table-column align="right" label="详情">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">详情</el-button>
        </template>
      </el-table-column>
      <el-table-column align="right" label="是否通过">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            :disabled="scope.row.acceess >= 1"
            @click="handleApprove(scope.row)"
            >{{ fun1(scope.row) }}</el-button
          >
        </template>
      </el-table-column>
    </el-table>
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
         <el-form-item label="型号或规格" prop="" :label-width="formLabelWidth">
          <el-input auto-complete="off" v-model="clickTarget.purpose"></el-input
        ></el-form-item>
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
            v-model="clickTarget.apply_time1"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="附件下载" :label-width="formLabelWidth">
          <el-button @click="downLoad1()">【附件下载】</el-button>
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
      tableData: [],
      clickTarget: {},
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      },
    };
  },
  created() {
    this.initial();
  },
  watch: {
    // time_value: function () {

    //   if (this.time_value[0]>this.time_value[1]) {
    //     this.tableData = this.tmp;
    //   } else {
    //     let l = [];
    //     this.tableData = l;
    //     for (let i = 0; i < this.tmp.length; i++) {
    //       // let res = this.time_value[0].compareTo(this.tmp[i].date);
    //       // let res2 = this.time_value[1].compareTo(this.tmp[i].date);
    //       if (
    //         this.time_value[0] <= this.tmp[i].publish_time &&
    //         this.time_value[1] >= this.tmp[i].publish_time
    //       ) {
    //         this.tableData.push(this.tmp[i]);
    //       }
    //     }
    //   }
    // },
    access_value: function () {
      if (this.access_value == "通过") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].acceess >= 1) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else if (this.access_value == "未通过") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].acceess < 1) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else {
        this.tableData = this.tmp;
      }
    },
  },
  methods: {
    async search() {
      console.log(this.selects.value1 + "time");

      const data = JSON.parse(window.sessionStorage.getItem("data"));
      for (let i = 0; i < this.academyListLi.length; i++) {
        if (this.academyListLi[i].cid == data.cid) {
          this.selects.academy = this.academyListLi[i].academy_name;
        }
      }
      const { data: res } = await this.$http.post(
        "president/purchaseScreen",
        this.selects
      );
      if (res.success) {
        this.purchaseList = res.date;
        console.log("purchseList+" + this.purchaseList);
        console.log("purchseList+" + res.date);

        this.handleList();
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
    },
    async getAcademyList() {
      console.log(2);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      console.log(arry);
      if (res.success) {
        this.academyListLi = res.date;
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
          console.log(i);
        }
      } else {
        console.log("categories请求失败！");
      }
    },

    async getPurchaseList() {
      await this.getAcademyList();
      await this.getCategoryList();
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res } = await this.$http.get(
        "president/getPurchaseList1?cid=" + data.cid
      );
      console.log("getGoogsList");
      if (res.success) {
        this.purchaseList = res.date;
        this.handleList();
      } else {
        console.log("purchasingList请求失败！");
      }
    },
    fun1(val) {
      if (val.acceess < 1) return "同意";
      else return "已通过";
    },
    //获得商品类别列表
    async getCategoryList() {
      console.log(2);
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
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
     async downLoad1() {
      window.open(
        "http://localhost:8080/ssm_war_exploded/downLoadPurchaseFile/?id=" +
          this.clickTarget.id
      );
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
    handleList() {
      let l = [];
      this.tableData = l;
      for (let i = 0; i < this.purchaseList.length; i++) {
        this.tableData[i] = this.purchaseList[i];
        this.tableData[i].apply_time1 = this.$moment(
          this.purchaseList[i].apply_time
        ).format("YYYY-MM-DD");
        this.tableData[i].publish_time1 = this.$moment(
          this.purchaseList[i].publish_time
        ).format("YYYY-MM-DD");
        this.tableData[i].academy =
          this.academyList[this.purchaseList[i].cid].academy_name;
        this.tableData[i].category =
          this.categorieList[this.purchaseList[i].pid].product_name;
      }
      //触发更新问题
      this.tableData.push(null);
      this.tableData.pop(null);
      this.tmp = this.tableData;

      console.log("yes");
      console.log("yes");
      console.log("yes");
      console.log("yes");
      console.log(this.tableData);
    },

    handleEdit(row) {
      this.dialogFormVisible = true;
      this.currenId = row.id;
      this.clickTarget = row;
    },
    async handleApprove(row) {
      const { data: res } = await this.$http.get(
        `president/purchaseApprove?id=` + row.id
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
}
#tagn {
  margin-right: 15px;
}
</style>