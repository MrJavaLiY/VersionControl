package com.version.control.annotation;

import java.lang.annotation.*;

/**
 * 作用在类上面，注意，这个注解不能和@service，@Component共用，共用的话不能起到作用
 * @author User
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Inherited
public @interface Version {
    /**
     * 希望受到某个版本控制，与yml中的配置版本相呼应，二者相等才能被注入bean
     * @return
     */
    String value() default "0";
}
