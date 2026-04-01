package com.qode.springimpl.context;

import com.qode.springimpl.exception.BeanNotFoundException;
import com.qode.springimpl.exception.DuplicateBeanNameException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanRegistry {

    private List<AbstractBeanDefinition<?>> beanDefinitionList;

    private Map<String, Object> beans;

    public BeanRegistry() {
        beanDefinitionList = new ArrayList<>();
        beans = new HashMap<>();
    }

    public void addBeanDefinition(AbstractBeanDefinition<?> beanDef) {
        beanDefinitionList.add(beanDef);
    }

    public Map<String, Object> getBeans() {
        return beans;
    }

    public <T> T getBean(Class<T> clazz) {
        return beans.values().stream().filter(bean -> bean.getClass().isAssignableFrom(clazz)).map(item -> (T) item).findAny().orElse(null);
    }

    public <T> List<T> getBeans(Class<T> clazz) {
        return beans.values().stream().filter(bean -> bean.getClass().isAssignableFrom(clazz)).map(item -> (T) item).toList();
    }

    public <T> T getBean(String beanName) {
        if (beans.containsKey(beanName)) {
            return (T) beans.get(beanName);
        }
        throw new BeanNotFoundException(String.format("%s bean not found", beanName));
    }

    public void refresh() {
        beanDefinitionList.forEach(this::createBean);
    }

    private void createBean(AbstractBeanDefinition beanDef) {
        if (beans.containsKey(beanDef.getBeanName())) {
            throw new DuplicateBeanNameException(String.format("Duplicate bean name %s", beanDef.getBeanName()));
        }
        beans.put(beanDef.getBeanName(), beanDef.getObject());
    }
}
