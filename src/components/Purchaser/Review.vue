<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/sWelcome' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>审核列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <el-table :data="tableData" style="width: 100%;font-size: 15px;" border stripe>
        <!-- 展开列 -->
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-table :data="scope.row.appList" style="width: 100%">
              <el-table-column prop="name" label="公司名">
              </el-table-column>
              <el-table-column prop="price" label="价格">
              </el-table-column>
              <el-table-column label="详情">
                <template slot-scope="scope">
                  <el-button type="primary" icon="el-icon-edit" size="mini"
                    @click="showEditDialog(scope.row.id)">详细信息</el-button>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <el-button type="primary">通过</el-button>
                <el-button type="danger">不通过</el-button>
              </el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="材料名"></el-table-column>
        <el-table-column prop="type" label="类别"></el-table-column>
        <el-table-column prop="num" label="数量"></el-table-column>
        <el-table-column prop="school" label="学院"></el-table-column>
        <el-table-column label="申请总数">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.sum }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" icon="el-icon-delete" size="mini"
              @click="removeUserById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum" :page-sizes="[50]" :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next" :total="total">
      </el-pagination>
    </el-card>
    <!-- 查看详细申请对话框 -->
    <el-dialog title="审核列表" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体区域 -->
      <el-form ref="addFormRef" :model="addForm" label-width="70px">
        <el-form-item label="材料名" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="addForm.price"></el-input>
        </el-form-item>
        <el-form-item label="详细信息" prop="details">
          <el-input type="textarea" :rows="3" placeholder="请输入内容" v-model="addForm.details">
          </el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addApplication()">确 定</el-button>
      </span>
    </el-dialog>
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
          sum: 20,
          appList: [
            {
              id: '111',
              name: 'xxx公司',
              price: '200￥',
              details: 'xxxxxxxx'
            },
            {
              id: '111',
              name: 'xxx公司',
              price: '200￥',
              details: 'xxxxxxxx'
            }
          ]
        },
        {
          id: '102',
          name: '电脑',
          type: '计算机',
          num: '30',
          school: '数学与计算机学院',
          sum: 19,
          appList: [
            {
              id: '123',
              name: 'xxx公司',
              price: '200￥',
              details: 'xxxxxxxx'
            },
            {
              id: '123',
              name: 'xxx公司',
              price: '200￥',
              details: 'xxxxxxxx'
            }
          ]
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
