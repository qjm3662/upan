package cn.qjm253.controller;

import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import cn.qjm253.utils.Config;
import cn.qjm253.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
@Controller
public class FileController extends BaseController {
    public static final String DEFAULT_PATH = "/WEB-INF/file/header/";
    public static final String DEFAULT_UPLOAD_FILE_PATH = "/WEB-INF/file/";

    /**
     * 头像下载
     * @param response
     * @param request
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadAvatar(@RequestParam String fileName,  HttpServletResponse response,
                                                 HttpServletRequest request) {
        //获取头像的保存，目录
        String dataDirectory = request.getServletContext().getRealPath(DEFAULT_PATH);
        File file = new File(dataDirectory, fileName);
        if(file.exists()){
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.addHeader("Content-Disposition", "attachment; filename="
                        + URLEncoder.encode(file.getName(), "utf-8"));
                byte[] buffer = new byte[1024];

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){     //直至把文件全部读出来
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(bis != null){
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 提取码下载文件
     *
     * @param identifyCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/download/{identifyCode}", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public void downloadFile(@PathVariable String identifyCode, HttpServletResponse response, HttpServletRequest request) {
        String[] params = {"identifyCode"};
        String hql = "from FileInfo f";
        hql = createHql(hql, "f", params);
        FileInfo fileInfo = fileDao.query(hql, params, identifyCode);
        if (fileInfo == null) {       //要下载的文件不存在（或者是提取码错误）
            return ;
        }
        //获取头像的保存，目录
        String dataDirectory = request.getServletContext().getRealPath(DEFAULT_UPLOAD_FILE_PATH);
        File file = new File(dataDirectory, fileInfo.getSaveName());
        if(file.exists()){      //如果文件存在则允许下载
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.addHeader("Content-Disposition", "attachment; filename="
                        + URLEncoder.encode(fileInfo.getFileName(), "utf-8"));
                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(bis != null){
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 非登录上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> upload(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = file.getOriginalFilename();
        String saveName = UUIDUtil.addUUID(fileName);
        String identifyCode = UUIDUtil.getRandomString(6);
        double size = file.getSize() / (1024.0 * 1024.0);
        try {
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_UPLOAD_FILE_PATH);
            File f = new File(path);
            if (!f.exists() || !f.isDirectory()) {        //创建文件夹
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


    /**
     * 登录以后上传
     *
     * @param file
     * @param isPublic
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> uploadFileAfterLogin(@RequestParam(value = "file") CommonsMultipartFile file, @RequestParam(value = "isPublic") boolean isPublic, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = file.getOriginalFilename();
        String saveName = UUIDUtil.addUUID(fileName);
        String identifyCode = UUIDUtil.getRandomString(6);
        double size = file.getSize() / (1024.0 * 1024.0);
        User user = auth.getUser(request);
        if (user == null) {
            return upload(file, request);
        }
        user = userDao.get(User.class, user.getUid());      //通过Id再从数据库中获取user对象，获取到的应该是持久化对象
        System.out.println("isPublic : " + isPublic);
        try {
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_UPLOAD_FILE_PATH);
            File f = new File(path);
            if (!f.exists() || !f.isDirectory()) {        //创建文件夹
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
            fileInfo.setPublic(isPublic);
            fileInfo.setSaveName(saveName);
            fileInfo.setIdentifyCode(identifyCode);
            fileInfo.setOwner_user(user);
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


    /**
     * 修改头像
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/modifyAvatar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> modifyAvatar(@RequestParam(value = "avatar") CommonsMultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = auth.getUser(request);
        if (user == null) {       //未登录或者登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        String fileName = file.getOriginalFilename();
        String saveName = UUIDUtil.addUUID(fileName);
        try {
            String path = request.getSession().getServletContext().getRealPath(DEFAULT_PATH);
            File f = new File(path);
            if (!f.exists() || !f.isDirectory()) {        //创建文件夹
                f.mkdirs();
            }
            f = new File(path, saveName);
            file.transferTo(f);
            String avatar = Config.url + "/download?fileName=" + f.getName();
            //开始修改用户的头像信息
            String[] params = {"avatar", "usn"};
            String sql = "update User u set u.avatar = :avatar where u.username = :usn";
            if(userDao.update(sql, params, avatar, user.getUsername()))
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        return result;
    }


}
