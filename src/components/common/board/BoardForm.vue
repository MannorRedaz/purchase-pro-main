<template>
  <el-form
        label-width="120px"
        :model="addModel"
        size="small"
        label-position="right"
      >
        <el-form-item label="公告名" prop="">
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.purchase_name"
            ></el-input>
          </el-col>
          <el-col :span="2"> 产品名称 </el-col>
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.product_name"
            ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="拍卖标的" prop="">
          <el-input v-model="addModel.reason" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请选择开始和结束时间" prop="">
          <el-col :span="8">
            <el-date-picker
              v-model="times"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </el-col>
          <el-col :span="2">产品类别</el-col>
          <el-col :span="3">
            <el-select v-model="times" placeholder="请选择">
              <el-option
                v-for="item in CategoriesMap"
                :key="item.pid"
                :value="item.product_name"
              >
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="2"> 预算价格 </el-col>
          <el-col :span="3">
            <el-input
              auto-complete="off"
              v-model="addModel.highest_price"
            ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="现场看样时间" prop="">
          <el-col :span="8">
            <el-date-picker
              v-model="addModel.site_inspection_time"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-col>
          <el-col :span="2"> 现场看样地点 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.site"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="报名联系人">
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.errol_contract"
            ></el-input>
          </el-col>
          <el-col :span="2"> 报名联系人电话 </el-col>
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.errol_tel"
            ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="看样联系人">
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.inspection_contract"
            ></el-input>
          </el-col>
          <el-col :span="2"> 看样联系人电话 </el-col>
          <el-col :span="8">
            <el-input auto-complete="off" v-model="addModel.inspection_tel"
              >></el-input
            >
          </el-col>
        </el-form-item>
        <el-form-item label="拍卖地点">
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.auction_site"
            ></el-input>
          </el-col>
          <el-col :span="2"> 拍卖时间 </el-col>
          <el-col :span="8">
            <el-input
              auto-complete="off"
              v-model="addModel.deadline"
            ></el-input>
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
</template>

<script>
export default {

}
</script>

<style>

</style>