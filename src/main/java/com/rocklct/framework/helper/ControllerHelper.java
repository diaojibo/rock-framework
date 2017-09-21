package com.rocklct.framework.helper;

import com.rocklct.framework.annotation.Action;
import com.rocklct.framework.bean.Handler;
import com.rocklct.framework.bean.Request;
import com.rocklct.framework.util.ArrayUtil;
import com.rocklct.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rocklct on 2017/9/14.
 */
public final class ControllerHelper {

    /**
     * This map is to store the mapping relationship between Request and Handler
     */
    private static final Map<Request, Handler> Action_Map = new HashMap<Request, Handler>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelper.class);


    static {
        /**
         * get all the Controller
         */

        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {

            // traverse all the elements
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    //traverse methods array
                    for (Method method : methods) {
                        //to check if the method is annotated with Action
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            //check if the request in the Action Annotation is valid
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {

                                    // get the requestMethod and requestPath from the Annotation
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);

                                    //put the request and corresponding handler into the map
                                    Action_Map.put(request, handler);
                                }
                            }

                        }
                    }
                }

            }
        }


    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod,requestPath);
        return Action_Map.get(request);
    }

}
