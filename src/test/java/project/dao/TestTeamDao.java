package project.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.dao.ProjectDao;
import cn.aliyun.ttms.team.dao.TeamDao;
import cn.aliyun.ttms.team.entity.Team;
import common.dao.TestBaseDao;

public class TestTeamDao extends TestBaseDao{

	@Test
	public void testInsertObject(){
		TeamDao dao=(TeamDao)ctx.getBean("teamDao");
		Team t1=new Team();
		t1.setName("中国白金游5日团");
		t1.setValid(1);
		t1.setProjectId(2);//必须是项目表中有的一个项目
		t1.setNote("中国白金游5日团");
		t1.setCreatedUser("admin");
		t1.setCreatedTime(new Date());
		t1.setModifiedTime(new Date());
		t1.setModifiedUser("admin");
		int rows=dao.insertObject(t1);
		Assert.assertEquals(1, rows);
	}
	
	@Test//测试分页查询
	public void testFindPageObjects(){
		TeamDao dao=(TeamDao)ctx.getBean("teamDao");
		String projectName="中国峰峰马拉松";//查询的是project里面的项目名称
		Integer valid=1;
		int startIndex=0;
		int pageSize=2;
		List<Map<String,Object>> list=dao.findPageObjects(projectName,valid,startIndex,pageSize);
		System.out.println("list="+list);
		Assert.assertNotEquals(null, list);
		System.out.println("list="+list.size());
	
		//根据条件获得数据，然后计算总页数
		int rowCount=dao.getRowCount(projectName, valid);
		PageObject pageObject=new PageObject();
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		Assert.assertEquals(1, //当我的数据库中就1页数据
				pageObject.getPageCount());
		System.out.println("pageCount="+pageObject.getPageCount());
	}
	
	@Test//测试根据ID修改后的valid是否成功
	public void testValidById(){
		TeamDao dao=(TeamDao) ctx.getBean("teamDao");
		int rows=dao.validById(new String[] {"2"}, 0);
		System.out.println("valid.rows="+rows);
		Assert.assertNotEquals(-1, rows);
	}
	
	
	
	
	
}

		

