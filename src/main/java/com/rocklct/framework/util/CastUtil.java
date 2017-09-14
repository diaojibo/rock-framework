package com.rocklct.framework.util;

import javafx.beans.binding.ObjectExpression;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rocklct on 2017/9/4.
 */
public final class CastUtil {

    //transform other object to String
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static String castString(Object obj) {
        return castString(obj, "");
    }

    //transform other object to double
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);

    }

    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0);
    }

    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj!=null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

}
