package cn.aliyun.ttms.team.controller;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.aliyun.ttms.common.web.JsonResult;
import cn.aliyun.ttms.project.entity.Project;
import cn.aliyun.ttms.team.entity.Team;
import cn.aliyun.ttms.team.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Resource
	private TeamService teamService;
	@RequestMapping("/listUI")
	public String listUI(){
		return "team/team_list";
	}
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "team/team_edit";//通过查看spring-mvc.xml
	}
	
	/**更新数据的操作*/
	@RequestMapping("/doUpdateTeam")
	@ResponseBody
	public JsonResult doUpdateObject(Team team) {
		teamService.updateObject(team);
		return new JsonResult();
	}
	
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String projectName,Integer valid,Integer pageCurrent){
		System.out.println("pageCurrent:"+pageCurrent);
		Map<String,Object> map=teamService.findPageObjects(projectName, valid, pageCurrent);
		return new JsonResult(map);
	}
	
	
	@RequestMapping("/doValidById")
	@ResponseBody
	//从页面上选项框获得的ID值和valid
	public JsonResult doValidById(String checkedIds,Integer valid){
		teamService.ValidById( checkedIds,valid);
		return new JsonResult();
	}
	
	/**保存添加信息的方法*/
	@RequestMapping("/doSaveObject")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doSaveObject(Team team){
		teamService.saveObject(team);
		return new JsonResult();//state=1,message=ok
	}
	
	/**获得项目信息的ID和名字*/
	@RequestMapping("/doFindPrjIdNames")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doFindProjectIdAndNames(){
		List<Map<String,Object>> map=teamService.findProjectIdAndNames();
		return new JsonResult(map);
	}
	
	/**根据id查找项目信息*/
	@RequestMapping("/doFindById")
	@ResponseBody //将返回的JSON转化成字符串
	public JsonResult doFindTeamById(Integer id){
		Team team=teamService.findObjectById(id);
		return new JsonResult(team);
	}
	
	
	
	
	
	
}
