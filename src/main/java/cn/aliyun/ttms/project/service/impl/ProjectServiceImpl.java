package cn.aliyun.ttms.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.aliyun.ttms.common.exception.SaveRuntimeException;
import cn.aliyun.ttms.common.exception.UpdateRuntimeException;
import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.dao.ProjectDao;
import cn.aliyun.ttms.project.entity.Project;
import cn.aliyun.ttms.project.service.ProjectService;
/**
 * 项目管理service对象
 * 项目所有与业务相关的事情一般都要放在service中，例如
 * 1)判断参数是否符合业务要求
 * 2)判断dao是否是我们需要的结果
 * 3)执行一些日志记录
 * 4)执行一些事务的处理
 * 5)........
 * @author Admin
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService{

	@Resource
	private ProjectDao projectDao;
	
	//查找多条信息
	public List<Project> findObjects() {
		//.......后续添加内容
		return projectDao.findObjects();
	}
	
	/**
	 * @param pageObject 用于接收控制层传递过来的分页信息
	 * 1)此参数中应包含startIndex
	 * 2)此参数中应包含pageSize
	 */
	public Map<String, Object> findPageObjects(Project project,PageObject pageObject) {
		// 1.获得页面表格中要显示的数据
		List<Project>list=projectDao.findPageObjects(project,pageObject);
		//2.获得表中记录数并计算页数
		int rowCount=projectDao.getRowCount(project);
		pageObject.setRowCount(rowCount);
		//3.构建map对象封装从dao层获得的数据
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);//分页数据
		return map;
	}

	public void ValidById(String checkedIds, Integer valid) {
		
		
		//判定参数checkedIds
		if(checkedIds==null||checkedIds.length()==0){
			throw new NullPointerException("必须有效果的id的值");
		}
		
		if(valid!=1&&valid!=0){
			throw new IllegalArgumentException("无效的valid值");
		}
		//解析字符串（1，2，3，4，5）；
		String ids[]=checkedIds.split(",");
		
		//访问dao层方法执行启用禁用的更新操作
		int rows=projectDao.validById(ids,valid);
		if(rows==-1)throw new RuntimeException("更新失败");
		
	}
	
	public void saveObject(Project project) {
		int rows= projectDao.insertObject(project);//将插入的数据保存到列表中
		if(rows==-1){
			throw new RuntimeException("save error");
		}
	}


	public Project findObjectById(Integer id) {
		if(id==null)
			throw new NullPointerException("it can not be null");
		Project pro=projectDao.findObjectById(id);
		if(pro==null)
			throw new SaveRuntimeException("project dose not exist");
		return pro;
	}
	public void updateObject(Project project) {
		int rows=	projectDao.updateObject(project);
		if(rows==-1)//正常情况下应该是1
			//说明在service中写道的自定义异常一般都是继承RuntimeException
			throw new UpdateRuntimeException("update error");
		
	}
}






















