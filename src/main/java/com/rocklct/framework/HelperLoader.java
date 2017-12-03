package com.rocklct.framework;

import com.rocklct.framework.annotation.Controller;
import com.rocklct.framework.helper.*;
import com.rocklct.framework.util.ClassUtil;

/**
 * Created by Rocklct on 2017/9/14.
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                ControllerHelper.class,
                AopHelper.class,
                IocHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
