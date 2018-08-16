package cn.aliyun.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.aliyun.ttms.product.dao.ProductTypeDao;
import cn.aliyun.ttms.product.entity.ProductType;
import cn.aliyun.ttms.product.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl  implements ProductTypeService{
	
	@Resource
	private  ProductTypeDao productTypeDao;
	public List<Map<String, Object>> findObjects() {
		return productTypeDao.findObjects();
	}
	public List<Map<String, Object>> findTreeNodes() {
				List<Map<String, Object>> map=
				productTypeDao.findTreeNodes();
				System.out.println(map);
				return map;
	}
	public void saveObject(ProductType type) {
		int rows=productTypeDao.insertObject(type);
		if(rows==-1)throw new RuntimeException("insert error");
		
	}
	public void updateObject(ProductType type) {
		int rows=productTypeDao.updateObject(type);
		if(rows==-1)throw new RuntimeException("update error");
	}
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null)throw new RuntimeException("id can not be null");
		return productTypeDao.findObjectById(id);
	}

}
