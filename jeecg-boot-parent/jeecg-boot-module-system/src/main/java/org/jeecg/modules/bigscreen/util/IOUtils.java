package org.jeecg.modules.bigscreen.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.jeecg.modules.bigscreen.bean.FileBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: jiaosen
 * @date: 2019/9/7 10:43
 * @description: 图片通用处理类
 */
@Slf4j
public class IOUtils {

    /**
     * 从本地读取图片数据信息
     *
     * @param path 本地图片地址
     * @return 图片流
     */
    public static FileBean loadFileByPath(String path) throws IOException {
        FileBean fileBean = new FileBean();

        FileInputStream stream = null;
        FileChannel fileChannel = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                log.error("加载文件[{}]失败", path);
                return fileBean;
            }

            stream = new FileInputStream(file);
            fileChannel = stream.getChannel();

            log.info("stream available :[{}]", stream.available());
            log.info("file length :[{}]", file.length());

            //字节
            ByteBuffer buffer = ByteBuffer.allocate(stream.available());
            stream.getChannel().read(buffer);

            fileBean.setFileMetaData(buffer.array());
            fileBean.setFileSize(buffer.array().length);
            buffer.clear();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return fileBean;
    }

    /**
     * 从网络流中读取文件
     *
     * @return
     */
    public static FileBean loadFileByNetwork(URL url) {
        FileBean fileBean = new FileBean();

        // 服务器返回的状态
        int httpResult;
        try {
            // 试图连接并取得返回状态码
            URLConnection urlconn = url.openConnection();
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            httpResult = httpconn.getResponseCode();
            if (httpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                // 取数据长度
                int filesize = urlconn.getContentLength();
                InputStream inputStream = urlconn.getInputStream();
                byte[] fileMetaData = new byte[filesize];
                int result = inputStream.read(fileMetaData);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return fileBean;
    }

    /**
     * base64转图片信息
     *
     * @param base64Img
     * @return
     */
    public static FileBean getMetadataByBase64(String base64Img) {
        FileBean fileBean = new FileBean();
        if (StringUtils.isBlank(base64Img)) {
            return null;
        }

        try {
            byte[] b = Base64Utils.decodeBase64(base64Img);
            fileBean.setFileMetaData(b);
            fileBean.setFileSize(b.length);
        } catch (Exception e) {
            log.error("base64转图片数据失败");
            return null;
        }
        return fileBean;
    }

    /**
     * 将文件流转换成base64
     *
     * @return
     */
    public static String encodeByte2Base64String(byte[] fileMetadata) {
        return Base64Utils.encodeBase64String(fileMetadata);
    }

    /**
     * 获取操作系统临时目录
     *
     * @return
     */
    public static String getTmpDir() {
        return System.getProperty("java.io.tmpdir") + File.separator;
    }

    public static File getTmpFile(String fileName){
        String parentPath = getTmpDir();
        File parentDirectory = new File(parentPath);

        if(!parentDirectory.exists()){
            parentDirectory.mkdirs();
        }

        File file =  new File(parentDirectory,fileName);
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error("error",e);
        }
        return file;
    }

    /**
     * 删除文件夹
     */
    public static void deleteFile(String path) {
        log.info(" 删除文件:{}", path);
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }


    public static void writeImageToPath(byte[] imageByte, String path) {
        OutputStream outputStream = null;
        try {
            File file = new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();

            }

            outputStream = new FileOutputStream(file);
            outputStream.write(imageByte);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文件转化成base64
     * */
    public static String getStringByFile(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            fileInputStream.read(buffer);
            return new String(buffer,"utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将文件转化成base64
     * */
    public static String getBase64ByFile(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            fileInputStream.read(buffer);
            return Base64Utils.encodeBase64String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static File writeBase64ToTmpPath(String base64String, String fileName) {
        byte[] imageByte = Base64Utils.decodeBase64(base64String);
        return writeBytesToTmpPath(imageByte,fileName);
    }

    public static File writeBytesToTmpPath(byte[] imageByte, String fileName) {
        File tmpFile = getTmpFile(fileName);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(tmpFile);
            outputStream.write(imageByte);
            outputStream.flush();
        } catch (IOException e) {
            log.error("error",e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("error",e);
                }
            }
        }

        return tmpFile;
    }
}
