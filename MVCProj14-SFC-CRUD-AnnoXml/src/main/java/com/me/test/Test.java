package com.me.test;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.me.dao.StudentOperationsDao;
import com.me.dao.StudentOperationsDaoImpl;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = null;
		ctx = new ClassPathXmlApplicationContext("persistance-beans.xml");
		StudentOperationsDao dao = ctx.getBean("studentDao", StudentOperationsDaoImpl.class);
		System.out.println(dao.fetchList());
		
	}

}
