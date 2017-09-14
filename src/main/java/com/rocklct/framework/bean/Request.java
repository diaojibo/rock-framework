package com.rocklct.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Rocklct on 2017/9/13.
 */
public class Request {

    private String requestMethod;

    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath(){
        return  requestPath;
    }

    //use method in apache.commons.lang3 to override default hashCode() method.
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    //use method in apache.commons.lang3 to override default equals() method.
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
}
