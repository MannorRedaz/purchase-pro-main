
export class purchaseListManageJs {
    static getDetail(data) {
      return  this.$http.post(
        "schoolAdmin/selectDetail",
        this.selects
      );
    //return data;
    }
  
  }