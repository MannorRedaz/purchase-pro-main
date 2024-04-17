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
              <el-option v-for="item in CategoriesMap" :key="item.pid" :value="item.product_name">
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

      <!--搜索-->
      <div class="buttons-box">
        <el-button type="primary" @click="search">搜索</el-button>
      </div>

      <!-- <div class="grade-box">
        <div class="grade"></div>
        <template>
          <el-button @click="addBoardShow"> 添加公告 </el-button>
        </template>
      </div> -->
    </div>
    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="List" style="width: 100%; font-size: 15px" border stripe>
        <el-table-column type="index"></el-table-column>

        <el-table-column label="公告名" prop="name"></el-table-column>
        <el-table-column label="发布学院" prop="academy"></el-table-column>
        <el-table-column label="商品类别" prop="category"></el-table-column>
        <el-table-column label="发布时间" prop="start_time"></el-table-column>
        <el-table-column label="截至时间" prop="end_time"></el-table-column>
        <el-table-column label="竞标人数" prop="applyCount"></el-table-column>
        <el-table-column label="最低价" prop="minimumPrice"></el-table-column>
        <el-table-column label="查看">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showDialog(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="编辑">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="editDialog(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="删除">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="delete1(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="查看竞标详情">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showBidding(scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="发布结果公告">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showAddResult(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="page-part">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage" :page-size="pagesize" :page-sizes="[50]"
        layout="total,sizes, prev, pager, next" :total="List.length">
      </el-pagination>
    </div>
    <!-- jieguo -->
    <el-dialog title="竞标详情" :visible.sync="showResultDialog" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <div>
        <br />
        <el-tag style="font-size: 20px">以下是出价详情：</el-tag>
        <el-table :data="resultList" style="width: 100%; font-size: 15px" max-height="300px" border stripe>
          <el-table-column type="index"></el-table-column>
          <el-table-column label="公司名称" prop="tag"></el-table-column>
          <el-table-column label="申请产品" prop="product_name"></el-table-column>
          <el-table-column label="出价" prop="realy_price"></el-table-column>
          <el-table-column label="申请日期" prop="">{{
            this.$moment(resultList.data).format("YYYY-MM-DD")
          }}</el-table-column>

          <el-table-column label="产品描述" prop="prescribe"></el-table-column>
        </el-table>
      </div>
    </el-dialog>

    <el-dialog title="进度" :visible.sync="processVisible" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <el-form label-width="120px" :model="addModel" size="small" label-position="right">
        <el-form-item label="竞标人数" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.purchase_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品名称 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.product_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品数量 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.tag"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="最低价" prop="">
          <el-input v-model="addModel.reason" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 添加结果公告 -->

    <el-dialog title="添加结果公告" :visible.sync="showAddResultDialog">
      <el-form>
        <div>
          <el-form :model="form">
            <el-form-item label="指定中标公司">
              <el-select v-model="obj" placeholder="请选择">
                <el-option v-for="item in resultList" :key="item.id" :label="item.tag" :value="item.id">
                </el-option>
              </el-select>
              <el-lable style="color:red">注意！如果不选择，则默认为最低价中标</el-lable>
            </el-form-item>

            <el-form-item label="上传推荐表" :label-width="formLabelWidth">
              <el-upload ref="upload" action="http://localhost:8088/upLoadResultFile1"
                :limit="limitNum" :auto-upload="false" accept=".pdf" :before-upload="beforeUploadFile"
                :on-change="fileChange" :on-exceed="exceedFile" :on-success="handleSuccess" :on-error="handleError"
                :file-list="fileList" :data="{ id: this.supplierId }">
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
        <el-button @click="showAddResult(false)">取 消</el-button>
        <el-button type="primary" @click="addResultBoard()">确定添加</el-button>
      </div>
    </el-dialog>
    <!-- paimai -->

    <el-dialog title="采购公告" :visible.sync="showDialogVisible" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <div>
        <div class="tit">{{ clickTarget.purchase_name }}</div>
        <div class="tit" style="font-size: 20px; margin-top: 20px"></div>
        <!-- {{ List[0].publish_time }} -->
        <div class="tit">
          发布时间：
          {{ this.$moment(clickTarget.publish_time).format("YYYY-MM-DD") }}
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div id="vsb_content" class="content">
          <p><span style="font-size: 16px"></span></p>
          <!-- <p class="MsoNormal" style="margin: 0cm 0cm 0px;text-align: center;line-height: 35px;mso-line-height-rule: exactly"><span style="color: rgb(51, 51, 51);font-family: 黑体;font-size: 24px;">攀枝花学院废旧铁窗处置拍卖公告<span > -->
          <!--?xml:namespace prefix="o" ns="urn:schemas-microsoft-com:office:office" /-->

          <!-- </span></p> -->
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              mso-line-height-rule: exactly;
            ">
            <span lang="EN-US" style="color: rgb(51, 51, 51); font-family: 黑体; font-size: 24px">
            </span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            ">
            <span style="color: rgb(51, 51, 51); font-family: 宋体; font-size: 20px">{{ clickTarget.title }} </span><span
              lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              ">
              <br /><span style="font-family: 宋体">
                <strong style="mso-bidi-font-weight: normal">
                  <span style="mso-spacerun: yes"> &nbsp; </span></strong></span> </span><strong
              style="mso-bidi-font-weight: normal">
              <span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">
                一、采购目的</span></strong>
            <strong style="mso-bidi-font-weight: normal">
              <span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">
              </span></strong>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            ">
            <span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 21px;
                mso-ascii-theme-font: minor-fareast;
                mso-fareast-theme-font: minor-fareast;
                mso-hansi-theme-font: minor-fareast;
              ">{{ clickTarget.reason
              }}<strong style="mso-bidi-font-weight: normal"><span lang="EN-US"> </span></strong></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              text-align: left;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
            ">
            <strong style="mso-bidi-font-weight: normal"><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">二、采购详情</span></strong><br />
            <span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1、发布学院:</span>
            <span lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              ">{{ clickTarget.academy }}</span>
            <br /><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、采购需求:</span><span style="font-family: 宋体"><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">{{ clickTarget.product_name }}</span><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">（{{ clickTarget.purpose }}）</span><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">
              </span></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            ">
            <strong style="mso-bidi-font-weight: normal"><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">三、竞拍要求</span></strong><strong style="mso-bidi-font-weight: normal"><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "><br /><span style="font-family: 宋体"></span></span></strong><span style="font-family: 宋体"><span
                lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "><span style="mso-spacerun: yes">
                  &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
                  &nbsp;&nbsp; </span><strong style="mso-bidi-font-weight: normal">1</strong></span><strong
                style="mso-bidi-font-weight: normal"><span style="
                    color: rgb(51, 51, 51);
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                  ">、竞拍资格：</span></strong><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">{{ clickTarget.qualifications }}</span><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">
              </span></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            ">
            <span style="font-family: 宋体"><strong style="mso-bidi-font-weight: normal"><span lang="EN-US" style="
                    color: rgb(51, 51, 51);
                    font-family: 'ˎ̥', 'serif';
                    font-size: 20px;
                  ">&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; 2</span></strong><strong
                style="mso-bidi-font-weight: normal"><span style="
                    color: rgb(51, 51, 51);
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                  ">、竞拍报名</span></strong></span><span lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "><br /><span style="font-family: 宋体"><span style="mso-spacerun: yes">&nbsp; &nbsp; &nbsp;&nbsp;
                  &nbsp;&nbsp;
                </span></span></span><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">1）、截止时间：</span><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              "><strong>{{
                this.$moment(clickTarget.end_time).format("YYYY-MM-DD")
              }}</strong></span>
            <a name="_GoBack"></a><br />
            <span style="font-family: 宋体"><span style="mso-spacerun: yes">
                &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
              </span></span>
            <span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">2）、报名方式：使用网站申请或者直接到学校进行申请</span><span style="font-family: 宋体">{{ clickTarget.auction_site }}
            </span><span lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "><br /><span style="font-family: 宋体"><span style="mso-spacerun: yes">
                  &nbsp; &nbsp;&nbsp; &nbsp;</span><strong style="mso-bidi-font-weight: normal"><span
                    style="mso-spacerun: yes"> </span>3</strong></span></span><strong
              style="mso-bidi-font-weight: normal"><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">、保证金：</span></strong><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">参与投标商在开拍前需缴纳现金保证金</span><span style="font-family: 宋体"><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">2000</span><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">元。未中标者当场退还保证金，若中标者弃标保证金不予退还。所有物资（含待拆除的学生课桌椅）</span></span><span style="
                color: black;
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
                mso-themecolor: text1;
              ">搬运完成、垃圾清运完毕、建筑物破坏恢复后退还保证金。</span><span lang="EN-US" style="
                color: black;
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
                mso-themecolor: text1;
              "><br /><span style="font-family: 宋体"></span></span><span style="font-family: 宋体"><span lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                "><span style="mso-spacerun: yes"> &nbsp;</span><span style="mso-spacerun: yes"> </span></span><span
                lang="EN-US" style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                "><span style="mso-spacerun: yes"> </span><strong style="mso-bidi-font-weight: normal">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四</strong></span><strong style="mso-bidi-font-weight: normal"><span
                  style="
                    color: black;
                    font-family: 宋体;
                    font-size: 20px;
                    mso-ascii-font-family: ˎ̥;
                    mso-themecolor: text1;
                  ">、成交原则：</span></strong><span style="
                  color: black;
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                  mso-themecolor: text1;
                ">{{ clickTarget.rules }}</span><span lang="EN-US" style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                ">
              </span></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 2;
            ">
            <span style="font-family: 宋体"><span lang="EN-US" style="
                  color: black;
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                  mso-themecolor: text1;
                ">
                五</span><span style="
                  color: black;
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                  mso-themecolor: text1;
                ">、其他事宜：</span></span><span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">{{ clickTarget.others }}</span>
          </p>

          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 40px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 1.98;
            "></p>
          <p><span style="font-size: 16px"></span></p>
          <p class="MsoNormal" style="
              margin: 0cm 0cm 0px;
              line-height: 35px;
              text-indent: 32px;
              mso-line-height-rule: exactly;
              mso-char-indent-count: 1.98;
            ">
            <span lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 16px;
                mso-bidi-font-size: 13px;
              "><br /><span style="font-family: 宋体"></span></span><span style="font-family: 宋体"><span lang="EN-US"
                style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp;</span><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                "></span><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">
                <span style="mso-spacerun: yes">
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  &nbsp;</span>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp;&nbsp; &nbsp;</span><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                ">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp;&nbsp;攀枝花学院</span></span><span lang="EN-US" style="
                color: rgb(51, 51, 51);
                font-family: 'ˎ̥', 'serif';
                font-size: 20px;
              "><br /><span style="font-family: 宋体">
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                &nbsp;<span style="mso-spacerun: yes">
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  &nbsp; &nbsp; &nbsp; &nbsp;</span><span style="mso-spacerun: yes"> </span>2021</span></span>
            <span style="
                color: rgb(51, 51, 51);
                font-family: 宋体;
                font-size: 20px;
                mso-ascii-font-family: ˎ̥;
              ">年</span><span style="font-family: 宋体"><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">4</span><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">月</span><span lang="EN-US" style="
                  color: rgb(51, 51, 51);
                  font-family: 'ˎ̥', 'serif';
                  font-size: 20px;
                ">14</span><span style="
                  color: rgb(51, 51, 51);
                  font-family: 宋体;
                  font-size: 20px;
                  mso-ascii-font-family: ˎ̥;
                ">日</span><strong style="mso-bidi-font-weight: normal"><span lang="EN-US" style="
                    color: rgb(51, 51, 51);
                    font-family: 'ˎ̥', 'serif';
                    font-size: 20px;
                  ">
                </span></strong></span>
          </p>
          <p><span style="font-size: 16px"></span></p>
        </div>
        <div id="div_vote_id"></div>
      </div>
    </el-dialog>
    <!-- tianjia -->
    <el-dialog title="添加公告" :visible.sync="showAddDialog" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <el-form label-width="120px" :model="addModel" size="small" label-position="right">
        <el-form-item label="公告名" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.purchase_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品名称 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.product_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品数量 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.tag"></el-input>
          </el-col>
        </el-form-item>
        <!-- <el-form-item label="拍卖标的" prop="">
          <el-input v-model="addModel.reason" auto-complete="off"></el-input>
        </el-form-item> -->
        <el-form-item label="采购目的" prop="">
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
              <el-option v-for="item in CategoriesMap" :key="item.pid" :value="item.product_name">
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
    <!-- xiugai -->
    <el-dialog title="修改公告" :visible.sync="showEditDialog" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <el-form label-width="120px" :model="editModel" size="small" label-position="right">
        <el-form-item label="公告名" prop="">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.purchase_name"></el-input>
          </el-col>
          <el-col :span="2"> 产品名称 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.product_name"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="拍卖标的" prop="">
          <el-input v-model="editModel.reason" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择开始和结束时间" prop="">
          <el-col :span="8">
            <el-date-picker v-model="time2" type="datetimerange" range-separator="至" start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-col>
          <el-col :span="2">产品类别</el-col>
          <el-col :span="3">
            <el-select v-model="category1" placeholder="请选择">
              <el-option v-for="item in CategoriesMap" :key="item.pid" :value="item.product_name">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="2"> 预算价格 </el-col>
          <el-col :span="3">
            <el-input auto-complete="off" v-model="editModel.highest_price"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="现场看样时间" prop="">
          <el-col :span="8">
            <el-date-picker v-model="editModel.site_inspection_time" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-col>
          <el-col :span="2"> 现场看样地点 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.site"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="报名联系人">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.errol_contract"></el-input>
          </el-col>
          <el-col :span="2"> 报名联系人电话 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.errol_tel"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="看样联系人">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.inspection_contract"></el-input>
          </el-col>
          <el-col :span="2"> 看样联系人电话 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.inspection_tel">></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="拍卖地点">
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.auction_site"></el-input>
          </el-col>
          <el-col :span="2"> 拍卖时间 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="editModel.deadline"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="成交原则">
          <el-input maxlength="18" v-model="editModel.rules"></el-input>
        </el-form-item>
        <el-form-item label="竞标资格">
          <el-input maxlength="18" v-model="editModel.qualifications"></el-input>
        </el-form-item>
        <el-form-item label="其他事项">
          <el-input v-model="editModel.others"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="primary" @click="edit">修改公告</el-button>
        <el-button size="mini" type="warning" @click="cancelEdit">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      limitNum:5,
      fileList:[],
      form:"",
      formLabelWidth: "120px",
      pagesize:5,
      obj: "",
      resultList: [],
      showResultDialog: false,
      showAddResultDialog: false,
      processVisible: false,
      supplierId: "",

      editModel: {},
      showEditDialog: false,
      times: [new Date(), new Date()],
      time2: [new Date(), new Date()],
      category: "",
      category1: "",

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
    };
  },
  created() {
    this.getAcademyList();
    this.getCategoriesList();
  },
  mounted() {
    this.getBoardList();
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
      // console.log(this.obj);
      // console.log("this.obj");
      if (this.obj.length == 0 || this.obj == null) {
        //  console.log("this.obj1");
        const { data: res } = await this.$http.get(
          "addResultBoard?id=" + this.supplierId
        );

        if (res.success) {
          this.$message.success(res.msg);
        } else {
          this.$message.error(res.msg);
        }
      } else {
        //  console.log("this.obj2");

        for (let i = 0; i < this.resultList.length; i++) {
          if (this.obj == this.resultList[i].id) {
            const { data: res1 } = await this.$http.post(
              "addResBoard",
              this.resultList[i]
            );
            if (res1.success) {
              this.$message.success(res1.msg);
            } else {
              this.$message.error(res1.msg);
            }
            break;
          }
        }
      }
    },
    async getApplyList(id) {
      const { data: res } = await this.$http.get("getApplyList?id=" + id);
      this.resultList = res.date;
    },
    showBidding(val) {
      this.getApplyList(val.id);
      this.showResultDialog = !this.showResultDialog;
    },
    showAddResult(val) {
      this.clickTarget = val;
      this.supplierId = val.id;
      this.getApplyList(val.id);
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
      const { data: res } = await this.$http.post("deletePurBoard", val);
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
        obj.purpose = arry[i].purpose;

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
        // console.log(val.errol_type);
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
      const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];

      this.selects.academy = "攀枝花学院";
      const { data: res } = await this.$http.post("boardScreen", this.selects);
      this.boardListDeal(res);
    },
  },
};
</script>

<style scoped lang='less'>
.buttons-box{
  margin-left: 80px;
}
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