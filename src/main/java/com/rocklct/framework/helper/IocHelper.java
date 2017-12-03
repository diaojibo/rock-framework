package com.rocklct.framework.helper;

import com.rocklct.framework.annotation.Inject;
import com.rocklct.framework.util.ArrayUtil;
import com.rocklct.framework.util.CollectionUtil;
import com.rocklct.framework.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by Rocklct on 2017/9/13.
 */
public final class IocHelper {

    private static Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);

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

                            LOGGER.debug("ioc filed:" + beanField.getName());

                            // if the beanService exists
                            if (beanFieldInstance != null) {

                                //use ReflectionUtil to implement dependency injection
                                ReflectionUtil.setField(beanInstance, beanField
                                        , beanFieldInstance);

                                LOGGER.debug("ioc filed set complete from:" + beanInstance.getClass().getName());
                                LOGGER.debug("ioc filed set complete to:" + beanFieldInstance.getClass().getName());
                            }

                        }
                    }


                }


            }
        }

    }


}
