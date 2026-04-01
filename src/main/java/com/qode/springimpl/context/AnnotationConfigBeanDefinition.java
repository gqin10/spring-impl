package com.qode.springimpl.context;

import java.util.function.Supplier;

public class AnnotationConfigBeanDefinition<T> extends AbstractBeanDefinition<T> {

    private final Supplier<T> objSupplier;

    public AnnotationConfigBeanDefinition(String beanName, Supplier<T> objSupplier) {
        super(beanName);
        this.objSupplier = objSupplier;
    }

    @Override
    public T getObject() {
        return objSupplier.get();
    }
}
