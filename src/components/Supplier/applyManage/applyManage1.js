
import axios from 'axios';
export class applyManage1JS {

  static inital() {
    this.getAcademyList();
    this.getCategoryList();
  }
  static getbiddingList() {
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    let res = axios.get(
      "supplier/getApplyResultList?sid=" + data.id
    );
    return res;
  }

  static handleList2() {
    this.$message.success("188");
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    this.$message.success("191");

    const res = axios.get(
      "supplier/getApplyResultList?sid=" + data.id
    );
    return res;

  }
  static search(val) {

    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];

    let res = axios.post(
      "supplier/getSearchBidingList",
      val
    );
    return res;
  }
  //获得商品类别列表
  static getCategoryList() {
    // console.log("gjuaoguwaoghweog");

    let res = axios.post("categories");
    // console.log("gjuaoguwaoghweog");

    return res;
  }
  //获得学院列表
  static getAcademyList() {
    //  console.log("gjuaoguwaoghweog");
    let res = axios.post("academys");
    return res;
  }
  //使用学院号获得该学院的采购信息

  static getPurchaseList() {
    // console.log("gjuaoguwaoghweog");

    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    let res = axios.post("supplier/getPurchaseList?");
    // console.log("gjuaoguwaoghweog");

    return res;
  }
  //通过供应商id查询结果列表
  async getResultListBySupplierId() {
    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    let res = axios.get(
      "supplier/getResultListBySupplierId?sid=" + data.id
    );

    return res;
  }

  //获取申请列表
  static getApplyList() {

    const data = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    let res = axios.get(
      "supplier/getApplyList?id=" + data.id
    );
    return res;
  }
  //获取供应商列表
  static getSupplierList() {
    let res = axios.get("schoolAdmin/getSupplierList");
    return res;
  }
  static delete1(val) {
    let res = axios.post("supplier/deleteApply", val);
    return res;
  }

  static downLoad() {
    window.open(
      "http://localhost:8088/downloadResultFile/?id=" +
      this.clickTarget.result_id
    );
  }
  static getSupplier(val) {
    this.showDialog;
    let res = axios.get(
      "getSupplier?id=" + val.supplierId
    );
    return res;
  }

}