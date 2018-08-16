package common.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSessionFactory {
	
	@Test
	//测试mybatis
	public void testSessionFty(){
			ApplicationContext ctx=
			new ClassPathXmlApplicationContext("spring-mvc.xml","spring-pool.xml",
					"spring-mybatis.xml");    
			Object sessionFactory=ctx.getBean("sqlSessionFactory");
			System.out.println(sessionFactory);
			Assert.assertNotEquals(sessionFactory, null);
	}
}
