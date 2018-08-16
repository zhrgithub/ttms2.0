package cn.aliyun.ttms.team.service;

import java.util.List;
import java.util.Map;


import cn.aliyun.ttms.team.entity.Team;

public interface TeamService {
	/**执行分页查询，参数来自TeamController
	 * @param projectName 根据项目名称执行查询操作
	 * @param valid 根据禁用启用执行查询操作
	 * @param pageCunrent 表示当前页(要查询的第几页的数据)*/
	Map<String, Object> findPageObjects(
			String projectName,
			Integer valid,
			Integer pageCurrent);

	public void ValidById(String checkedIds,Integer valid);
	/**保存表单team信息,添加团的信息*/
	 public void saveObject(Team team);
	
		/**修改项目信息*/
	public void updateObject(Team team);
	
	 /**查询项目的ID和名字*/
	 List<Map<String, Object>> findProjectIdAndNames();

	Team findObjectById(Integer id);
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
