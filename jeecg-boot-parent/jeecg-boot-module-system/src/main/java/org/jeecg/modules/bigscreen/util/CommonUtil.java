package org.jeecg.modules.bigscreen.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.jeecg.modules.bigscreen.bean.PropertyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static String get32UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static CustomArtemisHttpUtil getCustomArtemisHttpUtil() {
        PropertyBean propertyBean = ServiceContextUtil.getBean(PropertyBean.class);
        CustomArtemisHttpUtil customArtemisHttpUtil = new CustomArtemisHttpUtil(propertyBean.getHikIp(),
                propertyBean.getAppkey(), propertyBean.getAppSecret());
        return customArtemisHttpUtil;
    }

    public static byte[] InputStream2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 对文件进行base64加密
     */
    public static String getImageBinary(String Imgpath) {
        File file = new File(Imgpath);
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            return new String(Base64Utils.encodeBase64(data));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String compile(String path) {
        return TextUtils.isEmpty(path) ? "" : compile(path, "utf-8");
    }

    public static String compile(String path, String encode) {
        if (TextUtils.isEmpty(path)) {
            return "";
        } else {
            try {
                path = URLDecoder.decode(path.trim(), encode);
            } catch (UnsupportedEncodingException var3) {
                log.error("URLDecoder.decode error", var3);
            }

            return path.replace("/", File.separator);
        }
    }

    public static boolean isWindow() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        } else {
            return false;
        }
    }




    /**
     * 在SpringMvc中获取到Session
     *
     * @return
     */
    public static HttpSession getSession() {
        //获取到Session对象
        HttpSession session = getHttpServletRequest().getSession();
        //获取到Response对象
        //HttpServletResponse response = attrs.getResponse();
        return session;
    }

    public static HttpServletRequest getHttpServletRequest() {
        //获取到ServletRequestAttributes 里面有
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到Request对象
        HttpServletRequest request = attrs.getRequest();
        return request;
    }

    //计算base64图片的字节数(单位:字节)
    //传入的图片base64是去掉头部的data:image/png;base64,字符串
    public static int imageSize(String imageBase64Str) {

        //1.找到等号，把等号也去掉(=用来填充base64字符串长度用)
        int equalIndex = imageBase64Str.indexOf("=");
        if (equalIndex > 0) {
            imageBase64Str = imageBase64Str.substring(0, equalIndex);
        }
        //2.原来的字符流大小，单位为字节
        int strLength = imageBase64Str.length();
        //3.计算后得到的文件流大小，单位为字节
        int size = strLength - (strLength / 8) * 2;
        return size;
    }

    /**
     * 将属性写到配置文件里面
     */
    public void writeProperties(String propertiesPath, Properties newProp) {
        File propertiesFile = new File(propertiesPath);
        if (propertiesFile.isFile() && propertiesFile.exists()) {
            //store方法，第一个参数是输入流，第二个是提示信息。#Sun Jan 07 18:47:58 CST 2018
            FileOutputStream fileOutputStream =null;
            try {
                fileOutputStream = new FileOutputStream(propertiesFile);
                newProp.store(fileOutputStream, new Date().toString());
                fileOutputStream.flush();
            } catch (IOException e) {
                log.error("error", e);
            }finally {
                if(fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        log.error("error", e);
                    }
                }
            }
        } else {
            log.error("{}文件不存在", propertiesPath);
        }
    }

    /**
     * 获得项目工程的绝对路径
     */
    public String getWebRoot(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获得发布后的jar当前路径
     */
    public String getJarRoot(){
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = home.getSource();
        return jarFile.getParentFile().getPath();
    }

    /**
     * 如果已打成jar包，则返回jar包所在目录
     * 如果未打成jar，则返回target所在目录
     * @return
     */
    public String getRootPath() throws UnsupportedEncodingException {
        // 项目的编译文件的根目录
        String path = URLDecoder.decode(getClass().getResource("/").getPath(), String.valueOf(StandardCharsets.UTF_8));
        if (path.startsWith("file:")) {
            int i = path.indexOf(".jar!");
            path = path.substring(0, i);
            path = path.replaceFirst("file:", "");
        }
        // 项目所在的目录
        return new File(path).getParentFile().getAbsolutePath();
    }

    public static String getCookie(String key){
        HttpServletRequest request = getHttpServletRequest();
        for (Cookie cookie : request.getCookies()) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static String getHost(HttpServletRequest request) {
        String ip = request.getHeader("Host");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            String xForwaredFor = request.getHeader("X-Forwarded-For");
            if (StringUtils.isNotBlank(xForwaredFor)) {
                String[] hosts = xForwaredFor.split(",");
                if (hosts.length >= 2) {
                    ip = hosts[1].trim();
                }
            }
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = null;
        }

        return ip;
    }

    public static String[] getSchemas(HttpServletRequest request) {
        String xfp = request.getHeader("X-Forwarded-Proto");
        String[] schemas;
        if (!StringUtils.isBlank(xfp) && !"unknown".equalsIgnoreCase(xfp)) {
            schemas = xfp.split(",");
        } else {
            schemas = new String[]{request.getScheme()};
        }

        return schemas;
    }
}
