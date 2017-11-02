package com.baixin.ees.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @Author 王岚枫
 * @Datetime 2017年10月26日 11:09
 */
public abstract class BlankUtil {

    private BlankUtil() {
    }

    public static boolean isBlank(Object o) {

        if (o == null)
            return true;

        if (o instanceof String)
            return ((String) o).trim().length() == 0 ? true : false;

        if (o.getClass().isArray())
            return Array.getLength(o) == 0 ? true : false;

        if (o instanceof Collection)
            return ((Collection<?>) o).size() == 0 ? true : false;

        if (o instanceof Map)
            return ((Map<?, ?>) o).isEmpty() ? true : false;

        return false;
    }

    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }

    public static boolean isBlankAll(Object... o) {

        if (o == null)
            return true;

        for (Object obj : o)
            if (isNotBlank(obj))
                return false;

        return true;
    }

    public static boolean isNotBlankAll(Object... o) {

        if (o == null)
            return false;

        for (Object obj : o)
            if (isBlank(obj))
                return false;

        return true;
    }

    public static boolean httpNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str)
                || "undefined".equalsIgnoreCase(str);
    }

    public static boolean httpNotNull(String str) {
        return !httpNull(str);
    }
}
