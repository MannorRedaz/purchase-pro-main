<template>
  <!-- 交易完成 -->
  <div>
    <!--功能分区 == 页面上的查询/审核-->
    <div class="function-part">
      <!--输入物品名称-->
      <div class="product-name-box">
        <div class="product-name">商品名称:</div>
        <el-input v-model="productName" placeholder="请输入内容"> </el-input>
      </div>

      <!--选择交易类型-->
      <div class="transaction-type-box">
        <div class="transaction-type">商品类别:</div>
        <div>
          <template>
            <el-select v-model="Categories" placeholder="请选择">
              <el-option v-for="item in CategoriesOption" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </template>
        </div>
      </div>

      <!--选择物品类型-->
      <div class="product-type-box">
        <div class="product-type">发布来源：</div>
        <div>
          <template>
            <el-select v-model="Academys" placeholder="请选择">
              <el-option v-for="item in AcademysOption" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </template>
        </div>
      </div>

      <!--选择适合年级-->
      <div class="grade-box">
        <div class="grade">时间选择:</div>
        <div>
          <template>
            <div class="block">
              <el-date-picker v-model="date" type="monthrange" range-separator="至" start-placeholder="开始月份"
                end-placeholder="结束月份">
              </el-date-picker>
            </div>
          </template>
        </div>
      </div>

      <!--搜索-->
      <div class="buttons-box">
        <el-button type="primary">搜索</el-button>
      </div>
    </div>

    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="goodslist" style="width: 100%;font-size: 15px;" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="简介" prop="Sub"></el-table-column>
        <el-table-column label="学院" prop="Aca"></el-table-column>
        <el-table-column label="类别" prop="Cat"></el-table-column>
        <el-table-column label="时间" prop="Date"></el-table-column>
        <el-table-column label="查看">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showDialog(scope.row.id)"></el-button>
          </template>
        </el-table-column>
        <el-table-column label="申请">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showDialog(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="page-part">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page.sync="currentPage" :page-size="50" layout="total, prev, pager, next" :total="100">
      </el-pagination>
    </div>
    <el-dialog title="详细信息" :visible.sync="showDialogVisible" width="50%" fullscreen @close="showDialogClosed"
      class="dialog">
      <span class="myclose">
        <i class="el-icon-close" @click="showDialogVisible = false"></i>
      </span>
      <div class="tit">
        <h2>【学党史 强自律】副校长朱波强讲授“加强基层党组织体系建设 提升基层党组织的领导力”专题党课</h2>
      </div>
      <div class="info">
        <span>2021年04月12日 机关党委 赵洋 点击数：49</span>
      </div>
      <div class="content">
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
        <p>某大学是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。</p>
      </div>
    </el-dialog>
    <el-dialog title="申请信息" :visible.sync="showDialogVisible" width="50%" fullscreen @close="showDialogClosed"
      class="dialog"></el-dialog>
    <!--//查看信息-->
    <!-- <div>
      <el-dialog
        title="查看信息"
        :visible.sync="viewVisible"
        center
        width="40%"
      >
        <el-form :model="tradeViewForm" width="60%">
          <el-row>
            <el-col :span="8">
              <el-form-item label="物品名称：">
                <span v-text="tradeViewForm.title"></span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="物品类型：">
                <span v-text="tradeViewForm.item"></span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="新旧程度：">
            <el-radio-group v-model="tradeViewForm.usage" disabled>
              <el-radio-button label="100%新" value="0"></el-radio-button>
              <el-radio-button label="80%新" value="1"></el-radio-button>
              <el-radio-button label="60%新" value="2"></el-radio-button>
              <el-radio-button label="45%新" value="3"></el-radio-button>
            </el-radio-group>
          </el-form-item>

          <el-row>
            <el-col :span="8">
              <el-form-item label="适用年级：">
                <span v-text="tradeViewForm.grade"></span>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <el-form-item label="价格：">
                <span v-text="tradeViewForm.price"></span>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-button @click="viewVisible = false">取 消</el-button>
      </el-dialog>
    </div> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      productName: 0,
      currentPage: 0,
      date: '',
      AcademysMap: {},
      CategoriesMap: {},
      goodslist: [
        {
          Sub: '1111',
          Aca: '2222',
          Cat: '3333',
          Date: '4444'
        }
      ],
      CategoriesOption: [
        {
          label: 'huaxue',
          value: '化学'
        }
      ],
      Categories: '',
      AcademysOption: [
        {
          label: 'shuji',
          value: '数学与计算机学院'
        }
      ],
      Academys: '',
      showDialogVisible: false,
      //时间选择器的时间

      //设置默认时间
      value1: [new Date(), new Date()],
      value2: "",
    };
  },
  created() {
    this.getAcademyList()
    this.getCategoriesList()
  },
  mounted() {
    this.getGoogsList()
  },
  methods: {
    async getAcademyList() {
      //  console.log(1);
      const { data: res } = await this.$http.post('academys');
      const arry = res.date;
      // console.log(res);
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          this.AcademysOption.push({ label: arry[i].academy_name, value: arry[i].cid })
          this.AcademysMap[arry[i].cid] = arry[i].academy_name;
        }
      } else {
        // console.log("academys请求失败！");
      }
    },
    async getCategoriesList() {
      //  console.log(2);
      const { data: res } = await this.$http.post('categories');
      const arry = res.date;
      if (res.success) {
        // console.log(res.date);
        for (let i = 0; i < res.date.length; i++) {
          this.CategoriesOption.push({ label: arry[i].product_name, value: arry[i].pid })
          this.CategoriesMap[arry[i].pid] = arry[i].product_name;
        }
      } else {
        // console.log("categories请求失败！");
      }
    },
    async getGoogsList() {
      // console.log(3);
      // console.log(this.AcademysMap)
      // console.log(this.CategoriesMap)
      const { data: res } = await this.$http.post('purchasingList');
      const arry = res.date;
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          const obj = {};
          obj.Sub = arry[i].product_name;
          obj.Aca = this.AcademysMap[arry[i].cid];
          obj.Cat = this.CategoriesMap[arry[i].pid];
          obj.Date = arry[i].deadline;
          this.goodslist.push(obj);
        }
      } else {
        // console.log("purchasingList请求失败！");
      }
    },
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
    showDialog(val) {
      // console.log(scope.row.id);
      this.showDialogVisible = true;
    },
    showDialogClosed() {
      // this.$refs.addFormRef.resetFields()
    },
  }
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