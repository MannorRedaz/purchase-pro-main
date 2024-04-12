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
 props:['message','message1'],
  data() {
    return {
      chartList:[],
      date:'',
      date2:'',
      date1:'',

      chartLine: null,
      xlist: [],
      ylist1:[0,0,0,0,0,0],
      ylist2:[0,0,0,0,0,0],

    };
  },
  //监听photoList的值，当它由空转变时就会触发，这时候就能取到了，拿到值后要传值到data的newPhotoList中
  watch: {
    message: function (newData) {
      console.log("q");
      console.log(newData);
      this.date = newData;
     // this.getPhotoList(this.newPhotoList)
     this.getChartList();
    },
    message1: function (newData) {
      this.date2 = newData;
      // this.getPhotoList(this.newPhotoList)
      console.log("message1");
      this.getChartList1();
    },
      },
  mounted() {
    this.inital();
    this.$nextTick(() => {
      this.drawLineChart();
    });
    
  },
  methods: {
    inital(){
     this.date = new Date();
     this.getChartList();
    },
    test(){
           console.log('niaho');
            //console.log(this.message);
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
            // data : ['周一','周二','周三','周四','周五','周六','周日']
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
        series:  [
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
          data:this.ylist2,
        },
      ],
      };
      // 使用刚指定的配置项和数据显示图表
      this.chartLine.setOption(option);
    },
    async getChartList(){
      console.log("getChartList");
      const data1 = JSON.parse(window.sessionStorage.getItem("data"));
      const { data: res }= await this.$http.get('getChartList?id='+data1.id);
      let str = this.$moment(this.date).format("YYYY-MM");
      //x坐标
      this.xlist[0] = str+"-06";
      this.xlist[1] = str+"-11";
      this.xlist[2] = str+"-16";
      this.xlist[3] = str+"-21";
      this.xlist[4] = str+"-26";
      this.xlist[5] = str+"-";
    //y坐标
    
      //钱和时间
      if(res.success){
        //时间，交易金额
        this.chartList = res.date;
        console.log(this.chartList);
        console.log(this.date);
         for(let i=0;i<7;i++){
           this.ylist1[i]=0;
           this.ylist2[i]=0;
         }
        for(let i=0;i<this.chartList.length;i++){
          //在这个月的
          console.log((this.chartList[i].date-30*24*60*60*1000)<(this.date));
          console.log(
             this.chartList[i].date>(this.date));
           if(((this.chartList[i].date-30*24*60*60*1000)<(this.date))&&(
             this.chartList[i].date>(this.date)
           )){
             console.log("有数据");
            //
            if((this.chartList[i].date-5*24*60*60*1000<this.date)){
              this.ylist1[0]++;
              this.ylist2[0]+=this.chartList[i].reality_price;
            }else if((this.chartList[i].date-10*24*60*60*1000<this.date)){
              this.ylist1[1]++;
              this.ylist2[1]+=this.chartList[i].reality_price;
            }else if((this.chartList[i].date-15*24*60*60*1000<this.date)){
              this.ylist1[2]++;
              this.ylist2[2]+=this.chartList[i].reality_price;
            }else if((this.chartList[i].date-20*24*60*60*1000<this.date)){
              this.ylist1[3]++;
              this.ylist2[3]+=this.chartList[i].reality_price;
            }else if((this.chartList[i].date-25*24*60*60*1000<this.date)){
              this.ylist1[4]++;
              this.ylist2[4]+=this.chartList[i].reality_price;
            }else {
              this.ylist1[5]++;
              this.ylist2[5]+=this.chartList[i].reality_price;
            }
          }
        //  this.xlist.push(this);
        }
      }else{
        console.log("chartList请求失败");
      }
    this.drawLineChart();
    },
     async getChartList1() {
      const data1 = JSON.parse(window.sessionStorage.getItem("data"));

      const { data: res1 }= await this.$http.get('getChartList?id='+data1.id);
      console.log("res1");
      
      console.log(res1);
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
      let D= new Date();
      
      this.date2[0];
      for (
        let i = 1;
        i < (this.date2[1] - this.date2[0]) / (1000 * 30 * 24 * 60 * 60) + 1;
        i++
      ) {
        
         this.time[i] = this.date2[0].getTime() + (1000 * 30 * 24 * 60 * 60)*i;
        //  console.log(this.time[0]+"."+this.time[i]);
        //this.xlist[i] = this.$moment(this.time[i]).format("YYYY-MM-DD");
        let p=0;
        let month=(this.date2[0].getMonth()+i+1)%12;
        if(month%12==0)month = 12;
        if((this.date2[0].getMonth()+i+1)>12){
             p=Math.floor((this.date2[0].getMonth()+i)/12);
        }
        this.xlist[i] = (this.date2[0].getFullYear()+p)+"-"+month+"-"+this.date2[0].getDate();
        console.log(this.xlist[i]+"this.xlist[i]");
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
          console.log("有数据");
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
  },
};
</script>

<style lang='less' scope>
.line-wrap {
  width: 70%;
  height: 500px;
}
.block{
    margin-left: 8%;
}
</style>