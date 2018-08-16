$(document).ready(function(){
	$("#queryFormId").on('click','.btn-add,.btn-update',doShowEditDialog);
	$("#queryFormId").on('click','.btn-search',doGetObjects);
	$("#queryFormId").on('click','.btn-valid,.btn-invalid',doValidById);	
	doGetObjects();
});
//获取项目记录信息
function doGetObjects(){
	
	var url="project/findObjects.do"
    var params=getQueryParamValues();
	var currentPage=$('#pagination').data('curPage');
	if(currentPage)params.pageCurrent=currentPage;
	$.post(url,params,function(result){
		if(result.state==1){
		  setTableRows(result.data.list);
		  setPagination(result.data.pageObject);
		}else{
		  alert(result.message);
		}
	});
}

//获取查询参数
function getQueryParamValues(){
	var name = $('#searchNameId').val();
	var projectId=$('#searchValidId option:selected') .val();//选中的值
	var params = {'name':name,'projectId':projectId,}
	return params;
}
//设置项目
function setTableRows(list){
	var tBody=$('tbody');
	tBody.empty();
	var tds='<td><input type="checkbox" class="checkbox" name="checkedItem" value="[id]"></td>'+
	'<td>[code]</td>'+
	'<td>[name]</td>'+
	'<td>[beginDate]</td>'+
	'<td>[endDate]</td>'+
	'<td>[valid]</td>'+
	'<td><a class="btn btn-default btn-update doShowEditDialog">修改</a></td>';
	
	for(var i in list){ 
	   var tr=$("<tr></tr>");
	   tr.data("id",list[i].id);
	   tr.append(tds.replace('[id]',list[i].id) 
	   .replace('[name]',list[i].name)
	   .replace('[code]',list[i].code)
	   .replace('[beginDate]',new Date(list[i].beginDate).toLocaleDateString())
	   .replace('[endDate]',new Date(list[i].endDate).toLocaleDateString())
	   .replace('[valid]',list[i].valid?'启用':'禁用'));
	    tBody.append(tr);
	}
}
//显示编辑框
function doShowEditDialog(){
	var uri='project/editUI.do?t='+Math.random(1000);
	if($(this).hasClass("btn-add")){
		title="添加项目";
	}
	if($(this).hasClass("btn-update")){
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
        title="修改项目"
	}
	$('#modal-dialog .modal-body').load(uri,function(){
		$('#modal-dialog .modal-title').html(title);
		$('#modal-dialog').modal('show');
	});
}

//禁用&启用
function doValidById(){
	var state;
	//判断点击的按钮
	if($(this).hasClass("btn-valid")){
		state=1;
	}else{
		state=0;
	}
	//获得选中的id
	var checkedIds=getCheckedIds();
	if(checkedIds==''){
		alert("至少选择一个");
		return;
	}
	//发送ajax请求修改状态
	var url='project/doValidById.do';
	var params={'checkedIds':checkedIds,'valid':state}
	$.post(url,params,function(result){
		if(result.state==1){
		  doGetObjects();
		}else{
		  alert(result.message);
		}
	})
}
//获得选中的id，然后拼接成字符串
function getCheckedIds(){
	var checkedIds ='';
	$('tbody input[name="checkedItem"]').each(function(){
		if($(this).is(':checked')){  //或者用prop('checked')
			if(checkedIds==''){
			   checkedIds += $(this).val();
			}else{
			   checkedIds += ','+$(this).val();
			}
		}
	})
	return checkedIds;
}