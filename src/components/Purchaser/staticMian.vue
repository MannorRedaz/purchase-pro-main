<template>
    <!-- <div>
     
    </div> -->
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div >
     <div class="block">
        <span class="demonstration" >时间选择</span>
        <el-date-picker v-model="value2" @change="change()" type="month" placeholder="选择月">
        </el-date-picker>
      </div>
      <br>
    <!-- <static-chart ></static-chart> -->
    <static-chart v-bind:message="value2"></static-chart>

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
      Englishname:'lishishi',
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