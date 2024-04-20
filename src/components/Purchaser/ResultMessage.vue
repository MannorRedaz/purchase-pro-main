<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/sWelcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>中标通知</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <el-date-picker v-model="date" type="datetimerange" range-separator="至" start-placeholder="开始日期"
        end-placeholder="结束日期">
      </el-date-picker>
      <el-table :data="goodslist" style="width: 100%;font-size: 15px;" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="产品名称" prop="Sub"></el-table-column>
        <el-table-column label="联系人姓名" prop="Aca"></el-table-column>
        <el-table-column label="时间" prop="Cat"></el-table-column>
        <el-table-column label="申请编号" prop="Date"></el-table-column>
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
      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum" :page-sizes="[50]" :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next" :total="total">
      </el-pagination>
    </el-card>
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 获取用户的参数对象
      queryInfo: {
        query: '',
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pagesize: 2
      },
      total: 5,
      date: '',
      addForm: {
        id: '',
        name: '',
        price: '',
        details: ''
      },
      // 控制申请对话框的显示与隐藏
      addDialogVisible: false,
      tableData: [
        {
          id: '101',
          name: '电脑',
          type: '计算机',
          num: '30',
          school: '数学与计算机学院',
          application: 0
        },
        {
          id: '102',
          name: '电脑',
          type: '计算机',
          num: '30',
          school: '数学与计算机学院',
          application: 1
        }
      ]
    }
  },
  methods: {
    // 监听 pagesize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage
    },
    // 监听添加用户对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
      // console.log(this.addForm.details)
    },
    showAddDialog(id) {
      this.addForm.id = id
      this.addDialogVisible = !this.addDialogVisible
    },
    ApplicaStatuChange(id) {
      for (let i = 0; i < this.tableData.length; i++) {
        const item = this.tableData[i]
        if (item.id === id) {
          item.application = 0
          // 提示添加用户成功
          this.$message.error('已取消申请！')
          break
        }
      }
    },
    addApplication() {
      // 隐藏添加用户的对话框
      this.addDialogVisible = false
      for (let i = 0; i < this.tableData.length; i++) {
        const item = this.tableData[i]
        if (item.id === this.addForm.id) {
          item.application = 1
          break
        }
      }
      // 提示添加用户成功
      this.$message.success('提交申请成功！')
    }
  }
}
</script>
<style lang="less" scoped></style>
