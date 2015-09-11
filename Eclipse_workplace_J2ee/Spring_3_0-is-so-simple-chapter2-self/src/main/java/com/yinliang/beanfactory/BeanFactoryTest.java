package com.yinliang.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.yinliang.beans.Car;

public class BeanFactoryTest {
	public static void main(String[] args) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		Resource resource = resolver.getResource("classpath:com/yinliang/beanfactory/bean.xml");

		BeanFactory beanFactory = new XmlBeanFactory(resource);

		Car car = beanFactory.getBean("car", Car.class);

		System.out.println(car);
	}
}
