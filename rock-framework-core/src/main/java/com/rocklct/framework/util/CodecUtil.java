package com.rocklct.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Rocklct on 2017/9/18.
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     *  encode url
     */
    public static String encodeURL(String source){
        String target;
        try{
            target = URLEncoder.encode(source,"UTF-8");
        }catch (Exception e){
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     *  decode url
     */
    public static String decodeURL(String source){
        String target;
        try{
            target = URLDecoder.decode(source,"UTF-8");
        }catch (Exception e){
            LOGGER.error("decode url failure",e);
            throw new RuntimeException(e);
        }

        return target;
    }

}
