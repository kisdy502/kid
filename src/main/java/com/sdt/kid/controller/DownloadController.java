package com.sdt.kid.controller;

import com.sdt.kid.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import org.xnio.IoUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/apppush/api")
public class DownloadController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    final static String realPath = "G:\\uapks\\apks";

    private final StorageService storageService;

    @Autowired
    public DownloadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @RequestMapping("/listForceApp")
    @GetMapping
    public ResponeBean listForceApp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        List<AppData> list = new ArrayList<>();
        AppData appData = new AppData();
        appData.setAppId(1);
        appData.setAppName("蜜蜂市场");
        appData.setPackageName("tv.beemarket");
        appData.setApkMd5("702F29807FCBD429206B825CD9E5D01F");
        appData.setApkSize(9574730L);
        appData.setApkUrl("http://192.168.66.67:8989/apppush/api/download/BeeMarket_1.15.25_release_avatar.apk");
        appData.setVersionName("1.15.25");
        appData.setVersionCode(11525);


        AppData appData2 = new AppData();
        appData2.setAppId(2);
        appData2.setAppName("FMFileTool");
        appData2.setPackageName("cn.fengmang.file");
        appData2.setApkMd5("784B207D6B39458F5B8D2A11AD673A32");
        appData2.setApkSize(7337703L);
        appData2.setApkUrl("http://192.168.66.67:8989/apppush/api/download/FMFileTool_1.1.00.apk");
        appData2.setVersionName("1.1.00");
        appData2.setVersionCode(1100);

        list.add(appData);
        list.add(appData2);

        ResponeBean responeBean = new ResponeBean();
        responeBean.setAppList(list);
        responeBean.setStatus(0);
        responeBean.setMsg("获取强推应用列表成功");

        return responeBean;

    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @RequestMapping("/download2/{url}")
    @GetMapping
    public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String query = request.getQueryString();
        String query2 = request.getServletPath();
        String query3 = request.getMethod();
        logger.info("query:" + query);
        logger.info("query2:" + query2);
        logger.info("query3:" + query3);
        String fileName = request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1, request.getServletPath().length());
        logger.info("query4:" + fileName);
        if (StringUtils.isEmpty(fileName)) {
            return;
        }
        File file = new File(realPath, fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            //response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            //response.setContentType("multipart/form-data;charset=UTF-8");也可以明确的设置一下UTF-8，测试中不设置也可以。
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("下载成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IoUtils.safeClose(fis);
                IoUtils.safeClose(bis);
            }
        }
        return;
    }


    class ResponeBean {
        private int status;
        private String msg;
        private List<AppData> appList;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<AppData> getAppList() {
            return appList;
        }

        public void setAppList(List<AppData> appList) {
            this.appList = appList;
        }
    }

    public class AppData {
        private int appId;
        private String appName;
        private String packageName;
        private int versionCode;
        private String versionName;
        private String apkUrl;
        private String apkMd5;
        private long apkSize;


        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getApkMd5() {
            return apkMd5;
        }

        public void setApkMd5(String apkMd5) {
            this.apkMd5 = apkMd5;
        }

        public long getApkSize() {
            return apkSize;
        }

        public void setApkSize(long apkSize) {
            this.apkSize = apkSize;
        }

    }
}
