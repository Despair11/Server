package org.jeecg.modules.bigscreen.util;

import java.util.*;

public class BatchListUtil {

    public static <T, K> Map<Integer, List<K>> batchList(Collection<T> list, int batchSize, K k) {
        Map<Integer, List<K>> itemMap = new HashMap<>();
        itemMap.put(1, new ArrayList<K>());
        for (T t : list) {
            List<K> batchList = itemMap.get(itemMap.size());
            if (batchList.size() == batchSize) {
                //当list满足批次数量，新建一个list存放后面的数据
                batchList = new ArrayList<>();
                itemMap.put(itemMap.size() + 1, batchList);
            }
            batchList.add((K)t);
        }
        return itemMap;
    }

    public static <T> Map<Integer, List<T>> batchList(Collection<T> list, int batchSize) {
        Map<Integer, List<T>> itemMap = new HashMap<>();
        itemMap.put(1, new ArrayList<T>());
        for (T t : list) {
            List<T> batchList = itemMap.get(itemMap.size());
            if (batchList.size() == batchSize) {
                //当list满足批次数量，新建一个list存放后面的数据
                batchList = new ArrayList<>();
                itemMap.put(itemMap.size() + 1, batchList);
            }
            batchList.add(t);
        }
        return itemMap;
    }
}
