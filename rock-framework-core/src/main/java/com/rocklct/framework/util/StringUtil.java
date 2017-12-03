package com.rocklct.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rocklct on 2017/9/4.
 */
public final class StringUtil {

    //to judge if the String is empty
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
