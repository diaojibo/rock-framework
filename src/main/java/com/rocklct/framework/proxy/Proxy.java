package com.rocklct.framework.proxy;

/**
 * Created by Rocklct on 2017/9/25.
 */
public interface Proxy {

    /**
     *  used to execute proxy chain
     */

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
