package cn.aliyun.ttms.common.dao;

/**类上定义的泛型用于约束类中方法的参数类型，方法的返回值类型或属性的类型*/
public interface BaseDao<T> {
	
	int insertObject(T t);
	int updateObject(T t);
	
	
	
	
}
