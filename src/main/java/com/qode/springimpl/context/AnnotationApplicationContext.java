package com.qode.springimpl.context;

public class AnnotationApplicationContext implements IApplicationContext {

    private AnnotationConfigScanner scanner;

    private BeanRegistry beanRegistry;

    public AnnotationApplicationContext(Class<?> configClass) {
        beanRegistry = new BeanRegistry();
        scanner = new AnnotationConfigScanner(configClass);
        scanner.scan(beanRegistry);
        beanRegistry.refresh();
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return beanRegistry.getBean(clazz);
    }

    @Override
    public Object getBean(String beanName) {
        return beanRegistry.getBean(beanName);
    }

    @Override
    public Object getBeans(Class<?> clazz) {
        return beanRegistry.getBeans(clazz);
    }
}
