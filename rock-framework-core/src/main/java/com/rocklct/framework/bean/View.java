package com.rocklct.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rocklct on 2017/9/14.
 */
public class View {

    //the path of the view
    private String path;

    /**
     * the model data(jsp)
     */

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }


}
