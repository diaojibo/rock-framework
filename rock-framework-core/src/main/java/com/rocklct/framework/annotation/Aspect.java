package com.rocklct.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by Rocklct on 2017/9/25.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
