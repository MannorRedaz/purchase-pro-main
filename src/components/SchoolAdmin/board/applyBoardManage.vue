<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <div class="headers">

        <el-button @click="release()">发布入围公告</el-button>
      </div>
    </div>
    <!--显示相关的审核数据-->

    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="applyForBoardList" :key="applyForBoardList.id" style="width: 100%;font-size: 15px;" border
        stripe>
        <el-table-column label="序号" type="index"></el-table-column>
        <el-table-column label="公告名" prop="boardname"></el-table-column>
        <el-table-column label="开始时间" prop="startTime"></el-table-column>
        <el-table-column label="结束时间" prop="endTime"></el-table-column>

        <el-table-column label="操作"> <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="delete1(scope.row)">删除</el-button>
          </template></el-table-column>
      </el-table>
    </div>


    <!-- jieguo -->
    <el-dialog title="添加申请" :visible.sync="showDialogVisible">
      <el-form>
        <div>
          <el-form :model="applyForm">
            <label>公告名</label>
            <el-input v-model="applyForm.boardname" prefix-icon="iconfont icon-3702mima"></el-input>

            <span class="demonstration">时间选择</span>
            <el-date-picker v-model="value1" type="daterange" range-separator="至" start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>

          </el-form>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addApplyForList(false)">取 消</el-button>
        <el-button type="primary" @click="addApplyForList(true)">确定添加</el-button>
      </div>
    </el-dialog>



  </div>

</template>

