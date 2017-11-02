package com.baixin.ees.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jason on 2017/10/23/023.
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Map<String,Properties> propsMap = new HashMap<String,Properties>();

    /**
     * 通过文件名称获取prop对象
     * @param propFileName
     * @return
     */
    public static Properties getProp(String propFileName){
        Properties prop = propsMap.get(propFileName);
        if(prop == null){
            prop = loadProps(propFileName);
            if(prop != null){
                propsMap.put(propFileName,prop);
            }
        }
        return prop;
    }

    /**
     * 通过文件名称加载配置生成Properties对象
     * @param propFileName prop文件名
     * @return
     */
    private static Properties loadProps(String propFileName){
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(propFileName);
            prop.load(in);
            return prop;
        } catch (FileNotFoundException e) {
            logger.error(propFileName+"文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        return null;
    }

    /**
     * 通过配置文件名和键获取值
     * @param propFileName  配置文件名称
     * @param key  键值
     * @return
     */
    public static String getPropertyValue(String propFileName,String key){
        Properties prop = getProp(propFileName);
        if(prop == null){
            return null;
        }
        return prop.getProperty(key);
    }

    /**
     * 通过配置文件名和键和默认值获取值
     * @param propFileName 文件名称
     * @param key  键值
     * @param defaultValue  默认值
     * @return
     */
    public static String getPropertyValue(String propFileName,String key, String defaultValue) {
        Properties prop = getProp(propFileName);
        if(prop == null){
            return defaultValue;
        }
        return prop.getProperty(key,defaultValue);
    }
}

