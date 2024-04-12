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
            <el-select v-model="selects.Categories" placeholder="请选择">
              <el-option v-for="item in CategoriesMap" :key="item.pid" :value="item.product_name">
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
            <el-select v-model="selects.Academys" placeholder="请选择">
              <el-option v-for="item in AcademysMap" :key="item.cid" :value="item.academy_name">
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
    </div>

    <!--显示相关的审核数据-->
    <div class="main-content">
      <el-table :data="List" style="width: 100%;font-size: 15px;" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="简介" prop="Sub"></el-table-column>
        <el-table-column label="学院" prop="Sub"></el-table-column>
        <el-table-column label="类别" prop="Cat"></el-table-column>

        <el-table-column label="发布时间" prop="Date"></el-table-column>
        <el-table-column label="截至时间" prop="Deadline"></el-table-column>
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
        <h2>
          【学党史 强自律】副校长朱波强讲授“加强基层党组织体系建设
          提升基层党组织的领导力”专题党课
        </h2>
      </div>
      <div class="info">
        <span>2021年04月12日 机关党委 赵洋 点击数：49</span>
      </div>
      <div class="content">
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
        <p>
          攀枝花学院是教育部布点在川西南、滇西北唯一一所以工为主的综合性普通本科院校，
          坐落于著名的中国钒钛之都、阳光花城、康养胜地、四川南向门户、全国唯一以花命名的优秀旅游城市——攀枝花市。
          学校是全国100所应用型本科产教融合发展工程项目建设高校、四川省高新技术产业示范科研单位、四川省知识产权优势培育单位、
          四川省首批激励科技人员创新创业改革试点高校、四川省职务科技成果权属混合所有制改革试点单位、四川省思想政治工作先进单位、
          四川省兴川战略人才强省示范基地。
        </p>
      </div>
    </el-dialog>
    <el-dialog title="申请信息" :visible.sync="showDialogVisible" width="50%" fullscreen @close="showDialogClosed"
      class="dialog"></el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      productName: 0,
      currentPage: 0,
      date: "",
      AcademysMap: [],
      CategoriesMap: [],
      List: [],

      showDialogVisible: false,
      selects: {
        Academys: "",
        Categories: "",
        value1: [new Date(), new Date()],
        value2: "",
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
    this.getGoogsList();
  },
  methods: {
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
    async getGoogsList() {
      const { data: res } = await this.$http.post("purchasingList");
      const arry = res.date;
      // console.log("getGoogsList");
      if (res.success) {
        for (let i = 0; i < res.date.length; i++) {
          const obj = {};
          obj.Sub = arry[i].product_name;
          for (let j = 0; j < this.AcademysMap.length; j++) {
            if (this.AcademysMap[j].cid == arry[i].cid) {
              obj.Aca = this.AcademysMap[j].academy_name;
            }
          }
          for (let j = 0; j < this.CategoriesMap.length; j++) {
            if (this.CategoriesMap[j].pid == arry[i].pid) {
              obj.Cat = this.CategoriesMap[j].product_name;
            }
          }
          obj.Date = arry[i].date;
          obj.Deadline = arry[i].deadline;
          this.List.push(obj);
        }
        // console.log(this.List);
      }
      else {
        // console.log("purchasingList请求失败！");
      }
    },
    handleSizeChange() { },
    handleCurrentChange() { },
    showDialog(val) {
      this.showDialogVisible = true;
    },
    showDialogClosed() {
      // this.$refs.addFormRef.resetFields()
    },
    async search() {
      const { data: res } = await this.$http.post("purchasingList", this.selects);


      const arry = res.date;
    }
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