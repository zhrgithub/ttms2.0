package product.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.aliyun.ttms.product.dao.ProductTypeDao;
import cn.aliyun.ttms.product.entity.ProductType;
import common.dao.TestBaseDao;

public class TestProductTypeDao  extends TestBaseDao{
		
	@Test
	public void testFindObjects(){
		ProductTypeDao typeDao=(ProductTypeDao)ctx.getBean("productTypeDao");
		List<Map<String, Object>> list=typeDao.findObjects();
		System.out.println(list);
		Assert.assertEquals(null, list);
	}
	
	
	@Test
	public void testInsertObject(){
		ProductTypeDao typeDao=(ProductTypeDao)ctx.getBean("productTypeDao");
		ProductType t1=new ProductType();
		t1.setName("皮球游");
		t1.setSort(4);
		t1.setParentId(139);
		t1.setNote("足球游");
		t1.setCreatedUser("admin");
		t1.setModifiedUser("admin");
		int rows=typeDao.insertObject(t1);
		Assert.assertEquals(1, rows);
	}
	
	
	
	
	
	
}
