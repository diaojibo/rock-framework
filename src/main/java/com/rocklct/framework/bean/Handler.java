package com.rocklct.framework.bean;

import java.lang.reflect.Method;

/**
 * Encapsulate the Action information
 *
 * Created by Rocklct on 2017/9/14.
 *
 */
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
