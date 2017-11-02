package com.baixin.ees.util;

import java.math.BigDecimal;
import java.util.UUID;


public class GeneratePK {

    /**
     * @title 生成20位长度的主键
     * @return
     */
    public static BigDecimal generatePrimary20Key() {
        long timepk = System.currentTimeMillis();// 13为的时间类型
        String randowpk = (Math.random() * 1000000 + "").split("\\.")[0];// 7位随机生成
        return new BigDecimal(timepk + randowpk);
    }


    /**
     * 生成uuid主键
     * 
     * @return
     */
    public static String generatePrimaryUUIDKey() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}