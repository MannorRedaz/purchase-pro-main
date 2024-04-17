<template>
  <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
  <div id="chartLine" class="line-wrap"></div>
  <!-- <div>
        <p >1{{message}}nihao1</p> 
    </div> -->
</template>

<script>
import * as echarts from "echarts";
export default {
  props: ["message", "message1", "message2"],
  data() {
    return {
      chartList: [],
      date: "",
      date2: "",
      date3: "",
      chartLine: null,
      time: [],

      xlist: [],
      ylist1: [0, 0, 0, 0, 0, 0],
      ylist2: [0, 0, 0, 0, 0, 0],
    };
  },
  //监听photoList的值，当它由空转变时就会触发，这时候就能取到了，拿到值后要传值到data的newPhotoList中
  watch: {
    message: function (newData) {
      this.date = newData;
      this.getChartList();
    },
    message1: function (newData) {
      this.date2 = newData;
      this.getChartList1();
    },
    message2: function (newData) {
      this.date3 = newData;
      this.getChartList2();
    },
  },
  mounted() {
    this.inital();
    this.getChartList();
    this.$nextTick(() => {
      this.drawLineChart();
    });
  },
  methods: {
    inital() {
      this.date = new Date();
      this.getChartList();
    },
    getData() {
      return this.chartList();
    },
    drawLineChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chartLine = echarts.init(this.$el, "shine");
      let option = {
        tooltip: {
          trigger: "axis",
        },
        //有哪些数据线
        legend: {
          data: ["订单数量", "交易金额"],
        },
        calculable: true,
        //x轴
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            axisTick: {
              show: false,
            },
            data: this.xlist,
          },
        ],
        //y轴
        yAxis: [
          {
            type: "value",
            axisTick: {
              show: false,
            },
            name: "",
          },
        ],
        //数据
        series: [
          {
            name: "订单数量",
            type: "line",
            stack: "总量",
            data: this.ylist1,
          },
          {
            name: "交易金额",
            type: "line",
            stack: "总量",
            data: this.ylist2,
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表
      this.chartLine.setOption(option);
    },
    async getChartList() {
      // console.log("getChartList");
      const data1 = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res } = await this.$http.get("getChartAllList");
      let str = this.$moment(this.date).format("YYYY-MM");
      const year = this.date.getFullYear();
      const month = this.date.getMonth() + 1;
      // 获得当前月的总天数
      const day = new Date(year, month, 0).getDate()
      //x坐标
      this.xlist[0] = str + "-06";
      this.xlist[1] = str + "-11";
      this.xlist[2] = str + "-16";
      this.xlist[3] = str + "-21";
      this.xlist[4] = str + "-26";
      this.xlist[5] = str + `-${day}`;
      //y坐标

      //钱和时间
      if (res.success) {
        //时间，交易金额
        this.chartList = res.date;
        // console.log(this.chartList);
        // console.log(this.date);
        for (let i = 0; i < 6; i++) {
          this.ylist1[i] = 0;
          this.ylist2[i] = 0;
        }

        //转换数据格式
        const data = this.chartList.map(item => ({
          date: item.date,
          amount: item.reality_price
        }));
        const result = this.calculateTotalAmountForSpecificDates(data);
        Object.entries(result).forEach(([date, amount], id) => {
          this.ylist2[id] += amount;
        });
      }
      this.drawLineChart();
    },

    calculateTotalAmountForSpecificDates(data) {
      const currentDate = this.date;
      const currentMonth = currentDate.getMonth();
      const currentYear = currentDate.getFullYear();
      const lastDayOfMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

      const specificDates = [6, 11, 16, 21, 26, lastDayOfMonth]; // 每个月的特定日期
      const result = {};
      let index = 0;
      for (const date of specificDates) {
        const targetDate = new Date(currentYear, currentMonth, date);
        // 找到小于等于目标日期的数据
        const filteredData = data.filter(item => new Date(item.date) <= targetDate);
        // 计算金额总量
        const totalAmount = filteredData.reduce((acc, curr) => acc + curr.amount, 0);
        // 计算数据条目数量
        const itemCount = filteredData.length;
        this.ylist1[index++] = itemCount;
        result[targetDate.toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' })] = totalAmount;
      }
      return result;
    },
    async getChartList1() {
      const { data: res1 } = await this.$http.get("getChartAllList");
      this.chartList = res1.date;
      for (let i = 0; i < 99; i++) {
        this.ylist1[i] = 0;
        this.ylist2[i] = 0;
      }
      this.xlist = [];
      this.time = [];
      //处理x轴
      this.xlist[0] = this.$moment(this.date2[0]).format("YYYY-MM-DD");
      this.time[0] = this.date2[0];
      let D = new Date();

      this.date2[0];
      for (
        let i = 1;
        i < (this.date2[1] - this.date2[0]) / (1000 * 30 * 24 * 60 * 60) + 1;
        i++
      ) {

        this.time[i] = this.date2[0].getTime() + (1000 * 30 * 24 * 60 * 60) * i;
        //  console.log(this.time[0]+"."+this.time[i]);
        //this.xlist[i] = this.$moment(this.time[i]).format("YYYY-MM-DD");
        let p = 0;
        let month = (this.date2[0].getMonth() + i + 1) % 12;
        if (month % 12 == 0) month = 12;
        if ((this.date2[0].getMonth() + i + 1) > 12) {
          p = Math.floor((this.date2[0].getMonth() + i) / 12);
        }
        this.xlist[i] = (this.date2[0].getFullYear() + p) + "-" + month + "-" + this.date2[0].getDate();
        // console.log(this.xlist[i] + "this.xlist[i]");
      }

      //处理y轴
      for (let i = 0; i < 99; i++) {
        this.ylist1[i] = 0;
        this.ylist2[i] = 0;
      }

      //  this.ylist1 = [];
      // this.ylist2 = [];
      for (let i = 0; i < this.chartList.length; i++) {
        if (
          this.chartList[i].date > this.date2[0] &&
          this.chartList[i].date - 1000 * 30 * 60 * 60 * 24 < this.date2[1]
        ) {
          // console.log("有数据");
          //
          for (let j = 0; j < this.time.length; j++)
            if (this.chartList[i].date < this.time[j]) {
              this.ylist1[j]++;
              this.ylist2[j] += this.chartList[i].reality_price;
              break;
            }
        }
      }
      this.drawLineChart();
    },
    async getChartList2() {

      const { data: res1 } = await this.$http.get("getChartAllList");
      this.chartList = res1.date;

      this.xlist = [];
      this.time = [];
      //处理x轴
      let D = new Date();
      this.xlist[0] = "第一季度";
      this.xlist[1] = "第二季度";
      this.xlist[2] = "第三季度";
      this.xlist[3] = "第四季度";

      //处理y轴
      for (let i = 0; i < 99; i++) {
        this.ylist1[i] = 0;
        this.ylist2[i] = 0;
      }
      // console.log("this.chartList");
      for (let i = 0; i < this.chartList.length; i++) {
        if (
          this.chartList[i].date > this.date3 &&
          this.chartList[i].date - 1000 * 30 * 60 * 60 * 24 * 12 < this.date3
        ) {
          // console.log("有数据");
          this.time = [];
          for (let i = 0; i < 4; i++) {
            this.time[i] = (this.date3.getTime() + i * 4 * 1000 * 24 * 30 * 60 * 60);
          }
          for (let j = 0; j < 4; j++)
            if (this.chartList[i].date < this.time[j]) {
              this.ylist1[j]++;
              this.ylist2[j] += this.chartList[i].reality_price;
              break;
            }
        }
      }
      this.drawLineChart();
    },
  },
};
</script>

<style lang='less' scope>
.line-wrap {
  width: 100%;
  height: 400px;
}

.block {
  margin-left: 8%;
}
</style>