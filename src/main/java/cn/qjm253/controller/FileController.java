package cn.qjm253.controller;

import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import cn.qjm253.utils.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
@Controller
public class FileController extends BaseController{
    public static final String DEFAULT_PATH = "/WEB-INF/file/header/";
    public static final String DEFAULT_UPLOAD_FILE_PATH = "/WEB-INF/file/";
    /**
     * 头像下载
     *
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAvatar(@RequestParam String fileName,
                                           HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        //检查文件名中非法字符，只允许是字母、数字和下划线
        try {
            headers.setContentDispositionFormData("myfile", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 获取物理路径
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_PATH);
            File pic = new File(path, fileName);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(pic), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> upload(@RequestParam(value = "file")CommonsMultipartFile file, HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = file.getOriginalFilename();
        String saveName = UUIDUtil.addUUID(fileName);
        String identifyCode = UUIDUtil.getRandomString(6);
        double size = file.getSize() / (1024.0 * 1024.0);
        try {
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_UPLOAD_FILE_PATH);
            File f = new File(path);
            if(!f.exists() || !f.isDirectory()){        //创建文件夹
                f.mkdirs();
            }
            f = new File(path, saveName);
            file.transferTo(f);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setCreateTime(System.currentTimeMillis());
            fileInfo.setUpdateTime(System.currentTimeMillis());
            fileInfo.setFileSize(size);
            fileInfo.setDownloadCount(0);
            fileInfo.setPublic(true);
            fileInfo.setSaveName(saveName);
            fileInfo.setIdentifyCode(identifyCode);
            fileDao.save(fileInfo);
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            result.put("identifyCode", identifyCode);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        return result;
    }


    @RequestMapping(value = "/user/upload", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> uploadFileAfterLogin(@RequestParam(value = "file") CommonsMultipartFile file, @RequestParam(value = "isPublic") boolean isPublic,  HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = file.getOriginalFilename();
        String saveName = UUIDUtil.addUUID(fileName);
        String identifyCode = UUIDUtil.getRandomString(6);
        double size = file.getSize() / (1024.0 * 1024.0);
        User user = auth.getUser(request);
        if(user == null){
            return upload(file, request);
        }
        try {
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_UPLOAD_FILE_PATH);
            File f = new File(path);
            if(!f.exists() || !f.isDirectory()){        //创建文件夹
                f.mkdirs();
            }
            f = new File(path, saveName);
            file.transferTo(f);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setCreateTime(System.currentTimeMillis());
            fileInfo.setUpdateTime(System.currentTimeMillis());
            fileInfo.setFileSize(size);
            fileInfo.setDownloadCount(0);
            fileInfo.setPublic(true);
            fileInfo.setSaveName(saveName);
            fileInfo.setIdentifyCode(identifyCode);
            fileInfo.setOwner(user.getUsername());
            fileInfo.setPublic(isPublic);
            fileDao.save(fileInfo);
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            result.put("identifyCode", identifyCode);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        return result;
    }



}
