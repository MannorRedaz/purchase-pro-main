<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <!--选择交易类型-->
      <div class="transaction-type-box">
        <div class="transaction-type">商品类别:</div>
        <div>
          <template>
            <el-select v-model="selects.categories" placeholder="请选择">
              <el-option v-for="item in categoryListLi" :key="item.pid" :value="item.product_name">
              </el-option>
            </el-select>
          </template>
        </div>
      </div>
      <!--选择交易类型-->
      <div class="transaction-type-box">
        <div class="transaction-type">发布来源:</div>
        <div>
          <template>
            <el-select v-model="selects.academy" placeholder="请选择">
              <el-option v-for="item in academyListLi" :key="item.cid" :value="item.academy_name">
              </el-option>
            </el-select>
          </template>
        </div>
      </div>

      <div class="grade-box">
        <div class="grade">时间选择:</div>
        <div>
          <template>
            <div class="block">
              <el-date-picker v-model="selects.value1" type="datetimerange" range-separator="至" start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
            </div>
          </template>
        </div>
      </div>

      <!-- 搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="search">搜索</el-button>
      </div>
    </div>
    <div class="main-content">
      <el-table :data="tableData" style="width: 100%;font-size: 15px;" border stripe>
                <el-table-column type="index"></el-table-column>
                 <el-table-column label="采购公告名" prop="boardName"></el-table-column>
                <el-table-column label="竞标产品" prop="product_name"></el-table-column>          <el-table-column
          label="出价" prop="realy_price"></el-table-column>
                 <el-table-column label="产品类别" prop="category"></el-table-column>
                <el-table-column label="申请时间" prop="apply_time1"></el-table-column>          
        <el-table-column label="取消申请">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini"
              @click="delete1(scope.row)">取消申请</el-button>
                      </template>
        </el-table-column>
                <el-table-column label="是否中标">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini">{{ fun1(scope.row) }}</el-button>
                      </template>
              </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
