package com.version.control.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 这个用法得注意，他不需要余下两个注解的功能，他使用的是注解传递，
 * 并且用的是{@link  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty}
 * 来进行的注入控制，不能说没用，就是挺麻烦的，
 * 一个版本就得重新再实现一个这个注解，然后把 ConditionalOnProperty 上的 havingValue给改成需要的
 *
 * @author MrJavaLiY
 */
@ConditionalOnProperty(
        name = {"server.version"},
        havingValue = "3.0"
)
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
public @interface VersionService {
}
