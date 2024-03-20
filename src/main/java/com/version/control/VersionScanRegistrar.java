package com.version.control;

import com.version.control.annotation.Version;
import com.version.exception.NotVersionClassRegistryException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 版本控制器核心注册类，主要功能就是吧所有被*
 * <li>{@link Version ClassLoader}</li>
 * 修饰的类提出来，按照yml配置的版本号来进行注入，从而实现一个动态的bean版本控制
 *
 * @author ly-User
 * @Date 2024-03-20 16:33
 */

public class VersionScanRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private String version = "";
    private final String DEFAULT_VERSION = "0";

    /**
     * 总控制，也是受spring管理的方法
     *
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry               current bean definition registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
        registerServiceAnnotationPostProcessor(packagesToScan, registry);
    }

    /**
     * 获取Application启动类所在地址
     *
     * @param metadata
     * @return
     */
    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
    }


    /**
     * Registers
     *
     * @param packagesToScan packages to scan without resolving placeholders
     * @param registry       {@link BeanDefinitionRegistry}
     * @since 2.5.8
     */
    private void registerServiceAnnotationPostProcessor(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        AtomicBoolean noReg = new AtomicBoolean(true);
        packagesToScan.forEach(p -> {
            Set<Class<?>> classList = ClassUtil.getClasses(p);
            for (Class<?> c : classList) {
                if (c.getAnnotation(Version.class) != null) {
                    String annotationVersion = c.getAnnotation(Version.class).value();
                    if (DEFAULT_VERSION.equals(version) || annotationVersion.equals(version)) {
                        String beanName = c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
                        //        //构造bean定义
                        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(c);
                        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
                        //如果已经有现成的bean了就跳过
                        if (registry.containsBeanDefinition(beanName)) continue;
                        //注册bean定义
                        registry.registerBeanDefinition(beanName, beanDefinition);
                        noReg.set(false);
                    }
                }
            }
        });
        if (noReg.get()) {
            throw new NotVersionClassRegistryException("没有发现受控制版本类版本号：" + version);
        }

    }

    /**
     * 获取yml中配置的版本
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        version = environment.getProperty("server.version");
        version = version == null ? DEFAULT_VERSION : version;
    }
}
