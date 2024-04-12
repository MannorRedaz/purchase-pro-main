<template>
    <!-- <div>
     
    </div> -->
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div >
     <div class="block">
       <span class="demonstration">按月选择</span>
      <el-date-picker
        v-model="value2"
        @change="change()"
        type="month"
        placeholder="选择月"
      >
      </el-date-picker>
        <span class="demonstration" >时间段选择</span>
            <el-date-picker
      v-model="value1"
      type="monthrange"
      align="right"
      unlink-panels
      range-separator="至"
      start-placeholder="开始月份"
      end-placeholder="结束月份"
      
      :picker-options="pickerOptions">  
    </el-date-picker>
      </div>
      <br>
    <!-- <static-chart ></static-chart> -->
    <static-chart v-bind:message="value2" v-bind:message1="value1"></static-chart>

    </div>
</template>

<script>
import StaticChart from './static.vue'
import * as echarts from "echarts";

export default {
    components:{
        StaticChart
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
     value1:'', 
     value2:'', 
    };
  },

  methods: {
    change(){
      StaticChart.methods.test();
    }
  }
  }
</script>

<style lang='less' scope>

</style>