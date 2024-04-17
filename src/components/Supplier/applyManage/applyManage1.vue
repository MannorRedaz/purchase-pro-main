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
              <el-option v-for="item in categoryList" :key="item.pid" :value="item.product_name">
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
              <el-option v-for="item in academyList" :key="item.cid" :value="item.academy_name">
              </el-option>
            </el-select>
          </template>
        </div>
      </div>

      <!-- 搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="search">搜索</el-button>
      </div>
    </div>
    <!-- 显示相关的审核数据 -->
    <div class="main-content">
      <el-table :data="bidList" border stripe>
        <el-table-column label="序号" type="index"> </el-table-column>
                 <el-table-column label="采购公告名" prop="boardName">
        </el-table-column>
                <el-table-column label="竞标产品" prop="productName"></el-table-column>          <el-table-column label="出价"
          prop="reallyPrice"></el-table-column>
                 <el-table-column label="产品类别" prop="category"></el-table-column>
                <el-table-column label="申请时间" prop="date"></el-table-column>          
        <el-table-column label="取消申请">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="delete1(scope.row)"
              :disabled="scope.row.is_result == 1">取消申请</el-button>
                      </template>
        </el-table-column>
                <el-table-column label="查看详情">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini"
              @click="showApply(scope.row)">查看详情</el-button>
                      </template>
              </el-table-column>
                <el-table-column label="是否中标">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" size="mini">{{ fun1(scope.row.b) }}</el-button>
                      </template>
              </el-table-column>
      </el-table>
      <el-pagination :page-sizes="[50]" layout="total,sizes, prev, pager, next" :total="bidList.length">
      </el-pagination>
    </div>
    <el-dialog title="竞标详情" :visible.sync="biddingVisible" width="50%" fullscreen class="dialog">
      <div>
        <br />

        <el-tag style="font-size: 20px">以下是出价详情：</el-tag>
        <el-table :data="biddingList" style="width: 100%; font-size: 15px" max-height="300px" border stripe>
          <el-table-column type="index"></el-table-column>
          <el-table-column label="公司名称" prop="tag"></el-table-column>
          <el-table-column label="申请产品" prop="product_name"></el-table-column>
          <el-table-column label="出价" prop="realy_price"></el-table-column>
          <el-table-column label="申请日期" prop="">{{
            this.$moment(biddingList.data).format("YYYY-MM-DD")
          }}</el-table-column>

          <el-table-column label="产品描述" prop="prescribe"></el-table-column>

        </el-table>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { applyManage1JS } from "./applyManage1.js";
export default {
  data() {
    return {
      biddingList: [],
      bidList: [],
      resultList: [],
      supplierList: [],
      academyList: [],
      categoryList: [],
      biddingVisible: false,
      times: [new Date(), new Date()],
      time2: [new Date(), new Date()],
      types: [
        { name: "中标", id: 1 },
        { name: "正在审核", id: 2 },
        { name: "未中标", id: 3 },
      ],
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      }, //设置默认时间
    };
  },
  created() {
    this.inital();
  },

  methods: {
    showApply(val) {
      // console.log(val + "fadsga");
      // console.log(val.id);

      this.getBiddingList(val.id);
      this.biddingVisible = !this.biddingVisible;
    },
    showDialogClosed() {
      this.biddingVisible = !this.biddingVisible;
    },
    fun1(val) {
      if (val == 2) {
        return "已中标";
      } else if (val == 0) {
        return "进行中";
      } else {
        return "未中标";
      }
    },
    fun2(val) {
      if (val.acceess == 11) {
        return "发布公告";
      }
      return "审核中";
    },
    inital() {
      //返回值问题

      // console.log("gjaoiguohg");
      applyManage1JS
        .getAcademyList()
        .then((result) => {
          // console.log("uesgsdgsdgh");
          result.data.date;
          // console.log();
          this.academyList = result.data.date;
        })
        .catch((err) => {
          this.$message.success("error");
        });

      applyManage1JS
        .getCategoryList()
        .then((result) => {
          this.categoryList = result.data.date;
        })
        .catch((err) => {
          this.$message.success("error");
        });

      //  this.$message.success("error");
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];

      this.$http
        .get("supplier/getApplyResultList?sid=" + data.data[0].id)
        .then((res) => {
          // console.log("res");

          // console.log(res.data.date);
          this.bidList = res.data.date;
          for (let i = 0; i < this.bidList.length; i++)
            this.bidList[i].date = this.$moment(this.bidList[i].date).format(
              "YYYY-MM-DD"
            );
          // this.bidList=null;
        });
    },
    async getBiddingList(id) {
      const { data: res } = await this.$http.get("getApplyList?id=" + id);
      this.biddingList = res.date;
    },
    async search() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      this.selects.type = data.id;
      applyManage1JS
        .search(this.selects)
        .then((result) => {
          this.bidList = result.data.date;
          this.$message.success(result.data.msg);
          this.selects.academy = null;
          this.selects.categories = null;
        })
        .catch((err) => {
          this.$message.error("出现问题，请联系工作人员");
        });
    },
    //获得商品类别列表
    async getCategoryList() {
      // console.log(2);
      const { data: res } = this.$http.post("categories");
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
      for (let i = 0; i < this.applyList.length; i++) {
        //申请列表
        //放入申请列表 purid 商品编号
        this.tableData[i] = this.applyList[i];
        // console.log(this.tableData);
        // console.log("list");
        // console.log(this.purchaseList);
        // console.log(this.applyList);
        this.tableData[i].boardName =
          this.purchaseList[this.applyList[i].purid].purchase_name;
        this.tableData[i].academyId =
          this.purchaseList[this.applyList[i].purid].cid;
        this.tableData[i].categoryId =
          this.purchaseList[this.applyList[i].purid].pid;
        this.tableData[i].apply_time1 = this.$moment(
          this.applyList[i].data
        ).format("YYYY-MM-DD");
        this.tableData[i].academy =
          this.academyList[
            this.purchaseList[this.applyList[i].purid].cid
          ].academy_name;
        this.tableData[i].category =
          this.categoryList[
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

      //console.log("yes" + this.tableData[0].category);
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

    async downLoad() {
      window.open(
        "http://localhost:8088/downloadResultFile/?id=" +
        this.clickTarget.result_id
      )
    }
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
}
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