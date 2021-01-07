package org.jeecg.modules.bigscreen.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Base64Utils extends Base64 {
    public Base64Utils() {
    }

    public static String encodeBase64String(byte[] binaryData, boolean chunk) {
        return StringUtils.newStringUtf8(encodeBase64(binaryData, chunk));
    }
}