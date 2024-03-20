package com.version.control.annotation;

import com.version.control.VersionScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 版本控制扫描主要注解，作用在Application启动类上面，
 * 启动类上面使用这个注解，就可以启动后续的注册功能
 * @author  ly
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({VersionScanRegistrar.class})
public @interface VersionScan {
}
