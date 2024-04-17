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
      <div id="searchStyle">
        <el-button type="primary" @click="search">搜索</el-button>
      </div>

      <div class="grade-box">
        <div class="grade"></div>
        <template>
          <el-button @click="addPurchaseShow"> 添加采购 </el-button>
        </template>
      </div>
    </div>
    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="paginatedData" style="width: 100%; font-size: 15px" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="商品名称" prop="product_name"></el-table-column>
        <el-table-column label="商品类别" prop="category"></el-table-column>
        <el-table-column label="申请时间" prop="apply_time1"></el-table-column>
        <el-table-column label="查看">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="seePurchaseDialog(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="取消申请">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="delete1(scope.row)"
              :disabled="scope.row.acceess >= 1">{{ fun1(scope.row) }}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="发布公告">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showAddBoard(scope.row)"
              :disabled="scope.row.purchase_name != null">{{ fun2(scope.row) }}</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage" :page-size="pageSize" :page-sizes="[5, 10, 20, 50]" @prev-click="prevPage"
        @next-click="nextPage" layout="total, sizes, prev, pager, next" :total="tableData.length">
      </el-pagination>

    </div>

    <el-dialog title="采购详情" :visible.sync="showDetailPurchase" :width="'500px'" :height="'300px'"
      :append-to-body="true">
      <el-form label-width="120px" :model="seeTarget" size="small" label-position="right">
        <el-form-item label="产品名称" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="seeTarget.product_name" readonly></el-input>
          </el-col>
          <el-col :span="4" style="margin-left: 20px;"> 产品数量 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="seeTarget.tag" readonly></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="型号或类型" prop="">
          <el-col>
            <el-input auto-complete="off" v-model="seeTarget.purpose" readonly></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="采购目的" prop="">
          <el-input auto-complete="off" v-model="seeTarget.reason" readonly></el-input></el-form-item>
        <el-form-item label="产品类别" prop="">
          <el-col :span="8">
            <el-input v-model="seeTarget.category" readonly></el-input>
          </el-col>
          <el-col :span="4" style="margin-left: 20px;"> 预算价格 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="seeTarget.highest_price" readonly></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="warning" @click="closeDetailPurchase">关闭</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加采购" :visible.sync="showAddPurchase" width="50%" fullscreen class="dialog"
      :append-to-body="true">
      <el-form label-width="120px" :model="addTarget" size="small" label-position="right">
        <el-form-item label="产品名称" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addTarget.product_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品数量 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addTarget.tag"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="型号或规格" prop="">
          <el-input auto-complete="off" v-model="addTarget.purpose"></el-input></el-form-item>
        <el-form-item label="采购目的" prop="">
          <el-input auto-complete="off" v-model="addTarget.reason"></el-input></el-form-item>
        <el-form-item label="产品类别" prop="">
          <el-col :span="3">
            <el-select v-model="addTarget.pid" placeholder="请选择">
              <el-option v-for="item in categoryListLi" :key="item.pid" :label="item.product_name" :value="item.pid">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="2"> 预算价格 </el-col>
          <el-col :span="3">
            <el-input auto-complete="off" v-model="addTarget.highest_price"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="上传附件">

          <div>
            <el-form :model="form">
              <el-form-item>
                <el-upload ref="upload" action="http://localhost:8088/uploadPurchaseFile" :limit="limitNum"
                  :auto-upload="false" accept=".doc" :before-upload="beforeUploadFile" :on-change="fileChange"
                  :on-exceed="exceedFile" :on-success="handleSuccess" :on-error="handleError" :file-list="fileList"
                  :data="{ id: this.purchaserId }">
                  <el-button size="small" plain>选择文件</el-button>
                  <div slot="tip" class="el-upload__tip">
                    只能上传word文件，且不超过10M
                  </div>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <el-button size="small" type="primary" @click="uploadFile">立即上传</el-button>
                <el-button size="small">取消</el-button>
              </el-form-item>

            </el-form>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="warning" @click="addPurchase">添加采购</el-button>
        <el-button size="mini" type="warning" @click="closeAddPurchase">关闭</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加公告" :visible.sync="showAddDialog" width="50%" fullscreen class="dialog">
      <el-form label-width="120px" :model="addModel" size="small" label-position="right">
        <el-form-item label="公告名" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.purchase_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品名称 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.product_name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="产品数量">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.tag"></el-input>
          </el-col>
          <el-col :span="2"> 型号或类型 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.purpose"></el-input></el-col>
        </el-form-item>

        <el-form-item label="招标目的" prop="">
          <el-input v-model="addModel.reason" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择开始和结束时间" prop="">
          <el-col :span="8">
            <el-date-picker v-model="times" type="datetimerange" range-separator="至" start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-col>
          <el-col :span="2">产品类别</el-col>
          <el-col :span="3">
            <el-select v-model="category" placeholder="请选择">
              <el-option v-for="item in categoryListLi" :key="item.pid" :value="item.product_name">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="2"> 预算价格 </el-col>
          <el-col :span="3">
            <el-input auto-complete="off" v-model="addModel.highest_price"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="成交原则">
          <el-input maxlength="18" v-model="addModel.rules"></el-input>
        </el-form-item>
        <el-form-item label="竞标资格">
          <el-input maxlength="18" v-model="addModel.qualifications"></el-input>
        </el-form-item>
        <el-form-item label="其他事项">
          <el-input v-model="addModel.others"></el-input>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="primary" @click="addBoard">添加公告</el-button>
        <el-button size="mini" type="warning" @click="cancelAdd">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      p: 0,
      purchaserId: '',
      addcategory: "",
      filterCategory: "",
      filterAccess: "",
      filtertime: [],

      supplierList: [],
      academyList: [],
      academyListLi: [],
      categoryList: [],
      categoryListLi: [],
      purchaseList: [],
      tmp: [],
      tmp1: [],

      seeTarget: {},
      addTarget: {},

      showDetailPurchase: false,
      showAddPurchase: false,
      showAddDialog: false,

      addModel: {
        id: "",
        cid: 0,
        pid: 1, //选择
        sid: 1, //默认
        deadline: "",
        product_name: "平板",
        highest_price: 5000,
        specified: 0,
        reason: "需要一台平板用于显示实时数据",
        tag: "10",
        purchase_name: "攀枝花学院采购手机公告3",
        publish_time: 1619869906000,
        start_time: 1619783444000,
        end_time: 1619783444000,
        title:
          "我校拟对一批拆下的 废旧铁窗做 公开处置拍卖，现将有关事项公告如下：",
        purpose: "出售废旧铁门铁窗等",
        site: "攀枝花学院分册中心",
        site_inspection_time: 1620215518000,
        qualifications: "有正规营业执照，身份证复印件及其他资质证明",
        notifiedcation: "2021-05-05 19:51:58",
        errol_contract: "报名老师",
        errol_tel: "1538849045",
        errol_type: 1,
        inspection_contract: "现场查看联系人",
        inspection_tel: "1538849045",
        click_count: 0,
        file_place: "http：//baidu。com",
        rules: "先通过网上申请竞标,最低价中标",
        auction_site: "攀枝花学院办公楼403",
        is_result: 0,
        others:
          "拍卖物的拆卸费、运输费、建筑物破坏恢复、垃圾清运及其它一切相关费用均由中标人自行承担。",
        acceess: 11,
      },
      editModel: {},

      times: [new Date(), new Date()],
      time2: [new Date(), new Date()],
      category: "",
      category1: "",

      types: [
        { name: "已通过", id: 1 },
        { name: "正在审核", id: 2 },
      ],
      newPwd: "",
      type_value: "",
      productName: 0,
      currentPage: 1,
      date: "",
      pageSize: 5, // 添加页面大小属性
      totalItems: 10, // 设置总数为适当的值
      tableData: [],
      clickTarget: {},
      count1: 0,
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      },
      limitNum: 10,
      fileList: [],
    };
  },

  created() {
    this.getPurchaseList();
  },

  watch: {
    filterCategory: function () {
      let l = [];
      this.tableData = l;
      for (let i = 0; i < this.tmp.length; i++) {
        if (this.tmp[i].category == this.filterCategory) {
          this.tableData.push(this.tmp[i]);
        }
      }
    },

    filterAccess: function () {
      if (this.filterAccess == "已通过") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].acceess == 11) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else if (this.filterAccess == "正在审核") {
        let l = [];
        this.tableData = l;
        for (let i = 0; i < this.tmp.length; i++) {
          if (this.tmp[i].acceess != 11) {
            this.tableData.push(this.tmp[i]);
          }
        }
      } else {
        this.tableData = this.tmp;
      }
    },
  },
  mounted() {
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    //获取当前id最大值+1，并存储于errol_type
    this.purchaserId = data.id;
  },
  computed: {
    paginatedData() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.tableData.slice(startIndex, endIndex);
    }
  },
  methods: {
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: "警告",
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length
          } 个`,
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      this.form.file = file.raw;
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      let extension = file.name.substring(file.name.lastIndexOf(".") + 1);
      let size = file.size / 1024 / 1024;
      if (extension != "doc") {
        this.$notify.warning({
          title: "警告",
          message: `只能上传word文件`,
        });
        return false;
      }
      if (size > 10) {
        this.$notify.warning({
          title: "警告",
          message: `文件大小不得超过10M`,
        });
        return false;
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: "成功",
        message: `文件上传成功`,
      });
      this.p = 1;
    },
    // 文件上传失败时的钩子 err错误信息
    handleError(err, file, fileList) {
      let myError = err.toString(); //转字符串
      myError = myError.replace("Error: ", ""); // 去掉前面的" Error: "
      this.$notify.error({
        title: "错误",
        message: `文件上传失败`,
      });
    },
    uploadFile() {
      this.$refs.upload.submit();
    },
    async search() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      for (let i = 0; i < this.academyListLi.length; i++) {
        if (this.academyListLi[i].cid == data.cid) {
          this.selects.academy = this.academyListLi[i].academy_name;
        }
      }
      const { data: res } = await this.$http.post(
        "purchaser/purchaseScreen1",
        this.selects
      );
      if (res.success) {
        this.purchaseList = res.date;
        this.handleList();
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
    },
    //获得商品类别列表
    async getCategoryList() {
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
      if (res.success) {
        this.categoryListLi = res.date;
        for (let i = 0; i < res.date.length; i++) {
          this.categoryList[res.date[i].pid] = res.date[i];
        }
      }
    },
    //获得学院列表
    async getAcademyList() {
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      if (res.success) {
        this.academyListLi = res.date;
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
        }
      }
    },
    //使用学院号获得该学院的采购信息
    async getPurchaseList() {
      await this.getAcademyList();
      await this.getCategoryList();
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "president/getPurchaseList1?cid=" + data.cid
      );
      // console.log("getGoogsList");
      if (res.success) {
        this.purchaseList = res.date;
        this.handleList();
      }
      this.search()
    },
    //获取供应商列表
    async getSupplierList() {
      const { data: res } = await this.$http.get("schoolAdmin/getSupplierList");
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.supplierList[res.date[i].id] = res.date[i];
        }
      } else {
      }
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
          this.categoryList[this.purchaseList[i].pid].product_name;
      }
      //触发更新问题
      this.tableData.push(null);
      this.tableData.pop(null);
      if (this.purchaseList.length > 0) {
        this.addTarget = this.tableData[0];
      }
      this.tmp = this.tableData;
    },
    async addPurchaseShow() {
      this.showAddPurchase = true;
    },
    async addPurchase() {
      if (this.addTarget.product_name == null || this.addTarget.product_name == "") {
        this.$message.warning("请输入采购商品名称");
        return;
      }
      if (this.addTarget.highest_price == '' || this.addTarget.highest_price == null) {
        this.$message.warning("请输入预算价格");
        return;
      }
      if (this.p == 0) {
        this.$message.warning("请先上传文件");
        return;
      }

      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      let tmp = {};
      tmp.id = null;
      tmp.cid = data.cid;
      tmp.sid = data.sid;
      tmp.tag = this.addTarget.tag;
      tmp.highest_price = this.addTarget.highest_price;
      tmp.product_name = this.addTarget.product_name;
      tmp.reason = this.addTarget.reason;
      tmp.purpose = this.addTarget.purpose;

      tmp.apply_time = new Date();
      tmp.pid = this.addTarget.pid;

      const { data: res } = await this.$http.post("purchaser/addPurchase", tmp);
      if (res.success) {
        this.$message.success(res.msg);
        this.getPurchaseList();
      } else {
        this.$message.error(res.msg);
      }
      this.showAddPurchase = false;
    },
    closeAddPurchase() {
      this.showAddPurchase = false;
    },
    async seePurchaseDialog(val) {
      this.seeTarget = val;
      this.showDetailPurchase = true;
    },
    closeDetailPurchase() {
      this.showDetailPurchase = false;
    },
    async delete1(val) {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      const { data: res } = await this.$http.get(
        "purchaser/deletePurchase?id=" + val.id
      );
      if (res.success) {
        this.$message.success(res.msg);
        this.getPurchaseList();
      } else {
        this.$message.error(res.msg);
      }
    },
    showAddBoard(val) {
      if (val.acceess != 11) {
        return;
      }
      this.addModel = val;
      this.addModel.rules = "先通过网上申请竞标,最低价中标";
      this.addModel.qualifications =
        "有正规营业执照，身份证复印件及其他资质证明";
      //this.addModel.purchase_name = val.product_name + "招标公告";
      this.showAddDialog = true;
    },
    cancelAdd() {
      this.addModel = {};
      this.showAddDialog = false;
    },
    async addBoard() {
      this.addModel.start_time = this.times[0];
      this.addModel.end_time = this.times[1];
      for (let i = 0; i < this.categoryListLi.length; i++) {
        if (this.category == this.categoryListLi[i].product_name) {
          this.addModel.pid = this.categoryListLi[i].pid;
          break;
        }
      }
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];

      this.addModel.cid = data.cid;
      const { data: res } = await this.$http.post("addPurBoard", this.addModel);
      if (res.success) {
        this.$message.success(res.msg);
        this.handleList(res.date);
      } else {
        this.$message.error(res.msg);
      }
    },

    fun1(val) {
      if (val.acceess == 1) {
        return "审核中";
      } else if (val.acceess == 11) {
        return "通过";
      }
      return "取消申请";
    },
    fun2(val) {
      if (val.purchase_name != null) {
        return "采购公告已经发布";
      }
      if (val.acceess == 11) {
        return "发布公告";
      }
      return "审核中";
    },
    async downLoad() {
      window.open(
        "http://localhost:8088/downloadResultFile/?id=" +
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
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      const maxPage = Math.ceil(this.tableData.length / this.pageSize);
      if (this.currentPage < maxPage) {
        this.currentPage++;
      }
    },
    handleSizeChange(val) {
      // console.log("handleSizeChange:" + val);
      this.pageSize = val;
      // this.getPurchaseList()  //发起分页请求
    },
    handleCurrentChange(val) {
      // console.log("handleCurrentChange:" + val);
      this.currentPage = val;
      // this.getPurchaseList()  //发起分页请求

    },
    form(val) {
      console.log("form:" + val);
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
  overflow-y: auto;
  height: 50%;
}

.el-dialog .is-fullscreen {
  width: 500px !important;
  /* 设置对话框宽度为500像素 */
  height: 300px !important;
  /* 设置对话框高度为300像素 */
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

#searchStyle {
  margin-left: 40px;
  z-index: 999;
}
</style>