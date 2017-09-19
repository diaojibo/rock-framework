package com.rocklct.framework.bean;

/**
 * Created by Rocklct on 2017/9/18.
 */
public class Data {

    /**
     *  the model data(json)
     */

    private Object model;

    public Data(Object model){
        this.model = model;
    }

    public Object getModel(){
        return model;
    }


}
