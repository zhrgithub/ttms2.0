package common.dao;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class TestDruid {
	
	
	
	
	@Test
	//测试数据库连接池
	public  void testDurid(){
		
//		 1.测试ApplicationContext对象
		ApplicationContext ctx=
				new ClassPathXmlApplicationContext("spring-pool.xml");
//		 2.获得DataSource对象
				DataSource dataSource=(DataSource)ctx.getBean("dataSource");
//		 3.测试数据对象源是否为空
				System.out.println(dataSource);
				Assert.assertNotEquals(null, dataSource);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
