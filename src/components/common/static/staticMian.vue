<template>
  <!-- <div>
     
    </div> -->
  <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
  <div>
    <div class="block">
      <span class="demonstration">按月选择</span>
      <el-date-picker v-model="value2" @change="change()" type="month" placeholder="选择月">
      </el-date-picker>
      <span class="demonstration">按时间段 </span>

      <el-date-picker v-model="value1" type="monthrange" align="right" unlink-panels range-separator="至"
        start-placeholder="开始月份" end-placeholder="结束月份" @change="change1()" :picker-options="pickerOptions">
      </el-date-picker>
      <span class="demonstration">按年 </span>
      <el-date-picker v-model="value3" type="year" placeholder="选择年" @change="change2()">
      </el-date-picker>

    </div>
    <br />
    <!-- <static-chart ></static-chart> -->
    <div>
      <div style="right: 25%; position: absolute; width: 60%;">
        <static-chart v-bind:message="value2" v-bind:message1="value1" v-bind:message2="value3"></static-chart>
      </div>
      <div style="right: 5%; position: absolute; width: 25%">
        <el-table :data="tableData" :key="tableData.id" style="font-size: 15px; left: 10%" stripe>
          <el-table-column prop="name" label="公司名称"> </el-table-column>
          <el-table-column prop="count" label="单数"> </el-table-column>
          <el-table-column prop="money" label="总金额（元）"> </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import StaticChart from "./static.vue";
import * as echarts from "echarts";

export default {
  components: {
    StaticChart,
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '本月',
          onClick(picker) {
            picker.$emit('pick', [new Date(), new Date()]);
          }
        }, {
          text: '今年至今',
          onClick(picker) {
            const end = new Date();
            const start = new Date(new Date().getFullYear(), 0);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近六个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setMonth(start.getMonth() - 6);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      tableData: [],
      supplierList: [],
      Englishname: "lishishi",
      value2: "",
      value1: "",
      value3: "",
    };
  },
  methods: {
    async change() {
      StaticChart.methods.test();
      const { data: res } = await this.$http.get("getChartAllList");

      //查询每位供应商给与的数据 获取到supplierid
      let flag = 0;
      let m = 0;
      for (let i = 0; i < res.date.length; i++) {
        for (let j = 0; j < this.supplierList.length; j++) {
          if (res.date[i].sid == this.supplierList[j]) {
            flag = 1;
            break;
          }
        }
        if (flag == 1) flag = 0;
        else {
          this.supplierList.push(res.date[i].sid);
        }
      }
      this.tableData = [];
      for (let j = 0; j < this.supplierList.length; j++) {
        const { data: res } = await this.$http.get(
          "getSupplierChart?id=" + this.supplierList[j]
        );
        let obj = {};
        obj.name = res.date[0].name;
        obj.money = res.date[0].sid; //钱
        obj.count = res.date[0].id; //次数
        if (
          res.date[0].last_time - 30 * 24 * 60 * 60 * 1000 < this.value2 &&
          res.date[0].last_time > this.value2
        ) {
          this.tableData.push(obj);
        }
      }
    },
    async change1() {
      StaticChart.methods.test();
      const { data: res } = await this.$http.get("getChartAllList");

      //查询每位供应商给与的数据 获取到supplierid
      let flag = 0;
      let m = 0;
      //获取供应商
      for (let i = 0; i < res.date.length; i++) {
        for (let j = 0; j < this.supplierList.length; j++) {
          if (res.date[i].sid == this.supplierList[j]) {
            flag = 1;
            break;
          }
        }
        if (flag == 1) flag = 0;
        else {
          this.supplierList.push(res.date[i].sid);
        }
      }
      this.tableData = [];
      for (let j = 0; j < this.supplierList.length; j++) {
        const { data: res } = await this.$http.get(
          "getSupplierChart?id=" + this.supplierList[j]
        );
        let obj = {};
        obj.name = res.date[0].name;
        obj.money = res.date[0].sid; //钱
        obj.count = res.date[0].id; //次数
        let t1 = res.date[0].last_time - 30 * 24 * 60 * 60 * 1000 < this.value1[1].getTime();
        let t2 = res.date[0].last_time > this.value1[0].getTime();
        if (
          t1 && t2
        ) {
          this.tableData.push(obj);
        }
      }
    },
    async change2() {

      const { data: res } = await this.$http.get("getChartAllList");
      //查询每位供应商给与的数据 获取到supplierid
      // console.log("change2")
      let flag = 0;
      let m = 0;
      //获取供应商
      for (let i = 0; i < res.date.length; i++) {
        for (let j = 0; j < this.supplierList.length; j++) {
          if (res.date[i].sid == this.supplierList[j]) {
            flag = 1;
            break;
          }
        }
        if (flag == 1) flag = 0;
        else {
          this.supplierList.push(res.date[i].sid);
        }
      }
      this.tableData = [];
      for (let j = 0; j < this.supplierList.length; j++) {
        const { data: res } = await this.$http.get(
          "getSupplierChart?id=" + this.supplierList[j]
        );
        let obj = {};
        obj.name = res.date[0].name;
        obj.money = res.date[0].sid; //钱
        obj.count = res.date[0].id; //次数

        let t1 = res.date[0].last_time - 12 * 30 * 24 * 60 * 60 * 1000 < this.value3.getTime();
        let t2 = res.date[0].last_time > this.value3.getTime();
        if (
          t1 && t2
        ) {
          this.tableData.push(obj);
        }
      }
    },
  },
};
</script>

<style lang='less' scope></style>