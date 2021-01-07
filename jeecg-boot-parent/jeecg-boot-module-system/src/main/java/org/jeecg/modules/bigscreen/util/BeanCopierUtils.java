package org.jeecg.modules.bigscreen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

public class BeanCopierUtils {

    private static final Logger log = LoggerFactory.getLogger(BeanCopierUtils.class);
    // BeanCopier的内存缓存
    public static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        // 没有使用Converter
        copier.copy(source, target, null);
    }

    // BeanCopier缓存的Key
    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
