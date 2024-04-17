<template>
  <div>
    <div>
      <!-- <el-button @click="ShowApplyDialog">入围申请</el-button> -->
      <el-button @click="applyTableDown">推荐表下载</el-button>

      <div>
        <el-table :data="tableData" :key="tableData.id" style="width: 70%" stripe>
          <el-table-column prop="date1" label="申请时间" style="width: 100%;font-size: 15px;"> </el-table-column>
          <el-table-column align="right" label="是否通过">
            <template slot-scope="scope">
              <el-button v-if="scope.row.process == 1" type="primary" icon="el-icon-edit">通过</el-button>
              <el-button v-else type="primary" icon="el-icon-edit">未通过</el-button>
            </template>
          </el-table-column>
          <el-table-column align="right" label="重新申请">
            <template slot-scope="scope">
              <el-button @click="delApplyForList(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>
    </div>

    <!-- <el-dialog title="申请表" :visible.sync="applyDialog">
      <el-form :model="dataForm">        
        <el-form-item>
        <div>
          <el-form :model="form">
            <el-form-item label="上传推荐表" :label-width="formLabelWidth">
              <el-upload
                ref="upload"
                action="http://localhost:8088/uploadApplyFile"
                :limit="limitNum"
                :auto-upload="false"
                accept="application/msword"
                :before-upload="beforeUploadFile"
                :on-change="fileChange"
                :on-exceed="exceedFile"
                :on-success="handleSuccess"
                :on-error="handleError"
                :file-list="fileList"
                :data="{ id: this.supplierId }"
              >
                <el-button size="small" plain>选择文件</el-button>
                <div slot="tip" class="el-upload__tip">
                  只能上传word文件，且不超过5M
                </div>
              </el-upload>
            </el-form-item>
            <el-form-item>
              <el-button size="small" type="primary" @click="uploadFile"
                >立即上传</el-button
              >
              <el-button size="small">取消</el-button>
            </el-form-item>
            <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="dataForm.tag" autocomplete="off"></el-input>
        </el-form-item>
          </el-form>
        </div>
        </el-form-item>
        <el-form-item>
          <div slot="footer" class="dialog-footer">
        <el-button @click="addApplyForList(false)">取 消</el-button>
        <el-button type="primary" @click="addApplyForList(true)"
          >确 定</el-button
        >
      </div>
        </el-form-item>
      </el-form>
      
    </el-dialog> -->



    <el-dialog title="添加申请" :visible.sync="applyDialog">
      <el-form>
        <div>
          <el-form :model="form">
            <el-form-item label="上传推荐表" :label-width="formLabelWidth">
              <el-upload ref="upload" action="http://localhost:8088/uploadApplyFile" :limit="limitNum"
                :auto-upload="false" accept=".pdf,.doc" :before-upload="beforeUploadFile" :on-change="fileChange"
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
      dataForm: {
        id: '',
        date: '',
        sid: '',
        scid: 1,
        recommends: '',
        academy_access: 0,
        school_administrator_access: 0,
        causes: '',
        tag: '',

      },
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
      await this.getAcademyList();
      await this.getApplyForList();
    },
    async getAcademyList() {
      const { data: res } = await this.$http.post("academys");
      const arry = res.date;
      for (let i = 0; i < res.date.length; i++) {
        this.cities[i] = res.date[i].academy_name;
      }
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.academyList[res.date[i].cid] = res.date[i];
        }
      } else {
        // console.log("categories请求失败！");
      }
    },
    async applyShortList() {
      const { data: res } = await this.$http.post("supplier/applyShortList?id=", this.dataForm);
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.tableData = res.date[i];
          this.tableData[i].process = res.date[i].school_administrator_access;
        }
      } else {
      }
    },
    async getApplyForList() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      this.supplierId = data.id;
      // const { data: res } = await this.$http.get("supplier/getApplyForList?sid=" + this.supplierId);
      const { data: res } = await this.$http.get("supplier/getApplyForList?sid=" + this.supplierId);
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.tableData[i] = res.date[i];
          this.tableData[i].date1 = this.$moment(res.date[i].date).format("YYYY-MM-DD");
          this.tableData[i].process = res.date[i].school_administrator_access;
        }
      } else {

      }
    },

    async delApplyForList(val) {

      const { data: res } = await this.$http.get("supplier/applyShortListDelete?id=" + this.supplierId);
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
      if (val) {
        this.dataForm.date = new Date();
        this.dataForm.sid = this.supplierId;
        this.dataForm.scid = 1;
        const { data: res } = await this.$http.post("supplier/applyShortList", this.dataForm);
        if (res.success) {
          this.$message.success(res.msg);
        } else {
          this.$message.error(res.msg);
        }
      } else {
        this.applyDialog = !this.applyDialog;
      }
    },
    ShowApplyDialog() {
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
      if (data.access) {
        this.$message.warning("你已经通过申请");
        return;
      }
      this.applyDialog = !this.applyDialog;
    },
    //推荐表下载
    applyTableDown() {
      window.open("http://localhost:8088/applyFileDown");
    },
  },
};
</script>

<style lang="less" scoped>
//卡片样式
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
}

//文本样式区
.name-role {
  font-size: 16px;
  padding: 5px;
  text-align: center;
}

.sender {
  text-align: center;
}

.registe-info {
  text-align: center;
  padding-top: 10px;
}

.personal-relation {
  font-size: 16px;
  padding: 0px 5px 15px;
  margin-right: 1px;
  width: 100%;
}

.relation-item {
  padding: 12px;
}

.dialog-footer {
  padding-top: 10px;
  padding-left: 10%;
}

//布局样式区
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
