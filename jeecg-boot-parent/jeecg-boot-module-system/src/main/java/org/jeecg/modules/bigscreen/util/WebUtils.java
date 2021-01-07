package org.jeecg.modules.bigscreen.util;
 
import java.io.IOException;
import java.io.InputStream;

public class WebUtils {
 
 
    /**
     * 根据文件流判断图片类型
     *
     * @param is
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(InputStream is) {
        //读取文件的前几个字节来判断图片格式
        byte[] b = new byte[4];
        try {
            is.read(b, 0, b.length);
            return getPicTypeFromBytes(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.reset();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String getPicTypeFromBytes(byte[] bytes){
        String type = byteArrayToHexString(bytes).toUpperCase();
        if (type.contains("FFD8FF")) {
            return "jpg";
        } else if (type.contains("89504E47")) {
            return "png";
        } else if (type.contains("47494638")) {
            return "gif";
        } else if (type.contains("424D")) {
            return "bmp";
        } else {
            return "unknown";
        }
    }
 
 
    /**
     * 转换字节数组为十六进制字符串
     *
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
 
    // 十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
 
    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