<script>
export default {
  data() {
    return {
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
        rules:
          "本次固定资产成批拍卖，轮番报价，报价最高者中标，中标者需要在中标后签订合同，并在两个工作日内付清全部价款才能进行搬运。",
        auction_site: "攀枝花学院办公楼403",
        is_result: 0,
        others:
          "拍卖物的拆卸费、运输费、建筑物破坏恢复、垃圾清运及其它一切相关费用均由中标人自行承担。",
        acceess: null,
      },
      value1: '',
      applyForm: {
        boardname: '',
        startTime: new Date(),
        endTime: new Date(),

      },
      showResultDialog: false,
      showAddResultDialog: false,
      processVisible: false,
      supplierId: '',
      applyForBoardList: [],
      editModel: {},
      showEditDialog: false,
      times: [new Date(), new Date()],
      time2: [new Date(), new Date()],
      category: "",
      category1: "",

      resultForm: [],
      types: [
        { name: "结果公告", id: 2 },
        { name: "招标公告", id: 1 },
      ],
      newPwd: "",
      type_value: "",
      productName: 0,
      currentPage: 0,
      date: "",
      AcademysMap: [],
      CategoriesMap: [],
      List: [],
      clickTarget: {},
      count1: 0,
      showDialogVisible: false,
      showDialogVisible2: false,
      showAddDialog: false,
      selects: {
        academy: "",
        categories: "",
        value1: [new Date(), new Date()],
        type: "",
      },

      //设置默认时间
    };
  },
  created() {
    this.getAcademyList();
    this.getCategoriesList();
  },
  mounted() {
    this.getBoardList();
    this.getApplyBoard();
  },
  methods: {
    async getApplyBoard() {
      // console.log(2);
      const { data: res } = await this.$http.post("supplier/getApplyBoard");
      const arry = res.date;
      // console.log(arry);
      if (res.success) {
        this.applyForBoardList = res.date;
        this.applyForBoardList1 = res.date;
        for (let i = 0; i < this.applyForBoardList.length; i++) {
          this.applyForBoardList[i].startTime = this.$moment(
            this.applyForBoardList[i].startTime
          ).format("YYYY-MM-DD");
          this.applyForBoardList[i].endTime = this.$moment(
            this.applyForBoardList[i].endTime
          ).format("YYYY-MM-DD");

          // console.log("this.applyForBoardList[i].id");
          // console.log(this.applyForBoardList[i].id);
        }

        this.$message.success(res.msg);
      } else {
        // console.log("categories请求失败！");
      }
    },
    async addApplyForList(val) {
      if (val) {
        if (this.applyForm.boardname == null || this.applyForm.boardname == "") {
          this.$message.warning("请输入完整信息");
          return;
        }
        this.applyForm.startTime = this.value1[0];
        this.applyForm.endTime = this.value1[1];

        const { data: res } = await this.$http.post(
          "schoolAdmin/addApplyBoard",
          this.applyForm
        );
        if (res.success) {
          this.$message.success(res.msg);
        } else {
          this.$message.error(res.msg);
        }
        this.showDialogVisible = !this.showDialogVisible;
      } else {
        this.showDialogVisible = !this.showDialogVisible;
      }
    },
    async release() {
      this.showDialogVisible = !this.showDialogVisible;
    },
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
      // console.log("change");
      // console.log(file);
      this.form.file = file.raw;
      // console.log(this.form.file);
      // console.log(fileList);
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      // console.log("before upload");
      // console.log(file);
      let extension = file.name.substring(file.name.lastIndexOf(".") + 1);
      let size = file.size / 1024 / 1024;
      if (extension !== "pdf") {
        this.$notify.warning({
          title: "警告",
          message: `只能上传pdf文件`,
        });
      }
      if (size > 10) {
        this.$notify.warning({
          title: "警告",
          message: `文件大小不得超过10M`,
        });
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$notify.success({
        title: "成功",
        message: `文件上传成功`,
      });
    },
    // 文件上传失败时的钩子 err错误信息
    handleError(err, file, fileList) {
      // console.log(err);
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

    async addResultBoard() {

      const { data: res } = await this.$http.get("addResultBoard?id=" + this.supplierId);
      if (res.success) {
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }


    },
    showAddResult(val) {
      this.clickTarget = val;
      this.supplierId = val.id;
      // console.log(this.supplierId);
      // console.log(this.supplierId);
      // console.log(this.supplierId);
      // console.log(this.supplierId);

      this.showAddResultDialog = !this.showAddResultDialog;
    },
    showProcess(val) {
      this.clickTarget = val;
      this.processVisible = !this.processVisible;
    },
    async getApplyCount(val) {
      const { data: res } = await this.$http.get("getApplyCount?id=" + val);
      return res.status;
    },
    async getMinimumPrice(val) {
      const { data: res } = await this.$http.get("getMinimumPrice?id=" + val);
      return res.status;
    },
    async edit() {
      // console.log(this.category1);
      // console.log(this.category1);

      // console.log(this.category1);

      if (this.category1 != null) {
        // console.log(this.category1);
        // console.log(this.category1);

        // console.log(this.category1);
        for (let i = 0; i < this.CategoriesMap.length; i++) {
          if (this.category1 == this.CategoriesMap[i].product_name) {
            this.editModel.pid = this.CategoriesMap.pid;
            break;
          }
        }
      }
      this.editModel.start_time = this.time2[0];
      this.editModel.end_time = this.time2[1];
      const { data: res } = await this.$http.post(
        "editPurBoard",
        this.editModel
      );
      if (res.success) {
        this.$message.success(res.msg);
        boardListDeal(res.date);
        this.showEditDialog = false;
      } else {
        this.$message.error(res.msg);
        this.showEditDialog = false;
      }
      this.showEditDialog = false;
    },
    cancelEdit() {
      this.showEditDialog = false;
    },
    async delete1(val) {
      const { data: res } = await this.$http.get("schoolAdmin/deleteApplyBoard?id=" + val.id);
      if (res.success) {
        this.$message.success(res.msg);
        this.boardListDeal(res.date);
      } else {
        this.$message.error(res.msg);
      }
    },
    async editDialog(val) {
      this.editModel = val;
      for (let i = 0; i < this.CategoriesMap.length; i++) {
        if ((val.pid = this.CategoriesMap.pid)) {
          this.category1 == this.CategoriesMap[i].product_name;
          break;
        }
      }
      this.time2[0] = val.start_time;
      this.time2[1] = val.end_time;
      this.showEditDialog = true;
    },
    async addBoardShow() {
      this.showAddDialog = true;
    },
    async addBoard() {
      this.addModel.start_time = this.times[0];
      this.addModel.end_time = this.times[1];
      this.addModel.acceess = 11;
      for (let i = 0; i < this.CategoriesMap.length; i++) {
        if (this.category == this.CategoriesMap[i].product_name) {
          this.addModel.pid = this.CategoriesMap[i].pid;
          break;
        }
      }
      const { data: res } = await this.$http.post("addPurBoard", this.addModel);
      if (res.success) {
        this.$message.success(res.msg);
        this.boardListDeal(res.date);
        this.showAddDialog = !this.showAddDialog;
      } else {
        this.$message.error(res.msg);
      }
    },
    cancelAdd() {
      this.showAddDialog = false;
    },
    async downLoad() {
      window.open(
        "http://localhost:8088/downloadResultFile/?id=" +
        this.clickTarget.result_id
      );
    },

    async getAcademyList() {
      // console.log(1);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      // console.log(res);
      if (res.success) {
        this.AcademysMap = arry;
      } else {
        // console.log("academys请求失败！");
      }
    },
    async getCategoriesList() {
      // console.log(2);
      const { data: res } = await this.$http.post("categories");
      const arry = res.date;
      if (res.success) {
        this.CategoriesMap = arry;
        // console.log(this.CategoriesMap);
      } else {
        // console.log("categories请求失败！");
      }
    },
    async getBoardList() {
      // console.log("getGoogsList");
      await this.getAcademyList;
      await this.getCategoriesList;

      const { data: res } = await this.$http.get(
        "getBoardListByCid?cid=" + this.addModel.cid
      );

      // console.log("getGoogsList");
      if (res.success) {
        this.$message.success(res.msg);
        this.boardListDeal(res);
      } else {
        // console.log("purchasingList请求失败！");
      }
    },
    async boardListDeal(res) {
      let List2 = [];
      this.List = List2;
      const arry = res.date;
      this.count1 = res.date.length;
      for (let i = 0; i < res.date.length; i++) {
        const obj = {};
        obj.Sub = arry[i].product_name;
        for (let j = 0; j < this.AcademysMap.length; j++) {
          if (this.AcademysMap[j].cid == arry[i].cid) {
            obj.academy = this.AcademysMap[j].academy_name;
          }
        }
        for (let j = 0; j < this.CategoriesMap.length; j++) {
          if (this.CategoriesMap[j].pid == arry[i].pid) {
            obj.category = this.CategoriesMap[j].product_name;
          }
        }
        if (arry[i].start_time != null)
          obj.start_time = this.$moment(arry[i].start_time).format(
            "YYYY-MM-DD"
          );
        if (arry[i].end_time != null)
          obj.end_time = this.$moment(arry[i].end_time).format("YYYY-MM-DD");
        obj.name = arry[i].purchase_name;
        obj.publish_time = this.$moment(arry[i].publish_time).format(
          "YYYY-MM-DD"
        );
        obj.product_name = arry[i].product_name;
        obj.purchase_name = arry[i].purchase_name;
        obj.highest_price = arry[i].highest_price;
        obj.specified = arry[i].specified;
        obj.reason = arry[i].reason;
        obj.site = arry[i].site;
        obj.site_inspection_time = this.$moment(
          arry[i].site_inspection_time
        ).format("YYYY-MM-DD");
        obj.qualifications = arry[i].qualifications;
        obj.notifiedcation = arry[i].notifiedcation;
        obj.errol_contract = arry[i].errol_contract;
        obj.errol_tel = arry[i].errol_tel;
        obj.errol_type = arry[i].errol_type;
        obj.inspection_contract = arry[i].inspection_contract;
        obj.inspection_tel = arry[i].inspection_tel;
        obj.click_count = arry[i].click_count;
        obj.file_place = arry[i].file_place;
        obj.is_result = arry[i].is_result;
        obj.acceess = arry[i].acceess;
        obj.rules = arry[i].rules;
        obj.auction_site = arry[i].auction_site;
        obj.others = arry[i].others;
        obj.id = arry[i].id;
        obj.title = arry[i].title;
        obj.deadline = arry[i].deadline;
        obj.pid = arry[i].pid;
        obj.sid = arry[i].sid;
        obj.cid = arry[i].cid;
        obj.purpose = arry[i].purpose;
        obj.tag = arry[i].tag;
        obj.acceess = arry[i].acceess;
        obj.applyCount = await this.getApplyCount(arry[i].id);
        obj.minimumPrice = await this.getMinimumPrice(arry[i].id);

        if (obj.purchase_name != null && obj.purchase_name != "") {
          this.List.push(obj);
        }
      }
    },
    handleSizeChange(res) { },
    handleCurrentChange() { },
    async showDialog(val) {
      if (val.errol_type === 3) {
        // console.log(val.errol_type);


        const { data: res } = await this.$http.get("getResult?id=" + val.id);
        val.realy_price = res.date[0].reality_price;
        val.actual_product_name = res.date[0].actual_product_name;
        val.supplierId = res.date[0].id;
        val.result_id = res.date[0].id;
        this.getSupplier(val);
        this.showDialogVisible2 = true;
      } else {
        this.clickTarget = val;

        this.showDialogVisible = true;
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
    showDialogClosed() {
      // this.$refs.addFormRef.resetFields()
    },
    async search() {

      const data = JSON.parse(window.sessionStorage.getItem('data'));

      this.selects.academy = "攀枝花学院";
      const { data: res } = await this.$http.post("boardScreen", this.selects);
      this.boardListDeal(res);
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