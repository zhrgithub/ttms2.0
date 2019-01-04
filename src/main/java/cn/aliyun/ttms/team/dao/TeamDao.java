package cn.aliyun.ttms.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.aliyun.ttms.common.dao.BaseDao;
import cn.aliyun.ttms.team.entity.Team;

public interface TeamDao  extends BaseDao<Team>{
	/**向表中写入数据*/
		//int insertObject(Team team);
		/**
		 * 一条记录对应一个map（key为列的名字，value为列的值，多条记录是多个map对象，
		 * 然后多个map放到list集合）
		 * 分页查询项目信息 */
		List<Map<String,Object>> findPageObjects(
				@Param("projectName")String projectName,
				@Param("valid")Integer valid,
				@Param("startIndex")int startIndex,
				@Param("pageSize")int pageSize);
		/**统计表中的记录数*/
		public int getRowCount(
				@Param("projectName")String projectName,
				@Param("valid")Integer valid);
		/**根据ID和valid修改valid的状态*/
		public int validById(//下一步写MySQL语句
				@Param("ids")String[] ids,
				@Param("valid")Integer valid);
		
		
		//根据id查找项目信息
		Team findObjectById(Integer id);
		
		/**根据id修改project对象*/
	//	int updateObject(Team team);bhbhb
}
