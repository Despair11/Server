package org.jeecg.modules.bigscreen.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class ServiceContextUtil implements ApplicationContextAware {
	private static ApplicationContext context = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServiceContextUtil.context = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getContext() {
		if (context == null)
			throw new IllegalStateException("can not found application context.");
		return context;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		if (context == null)
			throw new IllegalStateException("can not find application context.");
		try {
			return (T) context.getBean(name);
		} catch (BeansException e) {

		}
		return (T) null;
	}

	

	public static <T> T getBean(Class<T> cls) {
		if (context == null)
			throw new IllegalStateException("can not find application context.");
		try {
			return (T) context.getBean(cls);
		} catch (BeansException e) {

		}
		return (T) null;
	}

	
	public static <T> Map<String, T> getBeansOfType(Class<T> cls) {
		if (context == null)
			throw new IllegalStateException("can not found application context.");
		try {
			return context.getBeansOfType(cls);
		} catch (BeansException e) {

		}
		return Collections.emptyMap();
	}

	
	public static Resource getResource(String location) {
		return context.getResource(location);
	}

}
