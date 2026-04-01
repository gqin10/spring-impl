package com.qode.springimpl.context;

public abstract class AbstractBeanDefinition<T> {

    protected final String beanName;

    public AbstractBeanDefinition(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public abstract T getObject();
}
