package com.rocklct.framework.util;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Created by Rocklct on 2017/9/13.
 */
public final class ArrayUtil {

    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array){
        return !isEmpty(array);
    }
}
