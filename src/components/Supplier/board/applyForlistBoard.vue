<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <el-button @click="applyTableDown">推荐表下载</el-button>
    </div>

    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="applyForBoardList" key="applyForBoardList.id" style="width: 100%; font-size: 15px" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="公告名" prop="boardname"></el-table-column>
        <el-table-column label="开始时间" prop="startTime"></el-table-column>
        <el-table-column label="结束时间" prop="endTime"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="ShowApplyDialog(scope.row)">入围申请</el-button>
          </template></el-table-column>
      </el-table>
    </div>
    <el-dialog title="添加申请" :visible.sync="applyDialog">
      <el-form>
        <div>
          <el-form :model="form">
            <el-form-item label="上传推荐表" :label-width="formLabelWidth">
              <el-upload ref="upload" action="http://localhost:8088/uploadApplyFile" :limit="limitNum"
                :auto-upload="false" accept=".pdf" :before-upload="beforeUploadFile" :on-change="fileChange"
                :on-exceed="exceedFile" :on-success="handleSuccess" :on-error="handleError" :file-list="fileList"
                :data="{ id: this.supplierId }">
                <el-button size="small" plain>选择文件</el-button>
                <div slot="tip" class="el-upload__tip">
                  只能上传pdf文件，且不超过5M
                </div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button size="small" type="primary" @click="uploadFile">立即上传</el-button>
              <el-button size="small">取消</el-button>
            </el-form-item>
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
import axios from "axios";

export default {
  data() {
    return {
      checkedCities: ["上海", "北京"],
      cities: [],
      upUrl: "http://localhost:8088/uploadApplyFile",
      supplierId: "",
      limitNum: 1,
      formLabelWidth: "100px",
      form: {
        file: "",
      },
      fileList: [],
      tableData: [],
      p: 0,
      dataForm: {
        id: "",
        date: "",
        sid: "",
        scid: 1,
        recommends: "",
        academy_access: 0,
        school_administrator_access: 0,
        causes: "",
        tag: "",
      },
      applyForBoardList: [],
      applyForBoardList1: [],

      applyDialog: false,
      academyList: [],
      applyForList: [],
    };
  },
  mounted() {
    this.inital();
  },
  methods: {
    async inital() {
      // 获取申请列表
      await this.getApplyBoard();
      // 获取产品类别
      await this.getAcademyList();
      await this.getApplyForList();
    },

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
        }
        this.$message.success(res.msg);
      } else {
        // console.log("categories请求失败！");
      }
    },
    async getAcademyList() {
      // console.log(2);
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      for (let i = 0; i < res.date.length; i++) {
        this.cities[i] = res.date[i].academy_name;
      }
      // console.log(arry);
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
          // console.log(i);
        }
      } else {
        // console.log("categories请求失败！");
      }
    },

    async applyShortList() {
      // console.log(2);
      const { data: res } = await this.$http.post(
        "supplier/applyShortList?id=",
        this.dataForm
      );
      this.tableData = res.date;
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.tableData[i].process = res.date[i].school_administrator_access;
        }
      } else {
        // console.log("categories请求失败！");
      }
    },
    async getApplyForList() {
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      this.supplierId = data.data[0].id;
      const res = await this.$http.get(
        "supplier/getApplyForList?sid=" + this.supplierId
      );
      this.tableData = res.date;
      if (res.success) {
        for (let i = 0; i < this.tableData.length; i++) {
          this.tableData[i].date1 = this.$moment(this.tableData[i].date).format(
            "YYYY-MM-DD"
          );
          this.tableData[i].process = this.tableData[i].school_administrator_access;
        }
      } else {
        // console.log("categories请求失败！");
      }
    },

    async delApplyForList(val) {
      const { data: res } = await this.$http.get(
        "supplier/applyShortListDelete?id=" + this.supplierId
      );
      if (res.success) {
        this.$message.success(res.msg);
      } else {
        this.$message.error(res.msg);
      }
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
      if (extension != "pdf") {
        this.$notify.warning({
          title: "警告",
          message: `只能上传pdf文件`,
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
    async addApplyForList(val) {
      if (this.p == 0) {
        this.$message.warning("请上传文件");
        return;
      }
      if (val) {
        this.dataForm.date = new Date();
        this.dataForm.sid = this.supplierId;
        this.dataForm.scid = 1;
        const { data: res } = await this.$http.post(
          "supplier/applyShortList",
          this.dataForm
        );
        if (res.success) {
          this.$message.success(res.msg);
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.applyDialog = !this.applyDialog;
      }
    },
    compareTime(a, b) {
      var arr = a.split("-"); //log [2016,04,06]
      var start = new Date(arr[0], arr[1] - 1, arr[2]);
      var starts = start.getTime(); //输出时间戳进行对比
      var arrs = b.split("-");
      var end = new Date(arrs[0], arrs[1] - 1, arrs[2]);
      var ends = end.getTime();
      var weekDay = [
        "星期天",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ];
      //getDay() 方法可返回表示星期的某一天的数字。
      // console.log(
      //   "开始时间是" +
      //     weekDay[start.getDay()] +
      //     "\r\n" +
      //     "结束时间是" +
      //     weekDay[end.getDay()]
      // );
      var now = new Date();
      // console.log("现在是" + weekDay[now.getDay()]);
      if (starts >= ends) {
        // console.log("开始时间大于结束时间");
        return true;
      } else {
        return false;

        // console.log("开始时间小于结束时间");
      }
    },
    ShowApplyDialog(val) {
      const data = JSON.parse(window.sessionStorage.getItem("data"));
      if (data.data[0].access) {
        this.$message.warning("你已经通过申请");
        return;
      }
      let date = this.$moment(new Date()).format("YYYY-MM-DD");
      //要不为null 且长度大于一
      if (this.tableData[0] != null && this.tableData.length > 0) {
        this.$message.warning("你已经申请过，如需重新申请请删除原来申请表");
        return;
      }
      if (date >= val.startTime && date <= val.endTime) {
        this.applyDialog = !this.applyDialog;
      } else {
        this.$message.warning("不在时间范围内");
      }
    },
    //推荐表下载
    applyTableDown() {
      window.open("http://localhost:8088/applyFileDown");
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