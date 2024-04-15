package cn.mono.purchase.controller;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author : 秋雨
 * @since : 2024/04/13
 * Description  :
 */
@RestController
@Tag(name = "文件")
public class FileController {
    /**
     * 文件的上传
     */

    @Resource
    private FileService fileService;

    @RequestMapping("uploadResultFile")
    public String uploadResultFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadResultFile(file);
    }

    /**
     * 文件的下载
     *
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping("downloadResultFile")
    public void downloadResultFile(int id, HttpServletResponse response) throws Exception {
        fileService.downloadResultFile(id, response);
    }
    /**
     * downLoadSupplierFile 下载申请文件
     *
     * @param id
     * @param response
     * @throws Exception
     */

    @RequestMapping("downLoadSupplierFile")
    public void downLoadSupplierFile(int id, HttpServletResponse response) throws Exception {
        fileService.downLoadSupplierFile(id, response);
    }
    @RequestMapping("downLoadPurchaseFile")
    public void downLoadPurchaseFile(int id, HttpServletResponse response) throws Exception {
        fileService.downLoadPurchaseFile(id, response);

    }
    @RequestMapping("applyFileDown")
    public void applyFileDown(HttpServletResponse response) throws Exception {
        fileService.applyFileDown(response);
    }
    @RequestMapping(value = "uploadApplyFile", method = RequestMethod.POST)
    public Message uploadApplyFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        return fileService.uploadApplyFile(file, request);
    }

    @RequestMapping(value = "uploadPurchaseFile", method = RequestMethod.POST)
    public Message uploadPurchaseFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        return fileService.uploadPurchaseFile(file, request);
    }
    @RequestMapping(value = "upLoadResultFile1", method = RequestMethod.POST)
    public Message upLoadResultFile1(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        return fileService.upLoadResultFile1(file, request);
    }
}
