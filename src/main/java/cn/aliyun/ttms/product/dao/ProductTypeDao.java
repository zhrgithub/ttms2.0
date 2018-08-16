package cn.aliyun.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.aliyun.ttms.common.dao.BaseDao;
import cn.aliyun.ttms.product.entity.ProductType;

public interface ProductTypeDao  extends BaseDao<ProductType>{
	List<Map<String,Object>> findObjects();
		/**查找tZtree nodes 节点*/
		List<Map<String, Object>> findTreeNodes();
		Map<String,Object> findObjectById(Integer id);
}
