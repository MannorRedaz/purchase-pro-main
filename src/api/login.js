import myAxios from './myAxios'
async function supplierLogin(data) {
    const { data: res } = await myAxios.post('supplierLogin');
    return res
}
export {
    supplierLogin
}