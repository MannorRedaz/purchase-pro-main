package cn.mono.purchase.service;

import cn.mono.purchase.dto.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : 秋雨
 * @since : 2024/04/13
 * Description  :
 */
public interface FileService {
    Message upLoadResultFile1(MultipartFile file, HttpServletRequest request);

    Message uploadPurchaseFile(MultipartFile file, HttpServletRequest request);

    String uploadResultFile(MultipartFile file);

    void downloadResultFile(int id, HttpServletResponse response) throws IOException;

    void downLoadSupplierFile(int id, HttpServletResponse response) throws IOException;

    void downLoadPurchaseFile(int id, HttpServletResponse response) throws IOException;

    void applyFileDown(HttpServletResponse response) throws IOException;

    Message uploadApplyFile(MultipartFile file, HttpServletRequest request);
}
