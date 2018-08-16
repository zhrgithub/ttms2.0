
$(document).ready(function(){
	$("#queryFormId").on("click",".btn-search",doGetObjects);	//查询表中的信息
	$("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);	//启用和禁用
	$("#queryFormId").on("click",".btn-add,.btn-update",showEditDialog);	//添加或者更新
	//加载数据
	doGetObjects();
	
});
//显示编辑模态框
function showEditDialog(){
	var title;
	var url="team/editUI.do";
	if($(this).hasClass("btn-add")){
		title="添加团信息"
	}
	if($(this).hasClass("btn-update")){
		title="修改团信息"
			//将要修改的记录的id值绑定到模态框上
			//目的是通过一个模块实现添加或者更新操作
		var id=	$("#modal-dialog").data("id",$(this).parent().parent().data("id"));//绑定id值，根据对象找到修改
		console.log("team_list_id="+id);
	}
	//在模态框的 .moday-body位置异步加载url
	$("#modal-dialog .modal-body").load(url,function(){
		console.log("url:"+url);
		$(".modal-title").html(title);
		$("#modal-dialog").modal("show");
	})
}




function doGetObjects(){
	//1.url
	var url="team/doFindPageObjects.do";
	//2.params
	var params=getQueryFormData();
	//动态的给属性赋值   加载时为undefined
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(pageCurrent){//有数值 返回true
			params.pageCurrent=pageCurrent
		}else{//返回值不能为空，否则报空指针异常
			params.pageCurrent=1;
		}
	  console.log(pageCurrent);
	//3.ajax(post)
	$.post(url,params,function(result){
		 console.log(JSON.stringify(result));
		if(result.state==1){
			//3.1填充表格(setTableRows(result.data.list))
			setTableRows(result.data.list);
			//3.2设置分页(setPagination(result.pageObject))
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	})
}

//获取表单中的数据
function setTableRows(list){
		var tds='<td><input type="checkbox" name="checkedItem" value="[id]"/></td>'
			+'<td>[name]</td>'
			+'<td>[projectName]</td>'
			+'<td>[valid]</td>'
			+'<td><button type="button" class="btn btn-success btn-update">修改</button></td>'
			//追加新的数据
			var tBody=$('#tbodyId');
			tBody.empty();
			 for(var i in list){//循环一次取一行数据(对应一对tr对象)
			  var tr=$("<tr></tr>");//创建一对tr对象
			  tr.data("id",list[i].id);//绑定数据,便于后续获得此数据进行修改等操作
			  tr.append(tds
	         .replace("[id]",list[i].id)
	         .replace("[name]",list[i].name)
	         .replace("[projectName]",list[i].projectName)
	         .replace("[valid]",list[i].valid?'启用':'禁用')
	          );
				tBody.append(tr);//将tr对象追加tbody
		}
}

//获取查询参数
function getQueryFormData(){//对象名要与controller里面的属性名相同
	var params = {
			"projectName":$("#searchNameId").val(),
			"valid":$("#searchValidId").val()}
	return params;
}




/*启用/禁用*/
function doValidById(){
	//1.判定触发的是启用还是禁用按钮（根据类选择器）
	var state;//定义一个状态值，表示状态
	if($(this).hasClass("btn-valid")){
		state=1;//启用（将选中的记录的valid修改为1）
	}else{
		state=0;//禁用
	}
	//2.获得选中的checkbox对应的id值
	var checkedIds=getCheckedIds();
	if(checkedIds==' '){
		console.log("值为空");
	}
	console.log("checkedIds="+checkedIds+"valid="+state);//在浏览器的控制台输出已经选择的id
	var params={"checkedIds":checkedIds,"valid":state};
	//3.获得的数据通过Ajax发送异步请求到服务器然后执行更新操作
	//执行更新操作
	var url="team/doValidById.do";
	$.post(url,params,function(result){
		console.log("result.state="+result.state);
		if (result.state==1) {//1表示查询成功了
			doGetObjects();//重新查询
		} else {
			alert(result.message);
		}
	})
}	

function getCheckedIds(){
	 var checkedIds=' ';
	 //获得tbody对象中名字为checkedItem的input对象
	 $('tbody input[name="checkedItem"]')
	 //迭代对象
	 .each(function(){
		 //判定这个input对象是否选中的input
		 if($(this).is(":checked")){
			 //将选中的checkbox的值拼接成字符串
			 if(checkedIds==' '){
				 checkedIds+=$(this).val();//this表示当前点击按钮对象
			 }else{
				 checkedIds+=","+$(this).val();
			 }//"1,2,3,4,5";
		 };
	 })
	 return checkedIds;
}