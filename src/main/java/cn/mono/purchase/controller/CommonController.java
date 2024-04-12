package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.Selecte;
import cn.mono.purchase.mapper.Purchasing_itemsMapper;
import cn.mono.purchase.pojo.BiddingApplication;
import cn.mono.purchase.pojo.PurchasingItems;
import cn.mono.purchase.service.BaseAcademyService;
import cn.mono.purchase.service.SupplierService;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

/**
 * @author nihao
 * @time 2021/4/16
 */
//@Api(value = "公共Controller", tags = { "公共访问接口" })
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class CommonController {
    @Resource
    private BaseAcademyService baseAcademyService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private Purchasing_itemsMapper purchasing_itemsMapper;
    Message message;
//    @ApiOperation(value = "获取供应商图表", notes = "根据供应商ID获取图表信息")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "成功"),
//            @ApiResponse(code = 400, message = "请求参数有误"),
//            @ApiResponse(code = 404, message = "找不到资源"),
//            @ApiResponse(code = 500, message = "服务器内部错误")
//    })
    @RequestMapping(value = "getSupplierChart")
    @ResponseBody
    public Message getSupplierChart(int id) {
        return baseAcademyService.getSupplierChart(id);
    }

    //获得供应商的情况
    @RequestMapping(value = "getChartSupplier")
    @ResponseBody
    public Message getChartSupplier() {
        return baseAcademyService.getChartSupplier();
    }

    @RequestMapping(value = "getChartAllList")
    @ResponseBody
    public Message getChartAllList() {
        return baseAcademyService.getChartAllList();
    }

    @RequestMapping(value = "getChartList")
    @ResponseBody
    public Message getChartList(int id) {
        return baseAcademyService.getChartList(id);
    }

    @RequestMapping(value = "addResultBoard")
    @ResponseBody
    public Message addResultBoard(int id) {
        return baseAcademyService.addResultBoard(id);
    }


    @RequestMapping(value = "getApplyCount")
    @ResponseBody
    public Message getApplyCount(int id) {
        return baseAcademyService.getApplyCount(id);
    }

    // 产品类别接口
    @RequestMapping(value = "getMinimumPrice ")
    @ResponseBody
    public Message getMinimumPrice(int id) {
        return baseAcademyService.getMinimumPrice(id);
    }


    //  学院类别接口
    @RequestMapping(value = "academys", method = RequestMethod.POST)
    @ResponseBody
    public Message getAcademyCategory() {
        return baseAcademyService.GetAcademyCategory();
    }

    // 产品类别接口
    @RequestMapping(value = "categories", method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchasingCategory() {
        return baseAcademyService.GetProductCategory();
    }

    //   采购列表接口
    @RequestMapping(value = "purchasingList", method = RequestMethod.POST)
    @ResponseBody
    public Message getPurchasingList() {
        return baseAcademyService.GetPurchasingCategory();
    }

    //   获得学院单独的采购公告
    @RequestMapping(value = "getBoardListByCid")
    @ResponseBody
    public Message getBoardListByCid(int cid) {
        return baseAcademyService.getBoardListByCid(cid);
    }

    //   结果接口
    @RequestMapping(value = "resultList", method = RequestMethod.POST)
    @ResponseBody
    public Message getResultCategory() {
        return baseAcademyService.GetResultCategory();
    }


    //   获得公告接口
    @RequestMapping(value = "getBoardList", method = RequestMethod.POST)
    @ResponseBody
    public Message boardListSelect() {
        return baseAcademyService.GetBoardList();
    }

    //   获得某条公告
    @RequestMapping(value = "getBoard")
    @ResponseBody
    public Message getBoard(int id) {
        return baseAcademyService.GetBoard(id);
    }

    @RequestMapping(value = "updateBoard", method = RequestMethod.POST)
    @ResponseBody
    public Message updateBoard(@RequestBody PurchasingItems purchasing_items) {
        return baseAcademyService.updateBoard(purchasing_items);
    }

    @RequestMapping(value = "getResult")
    @ResponseBody
    public Message getResult(int id) {
        return baseAcademyService.getResult(id);
    }

    @RequestMapping(value = "getApplyList")
    @ResponseBody
    public Message getApplyList(int id) {
        return baseAcademyService.getApplyList(id);
    }

    //获得中标用户
    @RequestMapping(value = "getSupplier")
    @ResponseBody
    public Message getSupplier(int id) {
        return baseAcademyService.getSupplier(id);
    }

    //获得中标用户
    @RequestMapping(value = "getAllBoardListByCid")
    @ResponseBody
    public Message getAllBoardListByCid(int cid) {
        return baseAcademyService.getAllBoardListByCid(cid);
    }

    //全部公告的 筛选功能
    @RequestMapping(value = "boardScreen", method = RequestMethod.POST)
    @ResponseBody
    public Message boardScreen(@RequestBody Selecte selecte) {
        System.out.println(selecte.toString());
        return baseAcademyService.boardScreen(selecte);
    }

    //某个学院的 筛选功能
    @RequestMapping(value = "boardScreen1", method = RequestMethod.POST)
    @ResponseBody
    public Message boardScreen1(@RequestBody Selecte selecte) {
        System.out.println(selecte.toString());
        return baseAcademyService.boardScreen1(selecte);
    }

    //添加采购公告
    @RequestMapping(value = "addPurBoard", method = RequestMethod.POST)
    @ResponseBody
    public Message addPurBoard(@RequestBody PurchasingItems purchasing_items) {

        return baseAcademyService.addPurBoard(purchasing_items);
    }

    //修改采购公告
    @RequestMapping(value = "editPurBoard", method = RequestMethod.POST)
    @ResponseBody
    public Message editPurBoard(@RequestBody PurchasingItems purchasing_items) {
        return baseAcademyService.editPurBoard(purchasing_items);
    }

    //删除采购公告
    @RequestMapping(value = "deletePurBoard", method = RequestMethod.POST)
    @ResponseBody
    public Message deletePurBoard(@RequestBody PurchasingItems purchasing_items) {

        return baseAcademyService.deletePurBoard(purchasing_items);
    }

    //添加结果公告
    @RequestMapping(value = "addResBoard", method = RequestMethod.POST)
    @ResponseBody
    public Message addResBoard(@RequestBody BiddingApplication bidding_application) {
        //return  baseAcademyService.addResBoard(purchasing_items);
        return baseAcademyService.addResBoard(bidding_application);
    }


    /**
     * 文件的上传
     */
    @Autowired
    //private HttpServletResponse response;
    private HttpServletRequest request;

    @CrossOrigin("*")
    @RequestMapping("uploadResultFile")
    public String uploadResultFile(@RequestParam("file") MultipartFile file) {
        //判断所上传文件是否存在
        if (file.isEmpty()) {
            return "上传错误";
        }
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        //设置上传文件的保存地址目录
        String dirPath = "D:\\R_normal_soft\\idea\\study\\ssm\\boardFile";
        File filePath = new File(dirPath);
        //如果保存的地址不存在,就先创建目录
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取当前文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
        //String newFileName = UUID.randomUUID() + "." + suffix;
        String id = request.getParameter("id");
        String newFileName = id + "." + suffix;

        try {
            //创建可能会有错误,强制添加异常
            file.transferTo(new File(dirPath + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "上传错误";
        }
        System.out.println(dirPath + newFileName);
        return newFileName;
    }

    /**
     * 文件的下载
     *
     * @param id
     * @param response
     * @throws Exception
     */
    @CrossOrigin("*")
    @RequestMapping("downloadResultFile")
    public void downloadResultFile(int id, HttpServletResponse response) throws Exception {
        System.out.println("downLoad4" + id);
        String name = new String((baseAcademyService.getFileName(id) + ".pdf").getBytes("UTF-8"), "iso-8859-1");
        // Step4 利用FileUtils将文件转成byte数组
        String filename = id + ".pdf";
        //设置下载名称
        String filename1 = name + ".pdf";
        response.setHeader("Content-Disposition", "attachment;filename=" + filename1);
        File file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\", filename);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        // Step5 从相应对象中获取输出流，将byte数组写出
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        // Step6 清除输出流的缓存、关闭输出流
        os.flush();
        os.close();
    }


    /**
     * downLoadSupplierFile 下载申请文件
     *
     * @param id
     * @param response
     * @throws Exception
     */
    @CrossOrigin("*")
    @RequestMapping("downLoadSupplierFile")
    public void downLoadSupplierFile(int id, HttpServletResponse response) throws Exception {

        String name = new String((baseAcademyService.getSupplyFileName(id)).getBytes("UTF-8"), "iso-8859-1");
        // Step4 利用FileUtils将文件转成byte数组
        //保存名称

        String filename = "";
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        File file = null;
        try {
            filename = id + ".docx";
            file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile", filename);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);
            // Step6 清除输出流的缓存、关闭输出流
            os.flush();
            os.close();
        } catch (Exception e) {
            filename = id + ".pdf";
            file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile", filename);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);

            // Step6 清除输出流的缓存、关闭输出流
            os.flush();
            os.close();
        }


    }

    @CrossOrigin("*")
    @RequestMapping("downLoadPurchaseFile")
    public void downLoadPurchaseFile(int id, HttpServletResponse response) throws Exception {
        String name = new String((baseAcademyService.getPurchaseFileName(id)).getBytes("UTF-8"), "iso-8859-1");
//        String name = new String((baseAcademyService.getSupplyFileName(id)).getBytes("UTF-8"),"iso-8859-1");
        // Step4 利用FileUtils将文件转成byte数组
        //保存名称
//??怎么解决采购id
        String filename = "";
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        File file = null;
        id = purchasing_itemsMapper.selectByPrimaryKey(id).getErrol_type();
        System.out.println(id);
        System.out.println("id?");
        try {
            try {
                filename = id + ".docx";
                file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\purchaseFile", filename);
                byte[] bytes = FileUtils.readFileToByteArray(file);
                ServletOutputStream os = response.getOutputStream();
                os.write(bytes);
                // Step6 清除输出流的缓存、关闭输出流
                os.flush();
                os.close();
            } catch (Exception e) {
                filename = id + ".pdf";
                file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\purchaseFile", filename);
                byte[] bytes = FileUtils.readFileToByteArray(file);
                ServletOutputStream os = response.getOutputStream();
                os.write(bytes);

                // Step6 清除输出流的缓存、关闭输出流
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            filename = id + ".doc";
            file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\purchaseFile", filename);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);
            // Step6 清除输出流的缓存、关闭输出流
            os.flush();
            os.close();
        }
    }

    @CrossOrigin("*")
    @RequestMapping("applyFileDown")
    public void applyFileDown(HttpServletResponse response) throws Exception {

        String name = new String(("推荐表.docx").getBytes("UTF-8"), "iso-8859-1");
        // Step4 利用FileUtils将文件转成byte数组
        //保存名称
        String filename = "推荐表.docx";
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        File file = new File("D:\\学习\\项目\\new9\\项目\\ssm\\supplyFile\\supSupplyFile", filename);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        ServletOutputStream os = response.getOutputStream();
        os.write(bytes);
        // Step6 清除输出流的缓存、关闭输出流
        os.flush();
        os.close();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "uploadApplyFile", method = RequestMethod.POST)
    @ResponseBody
    public Message uploadApplyFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        message = new Message();

        //判断所上传文件是否存在
        if (file.isEmpty()) {
            message.setSuccess(false);
            message.setMsg("上传错误");
            return message;
        }
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        //设置上传文件的保存地址目录
        String dirPath = "D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile\\";
        File filePath = new File(dirPath);
        //如果保存的地址不存在,就先创建目录
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取当前文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
        //String newFileName = UUID.randomUUID() + "." + suffix;
        String id = request.getParameter("id");
        String newFileName = id + "." + suffix;
//        String newFileName =  "yes." + suffix;
        try {
            //创建可能会有错误,强制添加异常
            file.transferTo(new File(dirPath + newFileName));

        } catch (IOException e) {
            e.printStackTrace();
            String filePth = dirPath + newFileName;
            File file1 = new File(filePth);
            if (!file1.exists()) {
                // 文件不存在
                message.setSuccess(false);
                message.setMsg("上传错误");
                return message;
            } else {
                message.setSuccess(false);
                message.setMsg("上传成功");
                return message;
            }
        }
        message.setSuccess(true);
        message.setMsg("上传成功");
        System.out.println(dirPath + newFileName);
        return message;
    }

    @CrossOrigin("*")
    @RequestMapping(value = "uploadPurchaseFile", method = RequestMethod.POST)
    @ResponseBody
    public Message uploadPurchaseFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        message = new Message();

        //判断所上传文件是否存在
        if (file.isEmpty()) {
            message.setSuccess(false);
            message.setMsg("上传错误");
            return message;
        }
//        System.out.println("uploadPurchaseFile");
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        //设置上传文件的保存地址目录
        String dirPath = "D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\purchaseFile\\";
        File filePath = new File(dirPath);
        //如果保存的地址不存在,就先创建目录
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取当前文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
        //String newFileName = UUID.randomUUID() + "." + suffix;

        String id = request.getParameter("id");


        //获取最大id
        id = supplierService.getPurchaseMaxID().getStatus().toString();
//        System.out.println(id);
//        System.out.println("id");


        String newFileName = id + "." + suffix;
//        String newFileName =  "yes." + suffix;
        try {
            //创建可能会有错误,强制添加异常
            file.transferTo(new File(dirPath + newFileName));

        } catch (IOException e) {
            e.printStackTrace();
            String filePth = dirPath + newFileName;
            File file1 = new File(filePth);
            if (!file1.exists()) {
                // 文件不存在
                message.setSuccess(false);
                message.setMsg("上传错误");
                return message;
            } else {
                message.setSuccess(false);
                message.setMsg("上传成功");
                return message;
            }
        }
        message.setSuccess(true);
        message.setMsg("上传成功");
        System.out.println(dirPath + newFileName);
        return message;
    }


    @CrossOrigin("*")
    @RequestMapping(value = "upLoadResultFile1", method = RequestMethod.POST)
    @ResponseBody
    public Message upLoadResultFile1(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        message = new Message();

        //判断所上传文件是否存在
        if (file.isEmpty()) {
            message.setSuccess(false);
            message.setMsg("上传错误");
            return message;
        }
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        //设置上传文件的保存地址目录
        String dirPath = "D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\";
        File filePath = new File(dirPath);
        //如果保存的地址不存在,就先创建目录
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取当前文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
        //String newFileName = UUID.randomUUID() + "." + suffix;
        String id = request.getParameter("id");
        String newFileName = id + "." + suffix;
//        String newFileName =  "yes." + suffix;
        try {
            //创建可能会有错误,强制添加异常
            file.transferTo(new File(dirPath + newFileName));

        } catch (IOException e) {
            e.printStackTrace();
            String filePth = dirPath + newFileName;
            File file1 = new File(filePth);
            if (!file1.exists()) {
                // 文件不存在
                message.setSuccess(false);
                message.setMsg("上传错误");
                return message;
            } else {
                message.setSuccess(false);
                message.setMsg("上传成功");
                return message;
            }
        }
        message.setSuccess(true);
        message.setMsg("上传成功");
        System.out.println(dirPath + newFileName);
        return message;
    }
}
