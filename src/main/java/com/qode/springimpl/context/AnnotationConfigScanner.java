package com.qode.springimpl.context;

import com.qode.springimpl.exception.InvalidConfigurationConstructorException;
import com.qode.springimpl.exception.NotConfigurationException;
import com.qode.springimpl.annotation.Bean;
import com.qode.springimpl.annotation.Configuration;
import com.qode.springimpl.util.StringUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

public class AnnotationConfigScanner {

    private Class<?> configClass;

    public AnnotationConfigScanner(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(Configuration.class)) {
            throw new NotConfigurationException(String.format("%s is not a class of @Configuration", configClass.getName()));
        }
        this.configClass = configClass;
    }

    public void scan(BeanRegistry beanRegistry) {
        Object configInst;

        try {
            configInst = configClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new InvalidConfigurationConstructorException(String.format("Cannot create instance for %s", configClass.getName()));
        }
        Arrays.stream(configClass.getMethods())
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .forEach(method -> {
                    this.registerBeanDefinition(method, beanRegistry, configInst);
                });
    }

    private void registerBeanDefinition(Method method, BeanRegistry beanRegistry, Object configInst) {
        Supplier<?> beanSupplier = () -> {
            try {
                return method.invoke(configInst);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        Bean bean = method.getAnnotation(Bean.class);
        String beanName = StringUtil.isNotEmpty(bean.value()) ? bean.value() : method.getName();
        beanRegistry.addBeanDefinition(new AnnotationConfigBeanDefinition(beanName, beanSupplier));
    }
}
