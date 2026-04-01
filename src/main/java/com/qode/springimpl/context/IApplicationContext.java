package com.qode.springimpl.context;

public interface IApplicationContext {

    Object getBean(Class<?> clazz);

    Object getBean(String beanName);

    Object getBeans(Class<?> clazz);
}
