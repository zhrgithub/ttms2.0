package cn.aliyun.ttms.project.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.aliyun.ttms.common.web.JsonResult;
import cn.aliyun.ttms.common.web.PageObject;
import cn.aliyun.ttms.project.entity.Project;
import cn.aliyun.ttms.project.service.ProjectService;
/**
 * 产品项目管理控制器对象
 * */

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Resource
	private ProjectService projectService;
	
	/***
	 * 此方法用于返回项目管理的列表页面
	 * http://localhost:8080/ttms2.0/project/listUI.do
	 * @return
	 */
	@RequestMapping("/listUI")
	public String listUI(){
		return "project/project_list";//通过查看spring-mvc.xml
	}//? return 语句返回的字符串对应一个jsp文件(在哪，名字是什么)
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "project/project_edit";//通过查看spring-mvc.xml
	}
	
	
	@RequestMapping("/findObjects")
	@ResponseBody//此注释的作用是将Java对象转换一个json字符串，然后返回
	//
	public Map<String,Object> findObjects(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", 1);
		map.put("code", "TT-2018511-CHN-LF-001");
		map.put("name", "环球游");
		return map;//{"id",:1,"code":"TT-2018511-CHN-LF-001"}
	}
	@RequestMapping("/projectList")
	@ResponseBody
	public List<Project> projectList(){
		return projectService.findObjects();
	}
	
	@RequestMapping("/findPageObjects")
	@ResponseBody//将返回的JSON转化成字符串
	public JsonResult findPageObjects(Project project,PageObject pageObject){
		//参数用于封装页面上传递的数据
		Map<String,Object> map=projectService.findPageObjects(project,pageObject);
		return new JsonResult(map);//state=1; message=ok; 
	}
	
	/**
	 * 启用禁用
	 * @param checkedIds
	 * @param valid
	 * @return
	 */
	@RequestMapping("/doValidById")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doValidById(String checkedIds,Integer valid){
		projectService.ValidById(checkedIds, valid);
		return new JsonResult();//state=1,message=ok
	}
	
	@RequestMapping("/doSaveProject")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doSaveProject(Project project){
		projectService.saveObject(project);
		return new JsonResult();//state=1,message=ok
	}
	/**查找项目信息*/
	@RequestMapping("/doFindById")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doFindProjectById(Integer id){
		Project project=projectService.findObjectById(id);
		return new JsonResult(project);
	}
	/**修改项目信息*/
	@RequestMapping("/doUpdateProject")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doUpdateProject(Project project){
		projectService.updateObject(project);
		return new JsonResult();
	}
	
	
}
