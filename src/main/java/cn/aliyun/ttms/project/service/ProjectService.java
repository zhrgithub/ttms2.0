package cn.aliyun.ttms.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.entity.Project;

@Service
public interface ProjectService {
	
	public List<Project> findObjects();
	/**返回分页记录
	 * 1）记录信息
	 * 2）分页信息
	 */
	public Map<String,Object> findPageObjects(Project project,PageObject pageObject);
	
	
	/**
	 * 启用禁用记录
	 * @param checkedIds
	 * @param valid
	 */
	public void ValidById(String checkedIds,Integer valid);
	
	public void saveObject(Project project);
	/**根据id查找project对象*/
	Project findObjectById(Integer id);
	/**修改项目信息*/
	public void updateObject(Project project);
}
