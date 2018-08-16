package cn.aliyun.ttms.team.service.imp;
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
import cn.aliyun.ttms.team.dao.TeamDao;
import cn.aliyun.ttms.team.entity.Team;
import cn.aliyun.ttms.team.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	
	/**一个Dao一般对应一个mapper文件,一个service可以对应多个Dao*/
	@Resource
	private  TeamDao teamDao;
	/**查询project信息*/
	@Resource
	private ProjectDao projectDao;
	
	public Map<String, Object> findPageObjects(String projectName, Integer valid, Integer pageCurrent) {
		
		PageObject pageObject =new PageObject();
		//pageObject.setPageSize(3);不写默认是2，我们在对象中定义的
		pageObject.setPageCurrent(pageCurrent);
		//根据startIndex及参数获得当前页数据
		List<Map<String, Object>> list=
		teamDao.findPageObjects(projectName, valid, pageObject.getStartIndex(),pageObject.getPageSize());
		//获得总页数
		int rowCount=teamDao.getRowCount(projectName, valid);
		pageObject.setRowCount(rowCount);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
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
		int rows=teamDao.validById(ids,valid);
		if(rows==-1)throw new RuntimeException("更新失败");
	}

	/**通常情况下不用判定信息是否为空，spring-MVC会自动判定，单元测试需要*/
	public void saveObject(Team team) {
		if(team==null){
			throw new SaveRuntimeException("保存的信息不能为空");
		}
		int rows= teamDao.insertObject(team);//将插入的数据保存到列表中
		if(rows==-1){
			throw new SaveRuntimeException("保存失败");
		}
		
	}
	
	/**查询项目ID和名字的实现方法*/
	public List<Map<String, Object>> findProjectIdAndNames() {
		return projectDao.findIdAndNames();
	}

	
	public void updateObject(Team team) {
		int result=teamDao.updateObject(team);
		if(result==-1)//正常情况下应该是1
			//说明在service中写道的自定义异常一般都是继承RuntimeException
			throw new UpdateRuntimeException("update error");
	}

	public Team findObjectById(Integer id) {
		if(id==null)
			throw new NullPointerException("it can not be null");
		Team tea=teamDao.findObjectById(id);
		if(tea==null)
			throw new SaveRuntimeException("project dose not exist");
		return tea;
	}

}
