package com.rocklct.framework.helper;

import com.rocklct.framework.annotation.Inject;
import com.rocklct.framework.util.ArrayUtil;
import com.rocklct.framework.util.CollectionUtil;
import com.rocklct.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Rocklct on 2017/9/13.
 */
public final class IocHelper {

    static {

        // get all the bean classes and their instance.
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //get all the member variables of the specific class
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    //traverse all the beanField
                    for (Field beanField : beanFields) {
                        //check if the field have specif Injection
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            //get the type of the member variable
                            Class<?> beanFieldClass = beanField.getType();
                            //search the corresponding instance from the beanMap
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            // if the beanService exists
                            if (beanFieldInstance != null) {

                                //use ReflectionUtil to implement dependency injection
                                ReflectionUtil.setField(beanInstance, beanField
                                        , beanFieldInstance);
                            }

                        }
                    }


                }


            }
        }

    }


}