export default {

  data() {
    return {
      bidList: {},
      addcategory: "",
      filterCategory: "",
      filterBidding: "",
      filtertime: [],
      tableData: [],
      resultList: [],
      supplierList: [],
      academyList: [],
      academyListLi: [],
      categoryList: [],
      categoryListLi: [],
      purchaseList: [],
      applyList: [],
      tmp: [],
      seeTarget: {},
      addTarget: {},

      editModel: {},

      times: [new Date(), new Date()],
      time2: [new Date(), new Date()],
      category: "",
      category1: "",

      types: [
        { name: "中标", id: 1 },
        { name: "正在审核", id: 2 },
        { name: "未中标", id: 3 },
      ],
      newPwd: "",
      type_value: "",
      productName: 0,
      currentPage: 0,
      date: "",
      clickTarget: {},
      count1: 0,
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      }, //设置默认时间
    };
  },
  created() {

    this.getAcademyList();
    this.getCategoryList();
    //this.handleList2();

    this.handleList();

    // this.getApplyList();
    // this.getResultListBySupplierId();
  },

  methods: {
    async handleList2() {
      // this.$message.success("188");

      // const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      //  this.$message.success("191");

      // //  const { data: res }=  this.$http.get(
      // //       "supplier/getApplyResultList?sid=" + data.id
      // //     );
      // const res =   this.$http.get(
      //      "supplier/getApplyResultList?sid=" + data.id
      //      );
      // console.log("200000");
      // console.log("200000");
      // console.log("200000");
      // console.log("200000");

      //this.$message.success("200"+res.msg);
      // this.bidList =res.date;
      // this.$message.success("200");

    },
    async search() {
      // console.log(this.selects.value1 + "time");

      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      // for (let i = 0; i < this.academyListLi.length; i++) {
      //   if (this.academyListLi[i].cid == data.cid) {
      //     this.selects.academy = this.academyListLi[i].academy_name;
      //   }
      // }
      //使用type替代那个供应商
      this.selects.type = data.id;
      const { data: res } = await this.$http.post(
        "supplier/applyListScreen",
        this.selects
      );
      if (res.success) {
        this.applyList = res.date;
        // console.log("purchseList+" + this.applyList);
        // console.log("purchseList+" + res.date);

        this.handleList();
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
    },
    //获得商品类别列表
    async getCategoryList() {
      // console.log(2);
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
      if (res.success) {
        this.categoryListLi = res.date;
        for (let i = 0; i < res.date.length; i++) {
          this.categoryList[res.date[i].pid] = res.date[i];
          // console.log(i);
        }
      } else {
        // console.log("categories请求失败！");
      }
    },
    //获得学院列表
    async getAcademyList() {
      // console.log(2);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      // console.log(arry);
      if (res.success) {
        this.academyListLi = res.date;
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
          // console.log(i);
        }
      } else {
        // console.log("categories请求失败！");
      }
    },
    //使用学院号获得该学院的采购信息

    async getPurchaseList() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.post("supplier/getPurchaseList?");
      // console.log("getGoogsList");
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.purchaseList[res.date[i].id] = res.date[i];
        }
        // console.log("purchasingList请求成功！");
      } else {
        // console.log("purchasingList请求失败！");
      }
    },
    //通过供应商id查询结果列表
    async getResultListBySupplierId() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "supplier/getResultListBySupplierId?sid=" + data.id
      );

      if (res.success) {
        this.resultList = res;
        // console.log("这里");
        // console.log(this.resultList);

        this.$message.success(res.msg);

      } else {
        // console.log("resultList请求失败！");

        this.$message.error(res.msg);
      }
    },

    //获取申请列表
    async getApplyList() {
      await this.getAcademyList();
      await this.getCategoryList();
      await this.getPurchaseList();
      await this.getResultListBySupplierId();
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "supplier/getApplyList?id=" + data.id
      );
      // console.log("getGoogsList");
      if (res.success) {
        this.applyList = res.date;
        this.handleList();
      } else {
        // console.log("purchasingList请求失败！");
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
    handleList() {
      let l = [];
      this.tableData = l;
      for (let i = 0; i < this.applyList.length; i++) {//申请列表
        //放入申请列表 purid 商品编号
        this.tableData[i] = this.applyList[i];
        // console.log(this.tableData);
        // console.log("list");
        // console.log(this.purchaseList);
        // console.log(this.applyList);
        this.tableData[i].boardName = this.purchaseList[
          this.applyList[i].purid
        ].purchase_name;
        this.tableData[i].academyId = this.purchaseList[
          this.applyList[i].purid
        ].cid;
        this.tableData[i].categoryId = this.purchaseList[
          this.applyList[i].purid
        ].pid;
        this.tableData[i].apply_time1 = this.$moment(
          this.applyList[i].data
        ).format("YYYY-MM-DD");
        this.tableData[i].academy = this.academyList[
          this.purchaseList[this.applyList[i].purid].cid
        ].academy_name;
        this.tableData[i].category = this.categoryList[
          this.purchaseList[this.applyList[i].purid].pid
        ].product_name;
        this.tableData[i].is_result =
          this.purchaseList[this.applyList[i].purid].is_result;

        this.tableData[i].pid =
          this.purchaseList[this.applyList[i].purid].is_result;
        for (let j = 0; j < this.resultList.length; j++) {
          //结果
          /**
           * for(int i=0;i<this.tableData.length;i++){pid==
           * }
           * 
           */
          this.tableData[j].bidding = 0;
          if (this.tableData[j].purid == this.resultList[j].pid) {
            this.tableData[j].bidding = 1;
          } else {
            this.tableData[j].bidding = 0;
          }
        }
      } //触发更新问题

      // console.log("yes" + this.tableData[0].category);
      this.tableData.push(null);
      this.tableData.pop(null);
      this.tmp = this.tableData;
    },
    async delete1(val) {
      const { data: res } = await this.$http.post("supplier/deleteApply", val);
      if (res.success) {
        this.$message.success(res.msg);
        this.getApplyList();
      } else {
        this.$message.error(res.msg);
      }
    },
    fun1(val) {

      // console.log("gjsoigj" + val);
      if (val.bidding == 1) {
        return "中标";
      } else {
        // console.log("gjsoigj" + val.bidding);
        return "未中标";
      }
      // if (val.b == 2) {
      //   return "你已中标";
      // }else if(val.b==0){
      //   return "进行中"
      // }else{
      //   return "未中标"
      // }
    },
    fun2(val) {
      if (val.acceess == 11) {
        return "发布公告";
      }
      return "审核中";
    },
    async downLoad() {
      window.open(
        "http://localhost:8088/ssm_war_exploded/downloadResultFile/?id=" +
        this.clickTarget.result_id
      );
    },
    async getSupplier(val) {
      await this.showDialog;
      const { data: res } = await this.$http.get(
        "getSupplier?id=" + val.supplierId
      );
      val.company_name = res.date[0].company_name;
      this.clickTarget = val;
      // console.log("32");
      // console.log("32");
      // console.log(this.clickTarget);
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
</style>