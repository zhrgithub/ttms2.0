package cn.aliyun.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.aliyun.ttms.product.entity.ProductType;
public interface ProductTypeService {
		List<Map<String, Object>> findObjects();
		List<Map<String, Object>> findTreeNodes();
		
		void saveObject(ProductType type);
		void updateObject(ProductType type);
		Map<String,Object> findObjectById(Integer id);
		void deleteObjectById(Integer id);
		
}
