import myAxios from './myAxios'
/**
 * 1、公告管理
 */
// 获取二级学院
async function getAcademyList() {
    const { data: res } = await myAxios.post('academys');
    return res
}
//获取商品类别
async function getCategoriesList() {
    const { data: res } = await myAxios.post('categories');
    return res
}
//获取采购定价结果
async function getResultList() {
    const { data: res } = await myAxios.post('resulList');
    return res
}
//获取完整采购清单
async function getPurchasingList() {
    const { data: res } = await myAxios.post('purchasingList');
    return res
}
//采购清单查询
async function selectPurchasingList(data) {
    const { data: res } = await myAxios.post('boardListSelect', data);
    return res
}
//对采购清单申请竞价
async function applyPurchasing(data) {
    const { data: res } = await myAxios.post('suplier/apply', data);
    return res
}
/**
 * 2、审核功能
 */
// 入围供应商单个竞价列表
async function selectBiddingApplicationById(id) {
    const { data: res } = await myAxios.post('suplier/supApplys', id);
    return res
}
//入围供应商竞价修改
async function selectPurchasingList(data) {
    const { data: res } = await myAxios.post('suplier/applyUpdate', data);
    return res
}
//入围供应商一定时间段的全体竞价列表
async function selectBiddingApplicationList(data) {
    const { data: res } = await myAxios.post('suplier/applySelect', data);
    return res
}
/***
 * 3、消息通知
 */
// 中标通知
async function awardNotificationById(id) {
    const { data: res } = await myAxios.post('suplier/supResult', data);
    return res
}
/**
 * 
 * 4、个人信息管理
 */
//更改个人信息
async function setBasicInfo(data) {
    const { data: res } = await myAxios.post(`supplier/infoUpdate`, data);
    return res
}
// 入围申请结果查询
async function applyInShortlistById(id) {
    const { data: res } = await myAxios.post(`suplier/applyShortList`, id);
    return res
}
export {
    getAcademyList, getCategoriesList, getResultList,
    getPurchasingList, selectPurchasingList,
    selectBiddingApplicationList, awardNotificationById, setBasicInfo,
    applyInShortlistById,applyPurchasing,selectBiddingApplicationById
}