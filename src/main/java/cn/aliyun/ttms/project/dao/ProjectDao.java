package cn.aliyun.ttms.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.aliyun.ttms.common.dao.BaseDao;
import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.entity.Project;

/**
 * 此接口对象为一个数据访问对象（关联一个mapper）
 * 所在层：DAL（数据访问层）
 */
public interface ProjectDao extends BaseDao<Project>{
	/**
	 * @param entity 代表是一个project对象
	 * @return 表示 insert 记录的行数
	 */
	//public int insertObject(Project entity);
	/**查询tms_projects表的所有信息*/
	public List<Project> findObjects();
	/**
	 * 分页查询项目信息
	 */
	public List<Project>  
    findPageObjects(
    		@Param("project")Project project,
    		@Param("pageObject")PageObject pageObject);
	/**获得表中的记录数*/
	public int getRowCount(Project project);
	/**
	 * 禁用启用记录数
	 * @param ids
	 * @param valid
	 * @return 表示更新的行数，假如返回值为-1表示更新失败
	 * 一般情况下两个参数的时候使用  @Param("xxx")
	 */
	public int validById(@Param("ids")String[] ids,@Param("valid")Integer valid);
	/**根据id查找project对象*/
	Project findObjectById(Integer id);
	/**根据id修改project对象*/
	//int updateObject(Project project);
	/**查询项目的ID和名字*/
	List<Map<String, Object>> findIdAndNames();
}

