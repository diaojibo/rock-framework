package com.rocklct.framework.bean;

import com.rocklct.framework.util.CastUtil;

import java.util.Map;

/**
 * Encapsulating request params from the client into a bean
 * <p>
 * Created by Rocklct on 2017/9/14.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    // get Long type param value
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    //get all the param fields
    public Map<String, Object> getMap() {
        return paramMap;
    }


}
