package cn.mono.purchase.service.impl;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.mapper.Purchasing_itemsMapper;
import cn.mono.purchase.service.BaseAcademyService;
import cn.mono.purchase.service.FileService;
import cn.mono.purchase.service.SupplierService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author : 秋雨
 * @since : 2024/04/13
 * Description  :
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private HttpServletRequest request;
    @Resource
    private SupplierService supplierService;
    @Resource
    private BaseAcademyService baseAcademyService;
    @Resource
    private Purchasing_itemsMapper purchasing_itemsMapper;

    @Override
    public Message upLoadResultFile1(MultipartFile file, HttpServletRequest request) {
        Message message = new Message();

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

        return message;
    }

    @Override
    public Message uploadPurchaseFile(MultipartFile file, HttpServletRequest request) {
        Message message = new Message();

        //判断所上传文件是否存在
        if (file.isEmpty()) {
            message.setSuccess(false);
            message.setMsg("上传错误");
            return message;
        }
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

        return message;
    }

    @Override
    public String uploadResultFile(MultipartFile file) {
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

        return newFileName;
    }

    @Override
    public void downloadResultFile(int id, HttpServletResponse response) throws IOException {
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

    @Override
    public void downLoadSupplierFile(int id, HttpServletResponse response) throws IOException {

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

    @Override
    public void downLoadPurchaseFile(int id, HttpServletResponse response) throws IOException {
        String name = new String((baseAcademyService.getPurchaseFileName(id)).getBytes("UTF-8"), "iso-8859-1");
//        String name = new String((baseAcademyService.getSupplyFileName(id)).getBytes("UTF-8"),"iso-8859-1");
        // Step4 利用FileUtils将文件转成byte数组
        //保存名称
//??怎么解决采购id
        String filename = "";
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        File file = null;
        id = purchasing_itemsMapper.selectByPrimaryKey(id).getErrol_type();


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

    @Override
    public void applyFileDown(HttpServletResponse response) throws IOException {
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

    @Override
    public Message uploadApplyFile(MultipartFile file, HttpServletRequest request) {
        Message message = new Message();

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

        return message;
    }
}
