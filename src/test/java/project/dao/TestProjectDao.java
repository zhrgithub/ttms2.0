package project.dao;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.dao.ProjectDao;
import cn.aliyun.ttms.project.entity.Project;
import common.dao.TestBaseDao;

public class TestProjectDao extends TestBaseDao{
	
	@Test
	public void insertObject() throws Exception{
		//1.获得ApplicationContext对象
		//2.获得dao对象
		ProjectDao projectDao=(ProjectDao)ctx.getBean("projectDao");
		//3.构建entity对象
		Project  entity=new Project();
		entity.setId(4);
		entity.setCode("tt-20180512-chm-lf-001");
		entity.setName("三亚五日游");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		entity.setBeginDate(sdf.parse("2018-05-12"));
		entity.setEndDate(sdf.parse("2018-06-12"));
		entity.setValid(1);
		entity.setNote("三亚五日游");
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		//4.将对象写入到数据库
		int n=projectDao.insertObject(entity);
		Assert.assertEquals(1, n);
		
	}
	
	@Test
	public void testfindObjects(){
		ProjectDao dao=(ProjectDao)ctx.getBean("projectDao");
		List<Project> list=dao.findObjects();
		System.out.println(list);
		Assert.assertNotEquals(null, list);
	
	}
	
	@Test
	public void testFindPageObjects(){
		ProjectDao dao=(ProjectDao)ctx.getBean("projectDao");
		//startIndex=0;pageSize=2;
		PageObject pageObject=new PageObject();
		Project project=new Project();
		//总记录数
		int rowCount=dao.getRowCount(project);
		//获得总页数（根据记录数，pageSize计算总页数）
		pageObject.setRowCount(rowCount);
		int pageCount=pageObject.getPageCount();
		//System.out.println("0000000000000000000000000000000000");
		System.out.println("pageCount="+pageCount);
		//获得当前页的记录（当前页为1）
		List<Project> list=dao.findPageObjects(project,pageObject);
		System.out.println(list);
		//System.out.println(project.getName());
		Assert.assertEquals(2, list.size());
	}
	
	
	@Test
	public void testValidById(){
		ProjectDao dao=(ProjectDao) ctx.getBean("projectDao");
		int rows=dao.validById(new String[] {"1"}, 0);
		System.out.println("valid.rows="+rows);
		Assert.assertNotEquals(-1, rows);
	}
	
	@Test
	public void testsaveObject(){
		ProjectDao dao=(ProjectDao) ctx.getBean("projectDao");
		int rows=dao.validById(new String[] {"1"}, 0);
		System.out.println("valid.rows="+rows);
		Assert.assertNotEquals(-1, rows);
	}
	
	@Test
	public void testUpdateObject(){
		ProjectDao dao=(ProjectDao) ctx.getBean("projectDao");
		//1.根据id查找project对象
		Project pro=dao.findObjectById(2);
		//2.修改对象内容
		pro.setName("中国白金游");
		//3.将对象内容持久化到数据库
		int rows=dao.updateObject(pro);
		Assert.assertEquals(1, rows);
	}
	
	
	
}
//DB-->ProjectMapper.xml-->ProjectDao-->ProjectService--->ProjectController-->页面